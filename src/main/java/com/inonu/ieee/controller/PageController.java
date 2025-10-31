package com.inonu.ieee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.service.abstracts.IImageService;
import com.inonu.ieee.service.abstracts.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pages")
public class PageController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private IPageService pageService;
    @Autowired
    private IImageService imageService;

    @PostMapping("")
    public ResponseEntity<PageDto> createPage(@RequestPart("page") String pageJson, @RequestPart("image") MultipartFile image) throws JsonProcessingException {

        CreatePageDto page = objectMapper.readValue(pageJson, CreatePageDto.class);
        String imageName = imageService.uploadImage(image);
        page.setImage(imageName);

        PageDto response = pageService.createPage(page);

        imageName = response.getImage();
        String url = null;
        if (imageName != null && !imageName.isEmpty()) {
            url = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("api/v1/images/")
                    .path(imageName)
                    .toUriString();
        }

        response.setImage(url);

        return ResponseEntity.ok(response);

    }

}
