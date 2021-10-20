package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RatingService {
    List<Rating> findAllRating();
}
