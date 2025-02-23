package com.example.demo.Controller;

import com.example.demo.DTO.EventDTO;
import com.example.demo.Model.Event;
import com.example.demo.Model.User;
import com.example.demo.Service.EventService;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("کاربر با موفقیت حذف شد.");
    }

    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody EventDTO dto) {
        // ذخیره رویداد در دیتابیس
        eventService.createEvent(dto);
        return ResponseEntity.ok("Event created successfully");
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("رویداد حذف شد.");
    }
}

