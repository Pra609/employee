package com.management.employee.controllers;

import com.management.employee.dtos.UserDto;
import com.management.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> createEmployee(@RequestBody UserDto employee){
        UserDto employeeDto=userService.createEmployee(employee);
        return ResponseEntity.ok(employeeDto);
    }

}
