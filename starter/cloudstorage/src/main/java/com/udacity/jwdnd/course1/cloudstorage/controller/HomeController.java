package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String logOut(){
        return "home";
    }

    @GetMapping
    public String HomeTemplate(Model model, Authentication authentication){
        /*String username = authentication.getName();
        User user = userService.getUser(username);

        if(user == null || username == null){
            System.out.println("User or username null !!");
            return "welcome";
        }else{
            int userId = user.getUserId();
            System.out.println("userID : " + userId);
            return "home";
        }*/
        return "home";
    }


}
