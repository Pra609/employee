package com.management.employee.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int departmentId;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    Set<User> user=new HashSet<>();


    @ManyToMany(mappedBy = "department")
    private  Set<Company> company=new HashSet<>();




}
