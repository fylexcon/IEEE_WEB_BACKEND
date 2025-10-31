package com.inonu.ieee.service.abstracts;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

    public String uploadImage(MultipartFile file);
    public UrlResource getImage(String fileName);
    public void deleteImage(String path);
}
