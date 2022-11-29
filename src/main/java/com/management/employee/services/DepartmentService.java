package com.management.employee.services;


import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Department saveDepartment(DepartmentDto departmentDto){

        String dName = departmentDto.getDName();
        //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
        if(departmentRepository.findOneByDepartmentNameIgnoreCase(dName).isPresent()){
            throw new UserAlreadyExists("Department with name " + dName + " is already presenet  ");
        }
        Department department=new Department();
        department.setDepartmentName(departmentDto.getDName());
        //System.out.println(department);
        departmentRepository.save(department);

        return department;
    }
}
