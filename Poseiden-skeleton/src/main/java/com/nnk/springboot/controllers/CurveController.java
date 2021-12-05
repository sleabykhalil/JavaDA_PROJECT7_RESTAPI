package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class CurveController {
    // TO DO: Inject Curve Point service
    @Autowired
    CurvePointService curvePointService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/curvePoint/list")
    public String getCurvePoint(Model model, Principal user) {
        // done TO DO: find all Curve Point, add to model
        String userInfo = myUserDetailsService.getUserInfo(user);
        model.addAttribute("loggedUser", userInfo);
        List<CurvePoint> curvePointList = curvePointService.findAllCurvePoint();
        model.addAttribute("curvePointList", curvePointList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping(value = "/curvePoint/validate")
    public String validateCurvePoint(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // done TO DO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            curvePointService.add(curvePoint);
            log.info("CurvePoint added Curve id=[{}]", curvePoint.getCurveId());
            return "redirect:/curvePoint/list";
        } else {
            log.error("CurvePoint can not be added id=[{}]", curvePoint.getCurveId());
            return "curvePoint/add";
        }

    }

    @GetMapping("/curvePoint/update/{id}")
    public String showCurvePointUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO DO: get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {
        // TO DO: check required fields, if valid call service to update Curve and return Curve list
        if (!result.hasErrors()) {
            curvePointService.update(curvePoint);
            log.info("CurvePoint updated Curve id=[{}]", id);
            return "redirect:/curvePoint/list";
        } else {
            log.error("CurvePoint can not be update id=[{}]", id);
            return "curvePoint/update";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        // TO DO: Find Curve by Id and delete the Curve, return to Curve list
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
