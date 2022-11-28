package com.management.employee.security;

import com.management.employee.entities.User;
import com.management.employee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
     private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userRepository.findOneByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("user with eamil "+email+"is not present"));
       return  user;
    }
}
