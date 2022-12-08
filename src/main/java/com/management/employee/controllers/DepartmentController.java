package com.management.employee.controllers;

import com.management.employee.dtos.CDReturnDto;
import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Department;
import com.management.employee.repositories.DepartmentRepository;
import com.management.employee.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentController {


    @Autowired
      private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/department/{id}")
    public ResponseEntity<CDReturnDto> createDepartment(@PathVariable int id,@Valid @RequestBody DepartmentDto departmentDto){
        Department createDepartment=this.departmentService.saveDepartment(id,departmentDto);
        CDReturnDto cdReturnDto=this.modelMapper.map(createDepartment,CDReturnDto.class);
        return new ResponseEntity<>(cdReturnDto, HttpStatus.CREATED);
    }

    @GetMapping("/getDepartment")
    public  ResponseEntity<List<Department>> getAllDepartemts(){
        List<Department> departments=departmentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @DeleteMapping("/department/{cid}/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id,@PathVariable  int cid) throws NoSuchFieldException, PSQLException {
        this.departmentService.deleteDepartment(id,cid);

       return ResponseEntity.ok("department deleted with id "+id+" successfully");


    }


    @PutMapping("/edit/department/{cid}/{did}")
    public ResponseEntity<CDReturnDto> createDepartment(@PathVariable int cid,@PathVariable int did,@Valid @RequestBody DepartmentDto departmentDto) throws NoSuchFieldException {
        Department department=departmentService.editDepartment(cid,did,departmentDto);
        CDReturnDto cdReturnDto=this.modelMapper.map(department,CDReturnDto.class);
        return new ResponseEntity<>(cdReturnDto, HttpStatus.CREATED);

    }


}
