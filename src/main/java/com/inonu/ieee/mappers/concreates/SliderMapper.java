package com.inonu.ieee.mappers.concreates;

import com.inonu.ieee.dtos.slider.CreateSliderDto;
import com.inonu.ieee.dtos.slider.SliderDto;
import com.inonu.ieee.mappers.abstracts.ISliderMapper;
import com.inonu.ieee.model.Slider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SliderMapper implements ISliderMapper {
    @Override
    public Slider createDtoToSlider(CreateSliderDto dto) {
        Slider slider = new Slider();
        slider.setPath(dto.getPath());
        slider.setLink(dto.getLink());
        slider.setTitle(dto.getTitle());
        slider.setDescription(dto.getDescription());
        slider.setSliderOrder(dto.getSliderOrder());

        return slider;
    }

    @Override
    public List<SliderDto> sliderListToDtoList(List<Slider> sliders) {
        List<SliderDto> sliderDtoList = new ArrayList<>();

        for(Slider slider : sliders) {
            SliderDto sl = sliderToSliderDto(slider);
            sliderDtoList.add(sl);
        }

        return sliderDtoList;
    }

    @Override
    public Slider sliderDtoToSlider(SliderDto dto) {
        Slider slider = new Slider();
        slider.setPath(dto.getPath());
        slider.setPath(dto.getPath());
        slider.setLink(dto.getLink());
        slider.setTitle(dto.getTitle());
        slider.setDescription(dto.getDescription());
        slider.setSliderOrder(dto.getSliderOrder());

        return slider;
    }

    @Override
    public SliderDto sliderToSliderDto(Slider slider) {
        SliderDto dto = new SliderDto();
        dto.setId(slider.getId());
        dto.setPath(slider.getPath());
        dto.setLink(slider.getLink());
        dto.setTitle(slider.getTitle());
        dto.setDescription(slider.getDescription());
        dto.setSliderOrder(slider.getSliderOrder());

        return dto;
    }
}
