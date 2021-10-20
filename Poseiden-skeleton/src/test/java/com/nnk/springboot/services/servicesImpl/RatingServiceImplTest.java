package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {
    @Mock
    RatingRepository ratingRepository;

    RatingService ratingServiceUnderTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllRating() {
    }
}