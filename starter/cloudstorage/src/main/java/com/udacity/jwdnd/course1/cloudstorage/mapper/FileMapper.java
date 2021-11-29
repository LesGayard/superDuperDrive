package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.core.io.Resource;

import java.util.ArrayList;


@Mapper
public interface FileMapper {

    /* FIND THE FILE */
    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    FileModel getFile(String filename);

    /* UPLOAD THE FILE*/
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userId, filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userId},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int upload (FileModel fileModel);

    /* DOWNLOAD THE FILE */
    @Select("SELECT * FROM FILES WHERE username = #{username} AND filename = #{filename}")
    Resource load(String filename);

    /* FIND THE FILE FOR A LIST IN THE VIEW FROM THE FILENAME */
    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userId = #{userId}")
    ArrayList<String>viewFilesByFilename(String filename);

    /* FIND THE FILE FOR A LIST IN THE VIEW FROM THE USERID*/
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    ArrayList<String> viewFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    ArrayList<FileModel> viewFileModelsByUserId(Integer userId);

    /* FIND THE FILE FROM ITS ID */
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileModel viewFileById(int id);

    /* DELETE THE FILE FROM ITS ID */
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
     Integer deleteFilesByFileId(Integer fileId);



}
