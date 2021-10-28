package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {
    @Mock
    BidListRepository bidListRepositoryMock;

    BidListService bidListServiceUnderTest;

    @BeforeEach
    void setUp() {
        bidListServiceUnderTest = new BidListServiceImpl(bidListRepositoryMock);
    }

    @Test
    void findAllBids() {
        //given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(bid);
        when(bidListRepositoryMock.findAll()).thenReturn(bidLists);
        //when
        List<BidList> result = bidListServiceUnderTest.findAllBids();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    void add() {
        //given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        when(bidListRepositoryMock.save(bid)).thenReturn(bid);
        //when
        BidList result = bidListServiceUnderTest.add(bid);
        //then
        assertThat(result.getAccount()).isEqualTo(bid.getAccount());
    }

    @Test
    void update() {
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        bid.setBidListId(1);
        when(bidListRepositoryMock.save(bid)).thenReturn(bid);
        //when
        BidList result = bidListServiceUnderTest.update(bid);
        //then
        assertThat(result.getBidListId()).isEqualTo(1);
    }

    @Test
    void findBidListById() {
        //given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        bid.setBidListId(1);
        when(bidListRepositoryMock.findById(1)).thenReturn(Optional.of(bid));
        //when
        BidList result = bidListServiceUnderTest.findBidListById(1);
        //then
        assertThat(result.getBidListId()).isEqualTo(1);
    }

    @Test
    void delete() {
        //given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        bid.setBidListId(1);
        when(bidListRepositoryMock.findById(1)).thenReturn(Optional.of(bid));
        doNothing().when(bidListRepositoryMock).deleteById(1);
        //when
        bidListServiceUnderTest.delete(1);
        //then
        verify(bidListRepositoryMock, times(1)).deleteById(1);
    }
}