package com.management.employee.repositories;

import com.management.employee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findOneByEmailIgnoreCase(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from  euser where department_id=:departmentId ",nativeQuery = true)
    public void deleteUserByDepartment(@Param("departmentId") int departmentId);

    @Query(value = "select*from  euser where department_id=:departmentId ",nativeQuery = true)
    public List<User> UserByDepartment(@Param("departmentId") int departmentId);
}
