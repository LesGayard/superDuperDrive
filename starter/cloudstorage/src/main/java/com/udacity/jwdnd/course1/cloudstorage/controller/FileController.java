package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        //System.out.println("test file upload in the file controller !!");

        /* FIND THE ID */
        Integer userId = getUserId(authentication);
        /* If the file doesn't exist*/
        if (file.isEmpty()) {
            model.addAttribute("errorNotSaved", "Your changes were not saved.");
            return "result";
        }
        // if file not empty
        try {
            System.out.println("try catch uploading");
            /* if the file is already uploaded error message */
            if(this.fileService.isAlreadyUploaded(file)){

                model.addAttribute("errorExample", "The file is already uploaded. Please choose another file.");
                //System.out.println("file already exist !!");
            }else {
                /* CALL THE SERVICE LAYER */
                int serviceUpload = this.fileService.upload(file, authentication);
                //System.out.println("the upload service layer !! : " + serviceUpload);
                /* Take the arrayList !!*/
                model.addAttribute("success", "Your changes were successfully saved");
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
