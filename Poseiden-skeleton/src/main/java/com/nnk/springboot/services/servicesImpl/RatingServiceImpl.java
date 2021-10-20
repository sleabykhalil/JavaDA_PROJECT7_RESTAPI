package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    /**
     * Add rating
     *
     * @param rating
     * @return
     */
    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * find Rating by id
     *
     * @param id
     * @return
     */
    @Override
    public Rating findById(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            log.debug("Rating found id=[{}]", id);
            return rating.get();
        } else {
            log.debug("Rating can not found id=[{}]", id);
            return null;
        }
    }

    /**
     * Update Rating
     *
     * @param rating
     * @return
     */
    @Override
    public Rating update(Rating rating) {
        Rating ratingAfterUpdate = ratingRepository.save(rating);
        log.debug("Rating updated id=[{}]", ratingAfterUpdate.getId());
        return ratingAfterUpdate;
    }

    @Override
    public void delete(Integer id) {
        if (ratingRepository.findById(id).isPresent()) {
            ratingRepository.deleteById(id);
            log.debug("rating deleted id=[{}]", id);
        } else {
            log.debug("rating not found =[{}]", id);
        }
    }
}
