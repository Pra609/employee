package com.management.employee.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int companyId;

    private String companyName;

    @OneToMany(mappedBy = "company")
   private  Set<User> user=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "company_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Department> department=new HashSet<>();


}
