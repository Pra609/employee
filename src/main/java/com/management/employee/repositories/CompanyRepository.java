package com.management.employee.repositories;

import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    public Optional<Company> findOneByCompanyNameIgnoreCase(String name);

    @Query("SELECT a from  Company a WHERE  a.companyName LIKE %?1%")
    public List<Company> getcompanyByKeyword(String keyword);




}
