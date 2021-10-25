package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade add(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade findById(Integer tradeId) {
        Optional<Trade> tradeToUpdate = tradeRepository.findById(tradeId);
        if (tradeToUpdate.isPresent()) {
            log.debug("Trade found id=[{}]", tradeId);
            return tradeToUpdate.get();
        } else {
            log.debug("Trade not found =[{}]", tradeId);
            return null;
        }

    }

    @Override
    public Trade update(Trade trade) {
        Trade tradeAfterUpdate = tradeRepository.save(trade);
        log.debug("Trade updated id=[{}]", tradeAfterUpdate.getTradeId());
        return tradeAfterUpdate;
    }

}
