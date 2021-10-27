package com.udacity.jwdnd.course1.cloudstorage.service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {


    private final FileMapper fileMapper;
    private final UserMapper userMapper;


        @Value("${upload.path}")
        private String uploadPath ="starter/cloudstorage/src/main/resources/updates";

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    /*Create a directory for the uploaded files*/
        @PostConstruct
        public void init() {
            try {
                Files.createDirectories(Paths.get(uploadPath));
                System.out.println("upload path created");

            } catch (IOException e) {
                throw new RuntimeException("Could not create upload folder!");
            }
        }

        /* Upload method */
        public int upload(FileModel fileModel,MultipartFile multipartFile) throws IOException{
            try {
                System.out.println("inside the service layer first setting userID : " + fileModel.getUserId());
                fileModel.setFileName(multipartFile.getOriginalFilename());
                System.out.println("inside the service layer  setting uploaded fileName : " + fileModel.getFileName());
                fileModel.setContentType(multipartFile.getContentType());
                fileModel.setFileSize(Long.toString(multipartFile.getSize()));
                fileModel.setFileData(multipartFile.getBytes());
            }catch (FileAlreadyExistsException fileAlreadyExistsException){
                fileAlreadyExistsException.getLocalizedMessage();
            }
                return fileMapper.upload(fileModel);
        }


    /* if the file is already uploaded error message */
    public boolean isAlreadyUploaded(MultipartFile fileToUpload){
        System.out.println(fileMapper.getFile(fileToUpload.getOriginalFilename()));
            return fileMapper.getFile(fileToUpload.getOriginalFilename()) != null;

    }

        /* SELECT BY FILENAME */
        public Resource load(String fileName) {
            try {
                Path file = Paths.get(uploadPath)
                        .resolve(fileName);
                Resource resource = new UrlResource(file.toUri());

                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Could not read the file!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }

        /* DELETE THE FILE */
        public void deleteAll() {
            FileSystemUtils.deleteRecursively(Paths.get(uploadPath)
                    .toFile());
        }

        public List<Path> loadAll() {
            try {
                Path root = Paths.get(uploadPath);
                if (Files.exists(root)) {
                    return Files.walk(root, 1)
                            .filter(path -> !path.equals(root))
                            .collect(Collectors.toList());
                }

                return Collections.emptyList();
            } catch (IOException e) {
                throw new RuntimeException("Could not list the files!");
            }
        }

}
