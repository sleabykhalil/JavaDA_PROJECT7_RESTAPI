package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class BidListController {
    // TO DO: Inject Bid service
    @Autowired
    BidListService bidListService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/bidList/list")
    public String getBidList(Model model, Principal user) {
        // TO DO: call service find all bids to show to the view
        String userInfo = myUserDetailsService.getUserInfo(user);
        model.addAttribute("loggedUser", userInfo);
        List<BidList> bidLists = bidListService.findAllBids();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validateBidList(@Valid BidList bid, BindingResult result, Model model) {
        // TO DO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListService.add(bid);
            log.info("bid added id=[{}]", bid.getBidListId());
            return "redirect:/bidList/list";
        } else {
            log.error("bid can not be added id=[{}]", bid.getBidListId());
            return "bidlist/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showBidListUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO DO: get Bid by Id and to model then show to the form
        BidList bidList = bidListService.findBidListById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        // TO DO: check required fields, if valid call service to update Bid and return list Bid
        if (!result.hasErrors()) {
            bidListService.update(bidList);
            log.info("bid updated Id=[{}]", id);
            return "redirect:/bidList/list";
        } else {
            log.error("bid can not be updated Id=[{}]", id);
            return "bidList/update";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TO DO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
