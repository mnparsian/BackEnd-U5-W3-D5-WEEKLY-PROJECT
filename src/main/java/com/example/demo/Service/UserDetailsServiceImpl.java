package com.example.demo.Service;




import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        System.out.println("🔍 پیدا شد؟: " + user.isPresent());


        if (user.isEmpty()) {
            throw new UsernameNotFoundException("کاربر با نام کاربری " + username + " پیدا نشد");
        }
        System.out.println("🔍 رمز عبور هش‌شده در دیتابیس: " + user.get().getPassword());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles(user.get().getRole().toString()) // مقداردهی صحیح نقش‌ها
                .build();
    }

}

