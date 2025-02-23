package com.example.demo;

import com.example.demo.Model.ERole;
import com.example.demo.Model.Role;
import com.example.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName(ERole.ADMIN);
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName(ERole.USER);
            roleRepository.save(adminRole);

            Role organizerRole = new Role();
            adminRole.setName(ERole.ORGANIZER);
            roleRepository.save(organizerRole);
        }
    }
}
