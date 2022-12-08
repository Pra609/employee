package com.management.employee.services;


import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.CompanyRepository;
import com.management.employee.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepository companyRepository;

    public Department saveDepartment(int id,DepartmentDto departmentDto){
        Department department=new Department();
        if(companyRepository.findById(id).isPresent()){
            String dName = departmentDto.getDname();
            //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
            if(departmentRepository.findDepartmentByNameAndCompany(id,dName)!= null){
                System.out.println();
                throw new UserAlreadyExists("Department with name " + dName + " is already present  ");
            }

            department.setDepartmentName(departmentDto.getDname());
              Company company=companyRepository.findById(id).get();
            department.setCompany(company);
            departmentRepository.save(department);



        }



        return department;
    }


    public  void deleteDepartment(int id,int cid) throws NoSuchFieldException {



        Optional<Department> d1=departmentRepository.findById(id);
        if(d1!=null){
            departmentRepository.deleteDepartment(id,cid);
        }else{
            throw new NoSuchFieldException("department id "+id+" is not present");
        }



    }
}
