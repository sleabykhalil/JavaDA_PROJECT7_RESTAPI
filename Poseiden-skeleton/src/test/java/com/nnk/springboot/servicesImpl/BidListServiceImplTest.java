package com.nnk.springboot.servicesImpl;

import com.nnk.springboot.domain.dto.mapper.BidListMapper;
import com.nnk.springboot.domain.dto.mapper.BidListMapperImpl;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.servicesImpl.BidListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {
    @Mock
    BidListRepository bidListRepositoryMock;

    BidListMapper bidListMapperMock;

    BidListService bidListServiceUnderTest;

    @BeforeEach
    void setUp() {
        bidListMapperMock = new BidListMapperImpl();
        bidListServiceUnderTest = new BidListServiceImpl(bidListRepositoryMock, bidListMapperMock);
    }

    @Test
    void findAllBids() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void findBidListById() {
    }

    @Test
    void delete() {
    }
}