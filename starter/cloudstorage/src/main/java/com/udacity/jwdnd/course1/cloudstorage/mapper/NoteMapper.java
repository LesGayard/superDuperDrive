package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {

    /* FOREIGN KEY FOR THE USER NEEDED*/
    @Select("SELECT * FROM NOTES WHERE notetitle = #{notetitle}")
    NoteModel getNote(String noteTitle);

    /* VIEW NOTE BY ITS ID*/
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    NoteModel viewNoteById(int noteid);

    /* VIEW NOTE BY USER ID */
    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    ArrayList<String> viewNotesByUserId(Integer userId);

    /*Creation: On successful note creation, the user should be shown a success message and the created note should appear in the list.*/
    @Insert("INSERT INTO NOTES (noteTitle, notedescription,userId) VALUES (#{notetitle},#{notedescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(NoteModel noteModel);

    @Update("UPDATE NOTES SET (notetitle, notedescription) VALUES (#{noteTitle},#{notedescription}) WHERE  noteid = #{noteid} AND userId = #{userId}")
    int updateNoteId(int noteid);

    @Delete ("DELETE FROM NOTES WHERE userId = #{userId} AND noteid = #{noteid}")
    int deleteNote(int noteid);


}
