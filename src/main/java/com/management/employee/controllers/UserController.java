package com.management.employee.controllers;

import com.management.employee.dtos.UserDto;
import com.management.employee.dtos.UserEditDto;
import com.management.employee.entities.User;
import com.management.employee.repositories.UserRepository;
import com.management.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> createEmployee(@RequestBody UserDto employee){
        User employeeDto=userService.createEmployee(employee);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }

    @PutMapping("/user/{id}")
      public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserEditDto user){
       User user1=userService.userEdit( id,user);
       return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
         userService.deleteUser(id);
        return ResponseEntity.ok("user deleted with id "+id+" successfully");
    }


    @GetMapping("user/department/{did}")
    public ResponseEntity<List<User>> getUserbyDepartment(@PathVariable int did){
        List<User> users=userRepository.UserByDepartment(did);

        return  new ResponseEntity<>(users, HttpStatus.OK);

    }


    @GetMapping("user/company/{did}")
    public ResponseEntity<List<User>> getUserbyCompany(@PathVariable int did){
        List<User> users=userRepository.UserByCompany(did);

        return  new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable int id){

        User  user=userService.getUserById(id);
        return  new ResponseEntity<>(user, HttpStatus.OK);

    }


}
