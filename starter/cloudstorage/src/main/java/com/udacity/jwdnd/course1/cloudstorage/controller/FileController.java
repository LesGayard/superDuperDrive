package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;


@Controller
@RequestMapping("/result")
public class FileController {

    private final FileService fileService;
    private final UserService userService;


    @Autowired
    public FileController(FileService fileService, UserService userService,ArrayList<String>files) {
        this.fileService = fileService;
        this.userService = userService;

    }


    /* UPLOAD THE FILES AND PUT THEM INTO AN ARRAYLIST TO BE DISPLAYED */
    @PostMapping
    public String uploadFile(@RequestParam("originalFilenameUpload") @ModelAttribute("FileModel") MultipartFile file, Model model, FileModel fileModel,Authentication authentication) {
        System.out.println("test file upload in the file controller !!");

        /* FIND THE ID */
        Integer userId = getUserId(authentication);
        /* If the file doesn't exist*/
        if (file.isEmpty()) {
            model.addAttribute("errorNotSaved", "errorNotSaved");
            return "result";
        }
        // if file not empty
        try {
            System.out.println("try catch uploading");
            /* if the file is already uploaded error message */
            if(this.fileService.isAlreadyUploaded(file)){

                model.addAttribute("errorExample", "errorExample");
                System.out.println("file already exist !!");

            }else {
                /* CALL THE SERVICE LAYER */
                int serviceUpload = this.fileService.upload(file, authentication);
                System.out.println("the upload service layer !! : " + serviceUpload);

                /* Take the arrayList !!*/
                model.addAttribute("success", "success");
                /*ArrayList<String> test = this.fileService.viewFiles(file.getOriginalFilename());
                System.out.println("Array list test : " + test);*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "result";

    }

    /* FIND THE USERID*/
    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }

}
