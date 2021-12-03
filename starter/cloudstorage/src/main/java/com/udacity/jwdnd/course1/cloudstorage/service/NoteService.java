package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;
    private ArrayList<NoteModel> allNoteModels;

    public NoteService(NoteMapper noteMapper, ArrayList<NoteModel> allNoteModels, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
        this.allNoteModels = new ArrayList<NoteModel>();
    }

    // find the note
    public NoteModel getNote(String noteTitle){
        return noteMapper.getNote(noteTitle);
    }

    //Insert
    // Creation: On successful note creation, the user should be shown a success message and the created note should appear in the list.
    public int insertNote (String noteTitle, String noteDescription, Authentication authentication){
        NoteModel noteModel = new NoteModel();

        try {
            noteModel.setNotetitle(noteTitle);
            System.out.println("inside the file service layer  setting uploaded Note title : " + noteModel.getNotetitle());
            noteModel.setNotedescription(noteDescription);

            Integer userId = getUserId(authentication);
            noteModel.setUserId(userId);
        }catch (Exception exception){
            exception.getLocalizedMessage();
        }
        System.out.println("test Insert note service layer method ! ");
        return this.noteMapper.insertNote(noteModel);
    }

    public boolean isAlreadyAdded(MultipartFile noteToAdd){
        return this.noteMapper.getNote(noteToAdd.getOriginalFilename()) != null;
    }

    /* VIEW NOTE BY ITS ID */
    public NoteModel viewNoteById(int noteId){return this.noteMapper.viewNoteById(noteId);}

    /* VIEW NOTES BY USER ID */
    public ArrayList<NoteModel>viewNotesByUserId(Integer userId){
        ArrayList<NoteModel> result = this.viewNotesByUserId(userId);
        return result;
    }

    //Update
    public int updateNoteId(int noteId){
        return noteMapper.updateNoteId(noteId);
    }

    //Delete
    public int deleteNote(int noteId){
        return noteMapper.deleteNote(noteId);
    }

    /* FIND THE USERID*/
    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
