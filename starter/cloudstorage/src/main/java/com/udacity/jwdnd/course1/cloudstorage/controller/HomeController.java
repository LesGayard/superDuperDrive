package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;


    @GetMapping
    public String HomeTemplate(Model model, Authentication authentication, MultipartFile multipartFile, Integer fileId,InputStream inputStream,RedirectAttributes redirect) throws Exception {

        //System.out.println("logInOk");
        String param = null;
        String username = "";
        Integer userId = 0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* CHECK THE RIGHT USER LOGGED IN WITH ITS OWN ID */
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
           // System.out.println("Principal if username : " + username);
        } else {
            username = principal.toString();
           // System.out.println("Principal else username : " + username);
        }

        User user = userService.getUser(username);
        System.out.println("user ! " + user);

        if (user == null || username == null) {
            System.out.println("User or username null !!");
            param = "error !!";
            model.addAttribute("param.error", true);
            return "home";
        } else {
            userId = getUserId(authentication);
            System.out.println("userID : " + userId);
            try{

                if(this.fileService.isFileDelete(fileId) == false){
                    model.addAttribute("FileModel",this.fileService.viewFilesByUserId(userId));
                }


                model.addAttribute("FileModel",this.fileService.viewFilesByUserId(userId));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "home";
    }

    @RequestMapping(value= "{fileId}")
    public String delete(Model model, Authentication authentication, Integer fileId, RedirectAttributes redirect){
        Integer userId = getUserId(authentication);

        if(this.fileService.isFileDelete(fileId) == false){
            redirect.addAttribute("fileId",this.fileService.deleteFilesById(fileId));
        }
        return "redirect:/home";
    }

    @RequestMapping(value= "/view/")
    public ResponseEntity<InputStreamResource> download(
            @RequestParam(required = false, name = "fileId") Integer fileId) {

        FileModel fileModel = this.fileService.viewFileById(fileId);

        String filename = fileModel.getFilename();
        String contentType = fileModel.getContenttype();

        byte[] fileData = fileModel.getFiledata();

        InputStream inputStream = new ByteArrayInputStream(fileData);

        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }

}
