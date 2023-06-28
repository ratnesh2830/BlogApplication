package com.api.BlogApplication.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImage(String path, MultipartFile multipartFile,int postId) throws IOException;
    InputStream getImage(String path,String imageName) throws FileNotFoundException;
}
