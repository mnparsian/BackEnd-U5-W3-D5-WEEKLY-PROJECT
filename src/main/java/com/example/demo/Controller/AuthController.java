package com.example.demo.Controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private UserService userService;
  @Autowired private AuthService authService;

  @PostMapping("/register")
  public User registerUser(@RequestBody UserDTO userDTO) {
    return userService.createUser(userDTO);
  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    System.out.println("🔥 درخواست ورود دریافت شد: " + loginRequest.getUsername());
    return authService.authenticateUser(loginRequest);
  }
}
