package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {
    @Mock
    TradeRepository tradeRepositoryMock;
    TradeService tradeServiceUnderTest;

    @BeforeEach
    void setUp() {
        tradeServiceUnderTest = new TradeServiceImpl(tradeRepositoryMock);
    }

    @Test
    void findAll() {
        //given
        Trade trade = new Trade("Trade Account", "Type");
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);
        //when
        when(tradeRepositoryMock.findAll()).thenReturn(tradeList);
        List<Trade> result = tradeServiceUnderTest.findAll();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    void add() {
        //given
        Trade trade = new Trade("Trade Account", "Type");
        trade.setTradeId(1);
        //when
        when(tradeRepositoryMock.save(trade)).thenReturn(trade);
        Trade result = tradeServiceUnderTest.add(trade);
        //then
        assertThat(result.getTradeId()).isEqualTo(1);
    }
}