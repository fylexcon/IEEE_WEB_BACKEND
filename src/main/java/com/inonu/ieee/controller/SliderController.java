package com.inonu.ieee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inonu.ieee.dtos.slider.CreateSliderDto;
import com.inonu.ieee.dtos.slider.SliderDto;
import com.inonu.ieee.service.abstracts.ISliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/sliders")
public class SliderController {

    @Autowired
    private ISliderService sliderService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SliderDto> findById(@PathVariable UUID id) {
        SliderDto sliderDto = sliderService.findById(id);
        return ResponseEntity.ok(sliderDto);
    }

    @PostMapping("")
    private ResponseEntity<SliderDto> createSlider(@RequestPart String slider, @RequestPart MultipartFile image) throws JsonProcessingException {

        CreateSliderDto createDto = objectMapper.readValue(slider, CreateSliderDto.class);

        SliderDto response = sliderService.create(createDto, image);
        return ResponseEntity.ok(response);
    }


}
