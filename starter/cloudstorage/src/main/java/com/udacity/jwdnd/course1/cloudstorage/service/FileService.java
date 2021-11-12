package com.udacity.jwdnd.course1.cloudstorage.service;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {


    private final FileMapper fileMapper;
    private final UserMapper userMapper;
    private static String UPLOADED_FOLDER = "starter/cloudstorage/src/main/resources/updates";


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
        public int upload(MultipartFile multipartFile) throws IOException{
            FileModel fileModel = new FileModel();
            // Get the file and save it somewhere
            byte[] bytes = multipartFile.getBytes();
            try {
                /* GET THE FILE TO SAVE FROM ITS PATH*/
                Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
                Files.write(path, bytes);

                System.out.println("writing the file");
                System.out.println("file uploaded : " + multipartFile.getOriginalFilename());

                fileModel.setFilename(multipartFile.getOriginalFilename());
                System.out.println("inside the file service layer  setting uploaded filename : " + fileModel.getFilename());
                fileModel.setContenttype(multipartFile.getContentType());
                fileModel.setFilesize(Long.toString(multipartFile.getSize()));
                //fileModel.setUserId(this.userMapper.getUserById(userId));
                fileModel.setFiledata(multipartFile.getBytes());



            }catch (FileAlreadyExistsException fileAlreadyExistsException){
                fileAlreadyExistsException.getLocalizedMessage();
            }
                return this.fileMapper.upload(fileModel);
        }


    /* if the file is already uploaded error message */
    public boolean isAlreadyUploaded(MultipartFile fileToUpload){
        System.out.println(" Test if this file is already uploaded : " + fileMapper.getFile(fileToUpload.getOriginalFilename()));
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

        /* VIEW THE FILES */
        public ArrayList<String>viewFiles(String fileName){
            return fileMapper.viewFiles(fileName);
        }

        /* SELECT FILES BY ID */
        public FileModel viewFileById(int id){
            return fileMapper.viewFileById(id);
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
