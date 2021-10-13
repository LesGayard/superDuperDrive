package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
<<<<<<< HEAD
@RequestMapping("/welcome")
public class Welcome {

    @GetMapping
    public String loginTemplate(){
        return "welcome";
=======
@RequestMapping("/login")
public class LogIn {

    @GetMapping
    public String loginTemplate(){
        return "login";
>>>>>>> github/master
    }
}
