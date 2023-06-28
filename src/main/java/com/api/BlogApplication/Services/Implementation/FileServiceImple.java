package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImple implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile, int postId) throws IOException {
        //image name that you want to store
        String imageName = postId+multipartFile.getOriginalFilename();

        //file path where you want to store this image
        String filepath = path+ File.separator + imageName;

        //create folder if not exist
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Paths.get(filepath));

        return imageName;
    }

    @Override
    public InputStream getImage(String path, String imageName) throws FileNotFoundException {
        String fullPath = path+File.separator+imageName;
        InputStream io=new FileInputStream(fullPath);
        return io;
    }
}
