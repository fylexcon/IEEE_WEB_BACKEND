package com.inonu.ieee.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inonu.ieee.enums.EventStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {

    @NotNull
    @Min(5)
    @Max(100)
    private String title;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull
    @Min(25)
    @Max(255)
    private String description;

    @NotNull
    private EventStatus status;
}
