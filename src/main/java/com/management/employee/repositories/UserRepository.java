package com.management.employee.repositories;

import com.management.employee.entities.Department;
import com.management.employee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findOneByEmailIgnoreCase(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from  euser where department_id=:departmentId ",nativeQuery = true)
    public void deleteUserByDepartment(@Param("departmentId") int departmentId);

    @Query(value = "select*from  euser e  join  user_role u on e.user_id=u.euser where e.department_id=:departmentId and  u.role!=1",nativeQuery = true)
    public List<User> UserByDepartment(@Param("departmentId") int departmentId);
    @Query(value = "SELECT*from euser e join  user_role u on e.user_id=u.euser where  u.role!=1  and e.name  LIKE %?1%  and  e.department_id=?2 ",nativeQuery = true )
    public List<User> getUserByDepartmentAndKeyword(String keyword, int departmentId);

    @Query(value = "select*from  euser e  join  user_role u on e.user_id=u.euser where e.company_id=:companyId and  u.role!=1",nativeQuery = true)
    public List<User> UserByCompany(@Param("companyId") int companyId);

    @Modifying
    @Transactional
    @Query(value = "delete from  euser where user_id=:userId ",nativeQuery = true)
    public void deleteUser(@Param("userId") int userId);




}
