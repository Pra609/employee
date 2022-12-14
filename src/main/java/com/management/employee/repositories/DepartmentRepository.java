package com.management.employee.repositories;

import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(value = "select*from department where company_id = :companyId and department_name = :name",nativeQuery = true)
    public Department findDepartmentByNameAndCompany(@Param("companyId") int companyId,@Param("name") String name);
//
    public List<Department> findByCompany(Company company);

    @Modifying
    @Transactional
    @Query(value = "delete from  department where department_id=:departmentId and company_id=:companyId",nativeQuery = true)
    public void deleteDepartment(@Param("departmentId") int departmentId,@Param("companyId") int companyId);
    @Query(value = "select*from department where company_id = :companyId ",nativeQuery = true)
    public List<Department> findDepartmentByCompany(@Param("companyId") int companyId);
//

    @Query("SELECT a from  Department a WHERE  a.departmentName  LIKE %?1%  and a.company.companyId=?2")
    public List<Department> getDepartmentByKeyword(String keyword,int companyId);

    //@Query(value="select*from department  WHERE  a.department_name LIKE %'keyword'%  and company_id=:companyId",nativeQuery = true)
    //public List<Department> getDepartmentByKeywordAndCompany(@Param("companyId") int companyId,@Param("keyword") String keyword);
    }
