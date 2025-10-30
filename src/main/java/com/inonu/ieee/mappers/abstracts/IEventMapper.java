package com.inonu.ieee.mappers.abstracts;

import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;
import com.inonu.ieee.model.Event;

import java.util.List;

public interface IEventMapper {

    public Event createDtoToEvent(CreateEventDto dto);
    public List<EventDto> eventListToDtoList(List<Event> events);
    public Event eventDtoToEvent(EventDto dto);
    public EventDto eventToEventDto(Event event);

}
