package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final UserService userService;

    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginTemplate(){
        return "welcome";

    }

    @PostMapping
    public String homeTemplate(@ModelAttribute User user, Model model){
        String parameter = null;
        System.out.println("username : " + user.getUsername() + " UserID " + user.getUserId());
        if(userService.isUserNameAvailable(user.getUsername())){
            parameter = "The username is not registered please sign up !";
        }
        if (parameter == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                parameter = "There was an error log you in. Please try again.";
            }
        }
        if (parameter == null) {
            model.addAttribute("error", true);
            return "home";
        } else {
            model.addAttribute("logout", parameter);
            return "welcome";
        }

    }
}