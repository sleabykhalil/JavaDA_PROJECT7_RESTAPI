package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.dto.BidListDto;
import com.nnk.springboot.domain.dto.mapper.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {
    @Autowired
    BidListRepository bidListRepository;
    @Autowired
    BidListMapper bidListMapper;

    @Override
    public List<BidList> findAllBids() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList add(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList update(Integer bidListId, BidList bidList) {
        BidListDto bidListDto = bidListMapper.bidListToBidListDte(bidList);
        bidListDto.setBidListId(bidListId);
        BidList bidListToSave= bidListMapper.bidListDteToBidList(bidListDto);
        return bidListRepository.save(bidListToSave);
    }

    @Override
    public BidList findBidListById(Integer bidListId) {
        return bidListRepository.getOne(bidListId);
    }

    @Override
    public void delete(BidList bidListToDelete) {
          bidListRepository.delete(bidListToDelete);
    }


}
