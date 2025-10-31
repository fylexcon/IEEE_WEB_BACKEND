package com.inonu.ieee.service.concreates;

import com.inonu.ieee.dtos.slider.CreateSliderDto;
import com.inonu.ieee.dtos.slider.SliderDto;
import com.inonu.ieee.exceptions.EntityNotFoundException;
import com.inonu.ieee.mappers.abstracts.ISliderMapper;
import com.inonu.ieee.model.Slider;
import com.inonu.ieee.repository.SliderRepository;
import com.inonu.ieee.service.abstracts.IImageService;
import com.inonu.ieee.service.abstracts.ISliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SliderManager implements ISliderService {

    @Autowired
    private SliderRepository sliderRepository;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ISliderMapper sliderMapper;

    @Override
    public SliderDto findById(UUID id) {

        Optional<Slider> optional = sliderRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Slider not found.");

        return sliderMapper.sliderToSliderDto( optional.get());
    }

    @Override
    public List<SliderDto> findAll() {
        List<Slider> sliders = sliderRepository.findAll();
        return sliderMapper.sliderListToDtoList(sliders);
    }

    @Override
    public SliderDto create(CreateSliderDto createSliderDto, MultipartFile image) {

        String imageName = imageService.uploadImage(image);
        createSliderDto.setPath(imageName);
        Slider createdSlider = sliderRepository.save(sliderMapper.createDtoToSlider(createSliderDto));

        return sliderMapper.sliderToSliderDto(createdSlider);
    }

    @Override
    public SliderDto update(UUID id, CreateSliderDto dto, MultipartFile image) {

        Optional<Slider> optional = sliderRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Slider not found.");


        Slider slider = optional.get();

        if(!image.isEmpty() || image != null)
        {
            imageService.deleteImage(slider.getPath());

            String fileName = imageService.uploadImage(image);
            slider.setPath(fileName);
        }

        slider.setTitle(dto.getTitle());
        slider.setDescription(dto.getDescription());
        slider.setLink(dto.getLink());
        slider.setSliderOrder(dto.getSliderOrder());

        slider = sliderRepository.save(slider);

        return sliderMapper.sliderToSliderDto(slider);
    }

    @Override
    public void delete(UUID id) {

    }
}
