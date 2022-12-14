package com.management.employee.services;

import com.management.employee.dtos.CDReturnDto;
import com.management.employee.dtos.CompanyDto;
import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.CompanyRepository;
import com.management.employee.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;



    public Company saveCompany(CompanyDto companyDto){

        String Cname= companyDto.getName();
        //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
        if(companyRepository.findOneByCompanyNameIgnoreCase(Cname).isPresent()){
            throw new UserAlreadyExists("Company with name " + Cname + " is already present  ");
        }
        Company company=this.modelMapper.map(companyDto,Company.class);
        Company c1= companyRepository.save(company);

        return c1;
    }

    public List<Company> getCompany(){
        return  this.companyRepository.findAll();

    }

    public  Company addDepartment(int id,DepartmentDto departmentDto) {

        Company company = companyRepository.findById(id).get();
        if (company != null) {
            if (departmentDto.getDname() != null) {
                if (departmentRepository.findDepartmentByNameAndCompany(company.getCompanyId(),departmentDto.getDname())!=null) {
                    Department d1 = departmentRepository.findDepartmentByNameAndCompany(company.getCompanyId(),departmentDto.getDname());

                    company.getDepartment().add(d1);

                    companyRepository.save(company);
                }

            } else {
                throw new NoSuchElementException("department with id" + departmentDto.getDname() + "is not present");
            }

        } else {
            throw new NoSuchElementException("company with id" + id + "is not present");
        }
        return  company;

    }

    public void deleteCompany(int id){
        Company company = companyRepository.findById(id).get();
        if(company!=null){

            if(departmentRepository.findDepartmentByCompany(id).isEmpty()){

                companyRepository.deleteById(id);

            }
            else{
                throw new UserAlreadyExists("department under company having company id "+id+"already exists,please delete them first");
            }


        }else {
            throw new NoSuchElementException("company with id" + id + "is not present");
        }



    }

    public Company companyById(int id){
        Company company=null;
        if(companyRepository.findById(id).isPresent()){
            company=companyRepository.findById(id).get();
        }else {
            throw new NoSuchElementException("company with id" + id + "is not present");
        }
        return  company;
    }

    public  Company editCompanyById(int id,CompanyDto companyDto){
        Company company=null;
        if(companyRepository.findById(id).isPresent() && companyDto.getName()!=null){
            company=companyRepository.findById(id).get();
            company.setCompanyName(companyDto.getName());
         companyRepository.save(company);
        }else {
            throw new NoSuchElementException("company with id" + id + "is not present");
        }
        return  company;

    }



}
