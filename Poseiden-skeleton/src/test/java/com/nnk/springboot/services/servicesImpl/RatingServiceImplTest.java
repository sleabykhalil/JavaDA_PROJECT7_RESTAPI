package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {
    @Mock
    RatingRepository ratingRepositoryMock;

    RatingService ratingServiceUnderTest;

    @BeforeEach
    void setUp() {
        ratingServiceUnderTest = new RatingServiceImpl(ratingRepositoryMock);
    }

    @Test
    void findAllRating() {
        //given
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        when(ratingRepositoryMock.findAll()).thenReturn(ratingList);
        //when
        List<Rating> result = ratingServiceUnderTest.findAllRating();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }


    @Test
    void add() {
        //given
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);
        //when
        Rating result = ratingServiceUnderTest.add(rating);
        //then
        assertThat(result.getSandPRating()).isEqualTo(rating.getSandPRating());
    }

    @Test
    void findById_whenFound_ReturnRating() {
        //given
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
        when(ratingRepositoryMock.findById(1)).thenReturn(Optional.of(rating));
        //when
        Rating result = ratingServiceUnderTest.findById(1);
        //then
        assertThat(result.getId()).isEqualTo(result.getId());
    }

    @Test
    void findById_whenNotFound_ReturnNull() {
        //given
        when(ratingRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //when
        Rating result = ratingServiceUnderTest.findById(1);
        //then
        assertThat(result).isNull();
    }

    @Test
    void update() {
        //given
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);
        //when
        Rating result = ratingServiceUnderTest.update(rating);
        //then
        assertThat(result.getId()).isEqualTo(rating.getId());
    }
}