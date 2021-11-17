package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/" )
public class ResultController {
    private final FileService fileService;

    public ResultController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String displayFilesUploaded(Model model, MultipartFile multipartFile) throws Exception{
        String result = "";
        try{
            if(fileService.isAlreadyUploaded(multipartFile) == true){
                model.addAttribute("success", true);
                System.out.println("display in home");
            }
        }catch(Exception ex){
            ex.getLocalizedMessage();
        }
        return "home";
    }
}
