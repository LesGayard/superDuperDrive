package com.udacity.jwdnd.course1.cloudstorage.controller;



import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/result")
public class FileController {

    private final FileService fileService;
    private final UserService userService;
    private static String UPLOADED_FOLDER = "src/main/resources/";

    @Autowired
    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("originalFilenameUpload") @ModelAttribute("FileModel") MultipartFile file, Model model, FileModel fileModel, Authentication authentication) {


        System.out.println("test file upload controller !!");

        if (file.isEmpty()) {
            model.addAttribute("errorNotSaved", "errorNotSaved");
            return "result";
        }
        // if file not empty
        try {
            System.out.println("try catch uploading");
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            /* if the file is already uploaded error message */
            if(bytes.equals(bytes)){
                model.addAttribute("errorExample","errorExample");
                System.out.println("file already exist !!");
            }else{
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                // if the file already exists -> error
                Files.write(path, bytes);
                System.out.println("writing the file");
                System.out.println("file uploaded : "+  file.getOriginalFilename());

                /* CALL THE SERVICE LAYER */
                int serviceUpload = fileService.upload(fileModel,file);
                System.out.println("the service layer !! : "+ serviceUpload);
                model.addAttribute("success", "success");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "result";
    }






}
