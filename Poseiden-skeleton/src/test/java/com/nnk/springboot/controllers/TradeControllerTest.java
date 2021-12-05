package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class TradeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TradeRepository tradeRepository;

    @Test
    void getTradeList() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addTrade() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void validate() throws Exception {
        Trade trade = new Trade("Trade Account", "Type");
        mockMvc.perform(post("/trade/validate")
                        .flashAttr("trade", trade))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void showUpdateForm() throws Exception {
        Trade trade = tradeRepository.save(new Trade("Trade Account", "Type"));
        mockMvc.perform(get("/trade/update/{id}", trade.getTradeId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateTrade() throws Exception {
        Trade trade = tradeRepository.save(new Trade("Trade Account", "Type"));
        Trade newTrade = new Trade("Trade Account", "Type");
        mockMvc.perform(post("/trade/update/{id}", trade.getTradeId().toString())
                        .flashAttr("trade", newTrade))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void deleteTrade() throws Exception {
        Trade trade = tradeRepository.save(new Trade("Trade Account", "Type"));
        mockMvc.perform(get("/trade/delete/{id}", trade.getTradeId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
        Optional<Trade> result = tradeRepository.findById(trade.getTradeId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }
}