package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeService {
    List<Trade> findAll();

    Trade add(Trade trade);
}
