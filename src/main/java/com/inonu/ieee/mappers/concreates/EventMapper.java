package com.inonu.ieee.mappers.concreates;

import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;
import com.inonu.ieee.mappers.abstracts.IEventMapper;
import com.inonu.ieee.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EventMapper implements IEventMapper {

    @Override
    public Event createDtoToEvent(CreateEventDto dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setStatus(dto.getStatus());

        return event;
    }

    @Override
    public List<EventDto> eventListToDtoList(List<Event> events) {
        List<EventDto> eventDtos = new ArrayList<>();

        for (Event event : events) {
            EventDto ev = new EventDto();
            ev.setId(event.getId());
            ev.setTitle(event.getTitle());
            ev.setDescription(event.getDescription());
            ev.setDate(event.getDate());
            ev.setStatus(event.getStatus());

            eventDtos.add(ev);
        }

        return eventDtos;
    }

    @Override
    public Event eventDtoToEvent(EventDto dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setStatus(dto.getStatus());

        return event;
    }

    @Override
    public EventDto eventToEventDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        dto.setStatus(event.getStatus());

        return dto;
    }
}
