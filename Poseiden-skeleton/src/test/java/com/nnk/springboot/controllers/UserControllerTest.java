package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user"
        , password = "123456")
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MyUserRepository myUserRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @WithMockUser(username = "admin"
            , password = "123456", roles = {"ADMIN"})
    @Test
    void getUserList() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addUser() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void validate() throws Exception {
        MyUser myUser = MyUser.builder()
                .username("validUserTest")
                .fullname("validUserTest")
                .password("P@ssw0rd")
                .role("ROLE_USER")
                .build();
        mockMvc.perform(post("/user/validate")
                        .flashAttr("myUser", myUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void showUpdateForm() throws Exception {
        MyUser myUser = myUserRepository.save(MyUser.builder()
                .username("validUserTest")
                .fullname("validUserTest")
                .password("P@ssw0rd")
                .role("ROLE_USER")
                .build());
        mockMvc.perform(get("/user/update/{id}", myUser.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        MyUser myUser = myUserRepository.save(MyUser.builder()
                .username("validUserTest")
                .fullname("validUserTest")
                .password(encoder.encode("P@ssw0rd"))
                .role("ROLE_USER")
                .build());
        MyUser newMyUser = MyUser.builder()
                .username("newValidUserTest")
                .fullname("newValidUserTest")
                .password("P@ssw0rd")
                .role("ROLE_USER")
                .build();
        mockMvc.perform(post("/user/update/{id}", myUser.getId().toString())
                        .flashAttr("myUser", newMyUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void deleteUser() throws Exception {
        MyUser myUser = myUserRepository.save(MyUser.builder()
                .username("validUserTest")
                .fullname("validUserTest")
                .password(encoder.encode("P@ssw0rd"))
                .role("ROLE_USER")
                .build());
        mockMvc.perform(get("/user/delete/{id}", myUser.getId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
        Optional<MyUser> result = myUserRepository.findById(myUser.getId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    @WithMockUser(username = "admin"
            , password = "123456", roles = {"ADMIN"})
    @Test
    void getAllUserArticles() throws Exception {
        mockMvc.perform(get("/secure/article-details"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}