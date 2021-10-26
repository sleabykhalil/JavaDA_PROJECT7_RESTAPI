package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@Controller
public class RatingController {
    // TO DO: Inject Rating service
    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        // TO DO: find all Rating, add to model
        List<Rating> ratingList = ratingService.findAllRating();
        model.addAttribute("ratingList",ratingList);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TO DO: check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingService.add(rating);
            log.info("Rating added  id=[{}]", rating.getId());
            return "redirect:/rating/list";
        } else {
            log.error("Rating can not be added id=[{}]", rating.getId());
            return "rating/add";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO DO: get Rating by Id and to model then show to the form
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        // TO DO: check required fields, if valid call service to update Rating and return Rating list
        if (!result.hasErrors()) {
            ratingService.update(rating);
            log.info("Rating updated  id=[{}]", id);
            return "redirect:/rating/list";
        } else {
            log.error("Rating can not be update id=[{}]", id);
            return "rating/update";
        }
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TO DO: Find Rating by Id and delete the Rating, return to Rating list
        ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
