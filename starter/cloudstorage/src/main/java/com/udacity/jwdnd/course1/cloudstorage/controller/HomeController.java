package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;



    @GetMapping
    public String HomeTemplate(Model model, Authentication authentication){
        System.out.println("ultimate");
        System.out.println("logInOk");
        String param = null;
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            System.out.println("Principal if username : " + username);
        } else {
            username = principal.toString();
            System.out.println("Principal else username : " + username);
        }

        User user = userService.getUser(username);
        System.out.println("user ! " + user);

        if(user == null || username == null){
            System.out.println("User or username null !!");
            param = "error !!";
            model.addAttribute("param.error", true);
            return "home";
        }else{
            Integer userId = getUserId(authentication);
            System.out.println("userID : " + userId);
            return "home";
        }

    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
