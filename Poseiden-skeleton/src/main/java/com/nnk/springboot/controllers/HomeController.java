package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }

    @GetMapping("/error")
    public String error(Model model) {
        //ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        model.addAttribute("errorMsg", errorMessage);
        //mav.setViewName("app/403");
        return "redirect:app/403";
    }

}
