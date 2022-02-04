package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



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
    public String addNote( Integer noteId, @RequestParam("noteTitle") String noteTitle ,@RequestParam("noteDescription") String noteDescription, Model model, @ModelAttribute("NoteModel") NoteModel noteModel,Authentication authentication){
        System.out.println("test Note controller add a note ! ");
        int userId = getUserId(authentication);

        Integer noteUpdate = null;

      if(noteId == null){
          System.out.println("must insert a note !!");
          this.noteService.insertNote(noteTitle,noteDescription,authentication);
          System.out.println("test after insertion");
          System.out.println(noteTitle);
      }else {
          System.out.println("must update the note");
          this.noteService.updateNoteId(noteTitle,noteDescription,noteId);
          System.out.println("test after update : " );
      }

        return "redirect:/home";
    }

    /* FIND THE USERID*/
    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
