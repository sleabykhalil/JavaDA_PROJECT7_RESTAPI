package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.MyUserDetailsService;
import com.nnk.springboot.services.RuleNameService;
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
import java.util.List;

@Slf4j
@Controller
public class RuleNameController {
    // TO DO: Inject RuleName service
    @Autowired
    RuleNameService ruleNameService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @RequestMapping("/ruleName/list")
    public String getRuleName(Model model, Principal user) {
        // TO DO: find all RuleName, add to model
        String userInfo = myUserDetailsService.getUserInfo(user);
        model.addAttribute("loggedUser", userInfo);
        List<RuleName> ruleNameList = ruleNameService.findAllRuleName();
        model.addAttribute("ruleNameList", ruleNameList);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TO DO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.add(ruleName);
            log.info("RuleName added  id=[{}]", ruleName.getId());
            return "redirect:/ruleName/list";
        } else {
            log.error("RuleName can not be added id=[{}]", ruleName.getName());
            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO DO: get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // TO DO: check required fields, if valid call service to update RuleName and return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.update(ruleName);
            log.info("CurvePoint updated Curve id=[{}]", id);
            return "redirect:/ruleName/list";
        } else {
            log.error("CurvePoint can not be update id=[{}]", id);
            return "ruleName/update";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TO DO: Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}
