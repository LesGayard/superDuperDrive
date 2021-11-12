package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@Controller
@RequestMapping("/result")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    private ArrayList<String>files;

    @Autowired
    public FileController(FileService fileService, UserService userService,ArrayList<String>files) {
        this.fileService = fileService;
        this.userService = userService;
        this.files = new ArrayList<String>();
    }


    /* UPLOAD THE FILES AND PUT THEM INTO AN ARRAYLIST TO BE DISPLAYED */
    @PostMapping
    public String uploadFile(@RequestParam("originalFilenameUpload") @ModelAttribute("FileModel") MultipartFile file, Model model, FileModel fileModel,Authentication authentication) {
        System.out.println("test file upload in the file controller !!");

        /* FIND THE ID */

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
                System.out.println("The array List before the work  : " + this.files.size());
                this.files.add(file.getOriginalFilename());
                System.out.println("The array List after the work  : " + this.files.size() + "file added : " + file.getOriginalFilename());

                model.addAttribute("success", "success");
                model.addAttribute("uploadedFiles", "test");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "result";


    }



}
