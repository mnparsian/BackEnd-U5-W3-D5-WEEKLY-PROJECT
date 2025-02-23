package com.example.demo.Controller;


import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.EventService;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/profile")
    public ResponseEntity<Optional<User>> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername(); // دریافت نام کاربری از توکن
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestBody UserDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(userService.updateUser(username, userDTO));
    }

}



