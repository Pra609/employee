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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/allcompanies")
    public ResponseEntity<List<Company>> getAllcompanies(){

        List<Company> companies= this.companyService.getCompany();
        return  new ResponseEntity<>(companies, HttpStatus.OK);



    }



}
