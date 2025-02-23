package com.example.demo.Controller;

import com.example.demo.DTO.EventDTO;
import com.example.demo.Model.Event;
import com.example.demo.Service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired private EventService eventService;

  @GetMapping
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/{id}")
  public Optional<Event> getEventById(@PathVariable Long id) {
    return eventService.getEventById(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Event createEvent(@Valid @RequestBody EventDTO eventDTO) {
    return eventService.createEvent(eventDTO);
  }

}
