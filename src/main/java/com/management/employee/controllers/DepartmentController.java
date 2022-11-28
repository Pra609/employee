package com.management.employee.controllers;

import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Department;
import com.management.employee.repositories.DepartmentRepository;
import com.management.employee.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentController {


    @Autowired
      private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/department")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        DepartmentDto createDepartmentDto=this.departmentService.saveDepartment(departmentDto);

        return new ResponseEntity<>(createDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/getDepartment")
    public  ResponseEntity<List<Department>> getAllDepartemts(){
        List<Department> departments=departmentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


}
