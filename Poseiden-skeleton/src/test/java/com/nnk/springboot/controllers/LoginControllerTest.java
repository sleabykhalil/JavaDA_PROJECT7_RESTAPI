package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "user"
            , password = "123456")
    @Test
    void error() throws Exception {
        mockMvc.perform(get("/error"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = "user"
            , password = "123456")
    @Test
    void getAllUserArticlesWithUserRoleReturnClientError() throws Exception {
        mockMvc.perform(get("/secure/article-details"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username = "admin"
            , password = "123456", roles = {"ADMIN"})
    @Test
    void getAllUserArticlesWithAdminRole() throws Exception {
        mockMvc.perform(get("/secure/article-details"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}