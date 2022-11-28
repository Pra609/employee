package com.management.employee.services;


import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Department;
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

    public DepartmentDto saveDepartment(DepartmentDto departmentDto){
        Department department=modelMapper.map(departmentDto, Department.class);
        departmentRepository.save(department);
        return departmentDto;
    }
}
