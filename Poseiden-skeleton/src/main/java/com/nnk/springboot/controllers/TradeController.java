package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.MyUserDetailsService;
import com.nnk.springboot.services.TradeService;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TradeController {
    // TO DO: Inject Trade service
    @Autowired
    TradeService tradeService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @RequestMapping("/trade/list")
    public String getTradeList(Model model, Principal user) {
        // TO DO: find all Trade, add to model
        String userInfo = myUserDetailsService.getUserInfo(user);
        model.addAttribute("loggedUser", userInfo);
        List<Trade> tradeList = new ArrayList<>();
        tradeList = tradeService.findAll();
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TO DO: check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            trade = tradeService.add(trade);
            log.info("Trade added  id=[{}]", trade.getTradeId());
            return "redirect:/trade/list";
        } else {
            log.error("trade can not be added Account=[{}]", trade.getAccount());
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO DO: get Trade by Id and to model then show to the form
        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        // TO DO: check required fields, if valid call service to update Trade and return Trade list
        if (!result.hasErrors()) {
            tradeService.update(trade);
            log.info("Trade updated  id=[{}]", id);
            return "redirect:/trade/list";
        } else {
            log.error("Trade can not be update id=[{}]", id);
            return "trade/update";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TO DO: Find Trade by Id and delete the Trade, return to Trade list
        tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
