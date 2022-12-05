package com.management.employee.repositories;

import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    public Optional<Company> findOneByCompanyNameIgnoreCase(String name);



}
