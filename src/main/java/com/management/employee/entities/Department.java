package com.management.employee.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<User> user=new HashSet<>();


    @ManyToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Set<Company> company=new HashSet<>();




}
