package com.inonu.ieee.controller;

import com.inonu.ieee.dtos.event.CreateEventDto;
import com.inonu.ieee.dtos.event.EventDto;
import com.inonu.ieee.service.abstracts.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private IEventService eventService;

    @GetMapping("")
    public List<EventDto> getAll()
    {
        return eventService.getEvents();
    }

    @PostMapping("")
    public EventDto createEvent(@RequestBody CreateEventDto dto) {
        return eventService.createEvent(dto);
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable(name = "id") UUID id) {
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable(name = "id") UUID id, @RequestBody CreateEventDto dto) {
        return eventService.updateEvent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable(name = "id") UUID id) {
        eventService.deleteEvent(id);
    }
}
