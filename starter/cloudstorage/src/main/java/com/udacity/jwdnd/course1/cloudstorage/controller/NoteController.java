package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/home")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    @Autowired
    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    // Get request for seeing the notes

    //Post request for adding a note
    @PostMapping("/noteToAdd")
    public String addNote(@RequestParam("noteTitle") String noteTitle ,@RequestParam("noteDescription") String noteDescription, Model model, @ModelAttribute("NoteModel") NoteModel noteModel,Authentication authentication){
        System.out.println("test Note controller add a note ! ");
        int userId = getUserId(authentication);

       if(noteTitle != null){
           int noteId = this.noteService.insertNote(noteTitle,noteDescription,authentication);
           System.out.println("inserted ok");
           System.out.println("note title : "+ noteTitle);
           System.out.println("note content : "+ noteDescription);

          model.addAttribute("NoteModel", this.noteService.viewNoteById(noteId));
       }else{
           System.out.println("note already exists !!");
       }

        System.out.println("test post note");


        return"redirect:/home";
    }

    /* FIND THE USERID*/
    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
