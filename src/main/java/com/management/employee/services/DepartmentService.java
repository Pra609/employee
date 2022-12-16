package com.management.employee.services;


import com.management.employee.dtos.DepartmentDto;
import com.management.employee.entities.Company;
import com.management.employee.entities.Department;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.CompanyRepository;
import com.management.employee.repositories.DepartmentRepository;
import com.management.employee.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public Department saveDepartment(int id,DepartmentDto departmentDto){
        Department department=new Department();
        if(companyRepository.findById(id).isPresent()){
            String dName = departmentDto.getDname();
            System.out.println(dName+"dname");
            if(!departmentDto.getDname().equals(null) && !departmentDto.getDname().isEmpty()) {
                boolean c1=!departmentDto.getDname().equals(null);
                boolean c2=!departmentDto.getDname().isEmpty();

                System.out.println(c1+"c1");
                System.out.println(c2+"c2");
                System.out.println(departmentDto.getDname());


                //System.out.println(departmentRepository.findOneByDepartmentNameIgnoreCase(dName));
                if (departmentRepository.findDepartmentByNameAndCompany(id, dName) != null) {
                    System.out.println();
                    throw new UserAlreadyExists("Department with name " + dName + " is already present  ");
                }


                department.setDepartmentName(departmentDto.getDname());
                Company company = companyRepository.findById(id).get();
                department.setCompany(company);
                departmentRepository.save(department);

            }

        }



        return department;
    }


    public  void deleteDepartment(int id,int cid)  {



        Optional<Department> d1=departmentRepository.findById(id);
        if(d1!=null){

            //userRepository.deleteUserByDepartment(id);
            if(userRepository.UserByDepartment(id).isEmpty()){
                departmentRepository.deleteDepartment(id,cid);
            }else{
                throw new UserAlreadyExists("Department with name " + id + " has employee  present  ");
            }


        }else{
            throw new NoSuchElementException("department id "+id+" is not present");
        }



    }

    public Department editDepartment(int cid,int did,DepartmentDto departmentDto)  {

        Department department=null;

        if(companyRepository.findById(cid).isPresent()){

            department=departmentRepository.findById(did).get();

            if(department!=null && departmentDto!=null){

                if(departmentRepository.findDepartmentByNameAndCompany(did,departmentDto.getDname())!= null){
                    System.out.println();
                    throw new UserAlreadyExists("Department with name " + departmentDto.getDname() + " is already present  ");
                }
                department.setDepartmentName(departmentDto.getDname());
                departmentRepository.save(department);

            }else{
                throw new NoSuchElementException("department id "+did+" is not present");
            }


        }

        return  department;

    }


    public List<Department> getDepartmentByCompany(int cid)  {
        List<Department> departments=null;
        if(companyRepository.findById(cid).isPresent()){

            departments=departmentRepository.findDepartmentByCompany(cid);


        }else{
            throw new NoSuchElementException("company id "+cid+" is not present");
        }

        return  departments;
    }


    public Department getDepartmentById(int id){

        Department department=null;

        if(departmentRepository.findById(id).isPresent()){
            department=departmentRepository.findById(id).get();
        }else{
            throw new NoSuchElementException(" Department by id "+id+" is not present");
        }

        return  department;

    }


}
