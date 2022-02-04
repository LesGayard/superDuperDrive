package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final HashService hashService;
    private final EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, UserService userService, HashService hashService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.hashService = hashService;
        this.encryptionService = encryptionService;

    }

    /* CREDENTIAL CREATION */
    public int creationCredential (String url, String username,String key, String password, Authentication authentication){
        System.out.println("credentials service creation layer ok ");
        /* The password must be crypted */

        CredentialModel credentialModel = new CredentialModel();

        credentialModel.setUrl(url);
        credentialModel.setUsername(username);
        credentialModel.setKey(key);
        System.out.println("before the setter key : " + key);
        System.out.println("after setter the key : " + credentialModel.getKey());
        credentialModel.setPassword(password);

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

    /* DELETE THE CREDENTIAL BY ITS ID */
    public int deleteCredential(Integer credentialid){
        return this.credentialMapper.deleteCredential(credentialid);
    }

    public Boolean isCredentialDeleted(Integer credentialid){
        Boolean result = false;
        if(this.credentialMapper.deleteCredential(credentialid) != null){
            result = true;
        }
        return result;
    }

    /* UPDATE EDIT THE CREDENTIAL */
    public int editCredential (CredentialModel credentialModel,String url, String username,String key, String password,Integer credentialid) {
        /* Decrypt the password */

        return this.credentialMapper.updateCredentialId(url, username,key, password,credentialid);
    }


    /* GET THE KEY FOR THE PASSWORD */

    /* Encrypted password */

    /* Decrypted Password */

}
