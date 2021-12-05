package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import com.nnk.springboot.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    MyUserService myUserService;
    //private MyUserRepository userRepository;
    @Autowired
    MyUserRepository userRepository;

    // @RolesAllowed("ADMIN")
    @RequestMapping("/user/list")
    public String home(Model model) {
        List<MyUser> users = myUserService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(MyUser myUser) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid MyUser myUser, BindingResult result, Model model) {
        if (myUserService.findUserByUsername(myUser.getUsername()) != null) {
            ObjectError error = new ObjectError("globalError", "User " + myUser.getUsername() + " exist pales change user name");
            result.addError(error);
        }
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            myUser.setPassword(encoder.encode(myUser.getPassword()));
            myUserService.add(myUser);
            return "redirect:/";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        MyUser user = myUserService.findById(id);
        user.setPassword("");
        model.addAttribute("myUser", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid MyUser user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //user.setId(id);
        myUserService.update(user);
        //model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        myUserService.delete(id);
        return "redirect:/user/list";
    }

    @GetMapping("/secure/article-details")
    public String getAllUserArticles(Model model) {
        return "redirect:/user/list";
    }
}
