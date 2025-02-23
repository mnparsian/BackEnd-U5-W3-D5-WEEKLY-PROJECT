package com.example.demo.Repository;

import com.example.demo.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByOrganizerId(Long organizerId);
}
