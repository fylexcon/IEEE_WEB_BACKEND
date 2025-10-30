package com.inonu.ieee.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inonu.ieee.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private UUID id;

    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private String description;

    private EventStatus status = EventStatus.FUTURE;
}
