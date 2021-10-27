package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    /* FOREIGN KEY FOR THE USER NEEDED*/
    @Select("SELECT * FROM NOTES WHERE username = #{userId}")
    Note getNote(String noteTitle);


    /*Creation: On successful note creation, the user should be shown a success message and the created note should appear in the list.*/
    @Insert("INSERT INTO NOTES (noteTitle, noteDescription) VALUES (#{noteTitle},#{noteDescription}) WHERE username = #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Update("UPDATE NOTES SET (noteTitle, noteDescription) VALUES (#{noteTitle},#{noteDescription}) WHERE  noteId = #{noteId} AND username = #{userId}")
    int updateNoteId(int noteId);

    @Delete ("DELETE FROM NOTES WHERE username = #{userId} AND noteId = #{noteId}")
    int deleteNote(int noteId);


}
