package com.example.demo.Service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.Model.Event;
import com.example.demo.Model.User;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

  @Autowired private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public Optional<Event> getEventById(Long id) {
    return eventRepository.findById(id);
  }

  public Event createEvent(EventDTO eventDTO) {
    Optional<User> organizer = userRepository.findById(eventDTO.getOrganizerId());
    if (organizer.isEmpty()) {
      throw new RuntimeException("سازمان‌دهنده پیدا نشد");
    }

    Event event = new Event();
    event.setTitle(eventDTO.getTitle());
    event.setDescription(eventDTO.getDescription());
    event.setDateTime(eventDTO.getDateTime());
    event.setLocation(eventDTO.getLocation());
    event.setAvailableSeats(eventDTO.getAvailableSeats());
    event.setOrganizer(organizer.get());

    return eventRepository.save(event);
  }

  public void deleteEvent(Long id){
    eventRepository.deleteById(id);
  }
}
