package com.inonu.ieee.dtos.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private UUID id;

    private String title;

    private String content;

    private String image;

    private boolean status;
}
