package com.management.employee.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private  Set<User> user=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "company_department",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Department> department=new HashSet<>();


}
