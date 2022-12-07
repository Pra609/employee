package com.management.employee.repositories;

import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    public Optional<Department> findOneByDepartmentNameIgnoreCase(String name);

    public List<Department> findByCompany(Company company);

    @Modifying
    @Transactional
    @Query(value = "delete from  company_department where department_id=:departmentId and company_id=:companyId",nativeQuery = true)
    public void deleteDepartment(@Param("departmentId") int departmentId,@Param("companyId") int companyId);



    }
