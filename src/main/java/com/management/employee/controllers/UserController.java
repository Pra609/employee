package com.management.employee.controllers;

import com.management.employee.dtos.UserDto;
import com.management.employee.dtos.UserEditDto;
import com.management.employee.dtos.UserReturnDto;
import com.management.employee.entities.User;
import com.management.employee.repositories.UserRepository;
import com.management.employee.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    ModelMapper modelMapper;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/department/{did}")
    public ResponseEntity<List<UserReturnDto>> getUserbyDepartment(@PathVariable int did,@RequestParam(required = false) String keyword){
        List<User> users=userService.getUserByDepartment(did,keyword);

        List<UserReturnDto>  userReturnDtos=new ArrayList<>();


        for(int i=0;i<users.size();i++){
            UserReturnDto userReturnDto=new UserReturnDto();
            userReturnDto.setName(users.get(i).getName());
            userReturnDto.setEmail(users.get(i).getEmail());
            userReturnDto.setDname(users.get(i).getDepartment().getDepartmentName());
            userReturnDtos.add(userReturnDto);
        }


        return  new ResponseEntity<>(userReturnDtos, HttpStatus.OK);

    }


    @GetMapping("/user/company/{did}")
    public ResponseEntity<List<User>> getUserbyCompany(@PathVariable int did){
        List<User> users=userRepository.UserByCompany(did);

        return  new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable int id){

        User  user=userService.getUserById(id);
        return  new ResponseEntity<>(user, HttpStatus.OK);

    }


}
