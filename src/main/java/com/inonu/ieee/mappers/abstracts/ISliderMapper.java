package com.inonu.ieee.mappers.abstracts;

import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;
import com.inonu.ieee.dtos.slider.CreateSliderDto;
import com.inonu.ieee.dtos.slider.SliderDto;
import com.inonu.ieee.model.Event;
import com.inonu.ieee.model.Slider;

import java.util.List;

public interface ISliderMapper {

    public Slider createDtoToSlider(CreateSliderDto dto);
    public List<SliderDto> sliderListToDtoList(List<Slider> sliders);
    public Slider sliderDtoToSlider(SliderDto dto);
    public SliderDto sliderToSliderDto(Slider slider);

}
