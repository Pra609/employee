package com.management.employee.repositories;

import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    public Optional<Department> findOneByDepartmentNameIgnoreCase(String name);

    public List<Department> findByCompany(Company company);



    }
