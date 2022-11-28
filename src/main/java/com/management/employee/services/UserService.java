package com.management.employee.services;

import com.management.employee.dtos.UserDto;
import com.management.employee.entities.User;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public UserDto createEmployee(UserDto userDto) {


        String email = userDto.getEmail();
        System.out.println(email);
        System.out.println(userDto);

        if (userRepository.findOneByEmailIgnoreCase(email).isPresent()) {
            throw new UserAlreadyExists("employee by email " + email + " already exist");
        }

        User employee = modelMapper.map(userDto, User.class);


        userRepository.save(employee);

        UserDto userDto1 = modelMapper.map(employee, UserDto.class);
        return userDto1;

    }
}
