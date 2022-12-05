package com.management.employee.services;


import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
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

    public Department saveDepartment(DepartmentDto departmentDto){

        String dName = departmentDto.getDname();
        //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
        if(departmentRepository.findOneByDepartmentNameIgnoreCase(dName).isPresent()){
            throw new UserAlreadyExists("Department with name " + dName + " is already present  ");
        }
        Department department=new Department();
        department.setDepartmentName(departmentDto.getDname());
        //System.out.println(department);
        departmentRepository.save(department);

        return department;
    }


    public  void deleteDepartment(int id) throws NoSuchFieldException {

        Optional<Department> d1=departmentRepository.findById(id);
        if(d1!=null){
            departmentRepository.deleteById(id);
        }else{
            throw new NoSuchFieldException("department id "+id+" is not present");
        }



    }
}
