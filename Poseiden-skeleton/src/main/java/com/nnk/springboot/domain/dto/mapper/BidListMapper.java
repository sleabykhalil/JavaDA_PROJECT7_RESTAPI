package com.nnk.springboot.domain.dto.mapper;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.dto.BidListDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BidListMapper {

    List<BidList> bidListDtesToBidLists(List<BidListDto> bidListDtos);
    List<BidListDto> bidListsToBidListDtes(List<BidList> bidLists);
    BidListDto bidListToBidListDte(BidList bidList);
    BidList bidListDteToBidList(BidListDto bidListDto);
}
