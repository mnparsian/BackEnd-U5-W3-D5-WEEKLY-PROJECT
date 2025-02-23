package com.example.demo.Repository;

import com.example.demo.Model.ERole;
import com.example.demo.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
public Optional<Role> findByName(ERole name);
}
