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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    void findById_whenFound_ReturnTrade() {
        //given
        Trade trade = new Trade("Trade Account", "Type");
        trade.setTradeId(1);
        when(tradeRepositoryMock.findById(1)).thenReturn(Optional.of(trade));
        //when
        Trade result = tradeServiceUnderTest.findById(1);
        //then
        assertThat(result.getTradeId()).isEqualTo(trade.getTradeId());
    }

    @Test
    void findById_whenNotFound_ReturnNull() {
        //given
        when(tradeRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //when
        Trade result = tradeServiceUnderTest.findById(1);
        //then
        assertThat(result).isNull();
    }

    @Test
    void update() {
        //given
        Trade trade = new Trade("Trade Account", "Type");
        trade.setTradeId(1);
        when(tradeRepositoryMock.save(trade)).thenReturn(trade);
        //when
        Trade result = tradeServiceUnderTest.update(trade);
        //then
        assertThat(result.getTradeId()).isEqualTo(trade.getTradeId());
    }
}