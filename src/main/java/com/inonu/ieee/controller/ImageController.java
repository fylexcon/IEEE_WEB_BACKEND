package com.inonu.ieee.controller;

import com.inonu.ieee.service.abstracts.IImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @PostMapping("")
    public ResponseEntity<String> fileUpload(@RequestParam(name = "file") MultipartFile file) {

        String path = imageService.uploadImage(file);
        return ResponseEntity.ok(path);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<UrlResource> getFile(@PathVariable String filename, HttpServletRequest request) {

        UrlResource resource = imageService.getImage(filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFilename());
        } catch (Exception ex) {
            throw new RuntimeException("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @DeleteMapping("/{filename:.+}")
    public void deleteFile(@PathVariable String filename) {
        imageService.deleteImage(filename);
    }
}
