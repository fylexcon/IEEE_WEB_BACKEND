package com.inonu.ieee.service.abstracts;

import com.inonu.ieee.dtos.slider.CreateSliderDto;
import com.inonu.ieee.dtos.slider.SliderDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ISliderService {

    public SliderDto findById(UUID id);
    public List<SliderDto> findAll();
    public SliderDto create(CreateSliderDto createSliderDto, MultipartFile image);
    public SliderDto update(UUID id, CreateSliderDto createSliderDto, MultipartFile image);
    public void delete(UUID id);
}
