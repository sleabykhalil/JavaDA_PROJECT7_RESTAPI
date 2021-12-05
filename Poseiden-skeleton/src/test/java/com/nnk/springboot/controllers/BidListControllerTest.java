package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BidListControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BidListRepository bidListRepository;

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void getBidList() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void getAddBidList() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void getUpdateBidList() throws Exception {
        BidList bidList = bidListRepository.save(BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(1.0).build());
        mockMvc.perform(get("/bidList/update/{id}", bidList.getBidListId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void postUpdateBidList() throws Exception {
        BidList bidList = bidListRepository.save(BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(1.0).build());
        BidList newBidList = BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(2.0).build();
        mockMvc.perform(post("/bidList/update/{id}", bidList.getBidListId().toString())
                        .flashAttr("bidList", newBidList))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void postUpdateBidList_whenValidationError_RedirectToSamePage() throws Exception {
        BidList bidList = bidListRepository.save(BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(1.0).build());
        BidList newBidList = BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(2.0).build();
        Object object = mockMvc.perform(post("/bidList/update/{id}", bidList.getBidListId().toString()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    @WithMockUser(username = "user"
            , password = "123456"
            , roles = {"USER"})
    void getDeleteBidList() throws Exception {
        BidList bidList = bidListRepository.save(BidList.builder()
                .account("test")
                .type("type")
                .bidQuantity(1.0).build());
        mockMvc.perform(get("/bidList/delete/{id}", bidList.getBidListId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
        Optional<BidList> result = bidListRepository.findById(bidList.getBidListId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }
}