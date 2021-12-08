package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {

    /* CREDENTIAL CREATION */
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int createCredential (CredentialModel credentialModel);

    /* FIND THE CREDENTIAL BY USER ID */
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    ArrayList<CredentialModel> viewCredentialModelsByUserId(Integer userId);

    /* FIND THE CREDENTIAL BY URL */
    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url}")
    CredentialModel getCredential(String url);


}
