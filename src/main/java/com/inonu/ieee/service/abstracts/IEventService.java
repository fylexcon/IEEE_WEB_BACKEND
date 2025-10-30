package com.inonu.ieee.service.abstracts;


import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;

import java.util.List;
import java.util.UUID;

public interface IEventService {

    public List<EventDto> getEvents();
    public EventDto createEvent(CreateEventDto dto);
    public EventDto getEventById(UUID id);
    public EventDto updateEvent(UUID id, CreateEventDto dto);
    public void deleteEvent(UUID id);
}
