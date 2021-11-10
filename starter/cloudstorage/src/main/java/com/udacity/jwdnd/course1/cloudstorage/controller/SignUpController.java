package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupTemplate(){
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){

        String signupError = null;

        int rowNewUser = 0;

        try{
            if(!userService.isUserNameAvailable(user.getUsername())) {
                signupError = "The username already exist !";
            }
        }catch (TooManyResultsException ex) {
            ex.printStackTrace();
            signupError = "The username already exist !";
        }

        if (signupError == null) {
            rowNewUser = userService.createUser(user);
            System.out.println("test : "+ rowNewUser);
            if (rowNewUser < 0) {
                signupError = "There was an error signing you up. Please try again.";
                }
        }

        if (signupError == null) {

            model.addAttribute("signupSuccess", true);
            System.out.println("row new user : " + rowNewUser);
            System.out.println("username: " + user.getUsername());
            System.out.println("firstname: " + user.getFirstname() + " lastname : " + user.getLastname());


        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
