package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BidListServiceImpl implements BidListService {
    @Autowired
    BidListRepository bidListRepository;

    @Override
    public List<BidList> findAllBids() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList add(BidList bidList) {
        BidList bidListToAdd = bidListRepository.save(bidList);
        log.debug("Bid added id=[{}]", bidListToAdd.getBidListId());
        return bidListToAdd;
    }


    @Override
    public BidList findBidListById(Integer bidListId) {
        Optional<BidList> bidList = bidListRepository.findById(bidListId);
        if (bidList.isPresent()) {
            log.info("bid founded Id=[{}]", bidList.get().getBidListId());
            return bidList.get();
        }
        log.error("bid can not found Id=[{}] null returned", bidListId);
        return null;
    }

    @Override
    public BidList update(BidList bidList) {
        BidList bidListToUpdate = bidListRepository.save(bidList);
        log.debug("Bid updated id=[{}]", bidListToUpdate.getBidListId());
        return bidListToUpdate;
    }

    @Override
    public void delete(Integer bidListId) {
        if (bidListRepository.findById(bidListId).isPresent()) {
            bidListRepository.deleteById(bidListId);
            log.debug("Bid deleted id=[{}]", bidListId);
        }
        log.debug("bid not  found id=[{}]", bidListId);
    }
}
