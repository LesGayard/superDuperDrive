package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private ArrayList<Note> allNotes;

    public NoteService(NoteMapper noteMapper, ArrayList<Note> allNotes) {
        this.noteMapper = noteMapper;
        this.allNotes = new ArrayList<Note>();
    }

    // find the note
    public Note getNote(String noteTitle){
        return noteMapper.getNote(noteTitle);
    }

    //Insert
    // Creation: On successful note creation, the user should be shown a success message and the created note should appear in the list.
    public int insertNote (Note note){
        allNotes.add(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
        System.out.println("Note Added in the list" + allNotes.size());
        return noteMapper.insertNote(new Note(null, note.getNoteTitle(), note.getNoteDescription(),note.getUserId()));
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
