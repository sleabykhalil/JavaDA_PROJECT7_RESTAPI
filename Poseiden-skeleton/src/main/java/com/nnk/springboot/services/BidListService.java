package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidListService {
    List<BidList> findAllBids();

    BidList add(BidList bidList);

    BidList update(BidList bidList);

    BidList findBidListById(Integer bidListId);

    void delete(Integer bidListId);
}
