package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void home() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void homeAdminWithoutLoginRedirectToLogin() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = "user"
            , password = "123456")
    @Test
    void homeAdminWithUserRoleReturnClientError() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username = "admin"
            , password = "123456", roles = {"ADMIN"})
    @Test
    void homeAdminWithAdminRole() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }
}