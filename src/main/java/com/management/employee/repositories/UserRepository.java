package com.management.employee.repositories;

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

    @Query(value = "select*from  euser where department_id=:departmentId ",nativeQuery = true)
    public List<User> UserByDepartment(@Param("departmentId") int departmentId);


    @Query(value = "select*from  euser where company_id=:companyId ",nativeQuery = true)
    public List<User> UserByCompany(@Param("companyId") int companyId);

    @Modifying
    @Transactional
    @Query(value = "delete from  euser where user_id=:userId ",nativeQuery = true)
    public void deleteUser(@Param("userId") int userId);




}
