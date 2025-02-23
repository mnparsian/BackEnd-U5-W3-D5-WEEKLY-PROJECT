package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder; // استفاده از Bean برای هش کردن پسورد

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // هش کردن رمز عبور
    user.setRole(userDTO.getRole());

    return userRepository.save(user);
  }
}
