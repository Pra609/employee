package com.management.employee.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "euser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int userId;

    private String name;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private  Department department;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
