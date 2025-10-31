package com.inonu.ieee.service.concreates;

import com.inonu.ieee.service.abstracts.IImageService;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageManager implements IImageService {

    private final Path uploadsLocation = Paths.get("./uploads");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(uploadsLocation);
        } catch (IOException e) {
            throw new RuntimeException("'uploads' directory could not be created.");
        }
    }


    @Override
    public String uploadImage(MultipartFile file) {
        if (file.isEmpty())
            throw new MultipartException("Image field must not be empty!");


        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        if (originalFilename.contains(".."))
            throw new RuntimeException("Invalid file name! The file contains invalid path segments!");

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/"))
            throw new RuntimeException("Invalid file type! The file contains invalid content-type!");


        String fileExtension = "";

        int lastDot = originalFilename.lastIndexOf('.');
        if (lastDot > 0)
            fileExtension = originalFilename.substring(lastDot);

        String uniqFileName = UUID.randomUUID().toString() + fileExtension;

        Path targetLocation = this.uploadsLocation.resolve(uniqFileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return uniqFileName;

        } catch (IOException e) {
            throw new RuntimeException("An error occurred while trying to upload the file!");
        }

    }

    public UrlResource getImage(String fileName) {
        try {
            Path filePath = this.uploadsLocation.resolve(fileName).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());

            if (resource.exists())
                return resource;
            else
                throw new RuntimeException("Image not found.");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Image not found.");
        }
    }

    @Override
    public void deleteImage(String path) {
        try {
            Path filePath = this.uploadsLocation.resolve(path).normalize();

            if (Files.exists(filePath))
                Files.delete(filePath);

            else
                throw new RuntimeException("Image not found.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while trying to delete the file!");
        }
    }
}
