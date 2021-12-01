package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private ArrayList<NoteModel> allNoteModels;

    public NoteService(NoteMapper noteMapper, ArrayList<NoteModel> allNoteModels) {
        this.noteMapper = noteMapper;
        this.allNoteModels = new ArrayList<NoteModel>();
    }

    // find the note
    public NoteModel getNote(String noteTitle){
        return noteMapper.getNote(noteTitle);
    }

    //Insert
    // Creation: On successful note creation, the user should be shown a success message and the created note should appear in the list.
    public int insertNote (NoteModel noteModel){
        System.out.println("test Insert note service layer method ! ");
        return noteMapper.insertNote(new NoteModel(null, noteModel.getNotetitle(), noteModel.getNotedescription(), noteModel.getUserId()));
    }

    //Update
    public int updateNoteId(int noteId){
        return noteMapper.updateNoteId(noteId);
    }

    //Delete
    public int deleteNote(int noteId){
        return noteMapper.deleteNote(noteId);
    }
}
