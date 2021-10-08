package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {
@Autowired
    BidListRepository bidListRepository;
    @Override
    public List<BidList> findAllBids() {
        return bidListRepository.findAll();
    }
}
