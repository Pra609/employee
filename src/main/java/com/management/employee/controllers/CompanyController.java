package com.management.employee.controllers;

import com.management.employee.dtos.CDReturnDto;
import com.management.employee.dtos.CompanyDto;
import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import com.management.employee.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/company")
    public ResponseEntity<CDReturnDto> createCompany(@Valid @RequestBody CompanyDto companyDto){
       Company company=this.companyService.saveCompany(companyDto);
      CDReturnDto cdReturnDto=this.modelMapper.map(company,CDReturnDto.class);
        return new ResponseEntity<>(cdReturnDto, HttpStatus.CREATED);
    }


    @PutMapping("/company/addDepartment/{id}")
    public ResponseEntity<Company> addDepartment(@PathVariable int id,@Valid @RequestBody DepartmentDto departmentDto){
        Company company=new Company();
        if(departmentDto!=null){
       company =this.companyService.addDepartment(id,departmentDto);
        }


        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(){

        List<Company> companies= this.companyService.getCompany();
        return  new ResponseEntity<>(companies, HttpStatus.OK);



    }


    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getAllCompanies(@PathVariable int id){

      Company company= this.companyService.companyById(id);
        return  new ResponseEntity<>(company, HttpStatus.OK);



    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> createCompany(@PathVariable int id){
         companyService.deleteCompany(id);

        return ResponseEntity.ok("company deleted with id "+id+" successfully");
    }




}
