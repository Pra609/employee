package com.management.employee.repositories;

import com.management.employee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findOneByEmailIgnoreCase(String email);
}
