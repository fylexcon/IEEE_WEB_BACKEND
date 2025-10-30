package com.inonu.ieee.service.concreates;

import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;
import com.inonu.ieee.exceptions.EntityNotFoundException;
import com.inonu.ieee.mappers.abstracts.IEventMapper;
import com.inonu.ieee.model.Event;
import com.inonu.ieee.repository.EventRepository;
import com.inonu.ieee.service.abstracts.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventManager implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private IEventMapper eventMapper;

    @Override
    public List<EventDto> getEvents() {

        List<Event> event = eventRepository.findAll();

        return eventMapper.eventListToDtoList(event);
    }

    @Override
    public EventDto createEvent(CreateEventDto dto) {
        Event event = eventRepository.save(eventMapper.createDtoToEvent(dto));
        return eventMapper.eventToEventDto(event);
    }

    @Override
    public EventDto getEventById(UUID id) {
        Optional<Event> event = eventRepository.findById(id);

        if(event.isEmpty())
            return null;

        return eventMapper.eventToEventDto(event.get());
    }

    @Override
    public EventDto updateEvent(UUID id, CreateEventDto dto) {

        Optional<Event> optional = eventRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Event not found.");

        Event event =  optional.get();
        event.setId(id);
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setStatus(dto.getStatus());

        return eventMapper.eventToEventDto(eventRepository.save(event));
    }

    @Override
    public void deleteEvent(UUID id) {
        Optional<Event> event = eventRepository.findById(id);

        if(event.isEmpty())
            throw new EntityNotFoundException("Event not found.");

        eventRepository.deleteById(id);
    }


}
