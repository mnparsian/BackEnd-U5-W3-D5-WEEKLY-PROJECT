package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.ERole;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder; // استفاده از Bean برای هش کردن پسورد
  @Autowired private RoleRepository roleRepository;

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
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    Set<Role> roles = new HashSet<>();

    for (String roleName : userDTO.getRoles()) {
      Role role = roleRepository.findByName(ERole.valueOf(roleName))
              .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
      roles.add(role);
    }
    user.setRoles(roles);

    user.setRoles(roles);// نقش‌ها رو به کاربر اختصاص بده
    //    Optional<Role> role = roleRepository.findByName("ADMIN");
    //    System.out.println("Role found: " + role.isPresent());
    roles.forEach(System.out::println);
    for (Role role : user.getRoles()) {
      role.getUsers().add(user); // کاربر رو به لیست کاربران نقش اضافه کن
    }

    System.out.println("🔍 User Roles Before Save: " + user.getRoles());
    return userRepository.save(user);

  }


  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public Optional<User> findByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return user;
  }

  public User updateUser(String username, UserDTO userDTO) {
    // پیدا کردن کاربر در دیتابیس
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        "کاربر با نام کاربری " + username + " پیدا نشد!"));

    // اگر ایمیل جدیدی فرستاده شده بود، آن را تنظیم کن
    if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
      user.setEmail(userDTO.getEmail());
    }

    // اگر رمز عبور جدید فرستاده شده بود، آن را هش کرده و تنظیم کن
    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }

    // اگر نقش جدیدی فرستاده شده بود، آن را تنظیم کن (در صورت داشتن جدول جداگانه برای نقش‌ها)
    if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
      Set<Role> roles = new HashSet<>();
      for (String roleName : userDTO.getRoles()) {
        // تبدیل String به Enum
        ERole roleEnum = ERole.valueOf(roleName);

        // گرفتن Role از دیتابیس
        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        roles.add(role);
      }

      user.setRoles(roles);
    }

    // ذخیره‌سازی تغییرات در دیتابیس


    return userRepository.save(user);

  }
    }