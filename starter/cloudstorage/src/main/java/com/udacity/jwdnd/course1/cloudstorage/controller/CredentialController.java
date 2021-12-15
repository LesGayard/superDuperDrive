package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping(value ="/home")
public class CredentialController {

    @Autowired
    private final CredentialService credentialService;

    private final HashService hashService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, HashService hashService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/credentials")
    public String creationCredentials( String key,Integer credentialId, Model model, @ModelAttribute("CredentialModel") CredentialModel credentialModel, @RequestParam("url") String url, @RequestParam("username") String username, @RequestParam("password") String password, Authentication authentication) {
        /* The password must be crypted */


       if(credentialId == null){
           System.out.println("credential creation controller");

           this.credentialService.creationCredential(url, username, key, password, authentication);
          System.out.println("url entered : " + url);
          System.out.println("username : " + username);
          System.out.println("encoded key :  " + key);
          System.out.println("password : " + password);


       }else{
           System.out.println("Credentials edition update !!");
           System.out.println("password before mofidication  " + password);

           /* Decrypt the password */
           System.out.println("decrypt password : " + password);

           this.credentialService.editCredential(credentialModel,url,username,key, password,credentialId);
           System.out.println("update layer password :  " + password);
       }
        return "redirect:/home";
    }


}
