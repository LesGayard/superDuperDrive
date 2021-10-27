package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.io.Resource;


@Mapper
public interface FileMapper {

    /* FIND THE FILE */
    @Select("SELECT * FROM FILES WHERE username = #{username} AND filename = #{filename}")
    FileModel getFile(String fileName);

    /* UPLOAD THE FILE*/
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int upload (FileModel fileModel);

    /* DOWNLOAD THE FILE */
    @Select("SELECT * FROM FILES WHERE username = #{username} AND filename = #{filename}")
    Resource load(String fileName);




}
