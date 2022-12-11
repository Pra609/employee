package com.management.employee.services;

import com.management.employee.entities.User;
import com.management.employee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.NoSuchElementException;

public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= this.userRepository.findOneByEmailIgnoreCase(email).orElseThrow(()->new NoSuchElementException("user with email: "+email+" not found"));
        return user;
    }
}
