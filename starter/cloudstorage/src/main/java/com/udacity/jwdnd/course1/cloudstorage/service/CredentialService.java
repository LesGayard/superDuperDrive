package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final HashService hashService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, HashService hashService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.hashService = hashService;
    }

    /* CREDENTIAL CREATION */
    public int creationCredential (String url, String username, String password, Authentication authentication){
        System.out.println("credentials service creation layer ok ");

        /* The password must be crypted */
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setUrl(url);
        credentialModel.setUsername(username);
        //credentialModel.setKey(key);
        credentialModel.setPassword(hashedPassword);

        Integer userId = getUserId(authentication);
        credentialModel.setUserid(userId);
        return this.credentialMapper.createCredential(credentialModel);
    }

    /* BOOLEAN IS CREATED */
    public Boolean isCredentialCreated (String url){
        Boolean result = false;
        if(this.credentialMapper.getCredential(url) != null){
            result = true;
        }
        return result;
    }

    /* VIEW THE CREDENTIALS BY USERID */
    public ArrayList<CredentialModel> viewCredentialModelsByUserId(Integer userId) {
        ArrayList<CredentialModel> result = this.credentialMapper.viewCredentialModelsByUserId(userId);
        return result;
    }

    /* FIND THE USERID*/
    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
