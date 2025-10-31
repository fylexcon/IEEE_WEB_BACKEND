package com.inonu.ieee.dtos.slider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSliderDto {

    private String path;

    private int sliderOrder;

    private String link;

    private String title;

    private String description;
}
