package com.management.employee.services;

import com.management.employee.dtos.CompanyDto;
import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;



    public Company saveCompany(CompanyDto companyDto){

        String Cname= companyDto.getName();
        //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
        if(companyRepository.findOneByCompanyNameIgnoreCase(Cname).isPresent()){
            throw new UserAlreadyExists("Company with name " + Cname + " is already presenet  ");
        }
        Company company=this.modelMapper.map(companyDto,Company.class);
        Company c1= companyRepository.save(company);

        return c1;
    }

    public List<Company> getCompany(){
        return  this.companyRepository.findAll();

    }

}
