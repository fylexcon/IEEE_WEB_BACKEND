package com.inonu.ieee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.service.abstracts.IImageService;
import com.inonu.ieee.service.abstracts.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPageService pageService;

    @Autowired
    private IImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<PageDto> GetPageById(@PathVariable(name = "id") UUID id) {

        PageDto page = pageService.getPageById(id);
        return ResponseEntity.ok(page);
    }

    @PostMapping("")
    public ResponseEntity<PageDto> createPage(@RequestPart("page") String pageJson, @RequestPart("image") MultipartFile image) throws JsonProcessingException {

        CreatePageDto page = objectMapper.readValue(pageJson, CreatePageDto.class);
        String imageName = imageService.uploadImage(image);
        page.setImage(imageName);

        PageDto response = pageService.createPage(page);

        imageName = response.getImage();
        String url = null;
        if (imageName != null && !imageName.isEmpty()) {
            url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/images/").path(imageName).toUriString();
        }

        response.setImage(url);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PageDto> updatePage(@PathVariable(name = "id") UUID id, @RequestPart("page") String pageJson,
                                              @RequestPart(value = "image", required = false) MultipartFile image) throws JsonProcessingException {

        CreatePageDto page = objectMapper.readValue(pageJson, CreatePageDto.class);

        PageDto response = pageService.updatePage(id, page, image);

        return ResponseEntity.ok(response);
    }


}
