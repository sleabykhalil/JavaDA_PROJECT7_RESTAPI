package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user"
        , password = "123456")
class RatingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RatingRepository ratingRepository;

    @Test
    void getRatingList() throws Exception {
        mockMvc.perform(get("/rating/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void validateRating() throws Exception {
        Rating rating = ratingRepository.save(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        mockMvc.perform(post("/rating/validate")
                        .flashAttr("rating", rating))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void showUpdateForm() throws Exception {
        Rating rating = ratingRepository.save(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        mockMvc.perform(get("/rating/update/{id}", rating.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateRating() throws Exception {
        Rating rating = ratingRepository.save(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        Rating newRating = ratingRepository.save(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        mockMvc.perform(post("/rating/update/{id}", rating.getId().toString())
                        .flashAttr("rating", newRating))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void deleteRating() throws Exception {
        Rating rating = ratingRepository.save(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        mockMvc.perform(get("/rating/delete/{id}", rating.getId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
        Optional<Rating> result = ratingRepository.findById(rating.getId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }
}