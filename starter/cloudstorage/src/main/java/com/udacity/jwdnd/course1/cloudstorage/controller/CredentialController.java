package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value ="/home")
public class CredentialController {

    @Autowired
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credentials")
    public String creationCredentials(Model model, @ModelAttribute("CredentialModel") CredentialModel credentialModel, @RequestParam("url") String url, @RequestParam("username") String username, @RequestParam("password") String password, Authentication authentication){

       int credentialId = 0;
       if(credentialId == 0){
           System.out.println("credential creation controller");
          credentialId =  this.credentialService.creationCredential(url, username, password, authentication);
          System.out.println("url entered : " + url);
          System.out.println("username : " + username);
       }
        return "redirect:/home";
    }


}
