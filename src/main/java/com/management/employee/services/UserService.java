package com.management.employee.services;

import com.management.employee.dtos.UserDto;
import com.management.employee.dtos.UserEditDto;
import com.management.employee.entities.Department;
import com.management.employee.entities.User;
import com.management.employee.errors.UserAlreadyExists;
import com.management.employee.repositories.CompanyRepository;
import com.management.employee.repositories.DepartmentRepository;
import com.management.employee.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public User createEmployee(UserDto userDto) {


        String email = userDto.getEmail();
        System.out.println(email);
        System.out.println(userDto);

        if (userRepository.findOneByEmailIgnoreCase(email).isPresent()) {
            throw new UserAlreadyExists("employee by email " + email + " already exist");
        }

        User employee = modelMapper.map(userDto, User.class);

        int cid= userDto.getCid();
        if(companyRepository.findById(cid).isPresent()){
            employee.setCompany(companyRepository.getById(cid));
        }
        else {
            throw new NoSuchElementException("company " + cid + " not found");
        }
        userRepository.save(employee);


        return employee;

    }


    public User userEdit(int id, UserEditDto userEditDto) {
        User user = userRepository.findById(id).get();
        System.out.println(user);
        if (userEditDto.getName() != null) {
            user.setName(userEditDto.getName());
        }
        if (userEditDto.getEmail() != null) {
            user.setEmail(userEditDto.getEmail());
        }
        if (userEditDto.getPassword() != null) {
            user.setPassword(userEditDto.getPassword());
        }
        if (userEditDto.getDid() != 0) {

            List<Department> department=departmentRepository.findByCompany(user.getCompany());

            if (department.contains(departmentRepository.findById(userEditDto.getDid()).get())) {
                user.setDepartment(departmentRepository.findById(userEditDto.getDid()).get());
            } else {
                throw new NoSuchElementException("department with id " + userEditDto.getDid() + " is not present in company "+ user.getCompany().getCompanyName());
            }




        }
        userRepository.save(user);

        return user;

    }


    public void deleteUser(int user_id){


            if(userRepository.findById(user_id).isPresent()){
                User user=userRepository.findById(user_id).get();
                //System.out.println(user);
                  userRepository.deleteUser(user_id);

            }
            else{
                throw  new NoSuchElementException("user with id "+user_id+" doesn't exist");

            }




    }


    public User getUserById(int user_id){
        User user=null;

        if(userRepository.findById(user_id).isPresent()){
            user=userRepository.findById(user_id).get();


        }
        else{
            throw  new NoSuchElementException("user with id "+user_id+" doesn't exist");

        }

          return user;


    }

    public List<User>  getUserByDepartment(int did){

        List<User> user=null;

        if(departmentRepository.findById(did).isPresent()){

            if(!userRepository.UserByDepartment(did).isEmpty()){
                user=userRepository.UserByDepartment(did);
            }else{

                throw new NoSuchElementException("There are no users under department with name"+departmentRepository.findById(did).get().getDepartmentName());
            }


        }else{
            throw  new NoSuchElementException("department with id "+did+" doesn't exist");

        }
        return  user;
    }



    public List<User>  getUserByCompany(int did){

        List<User> user=null;

        if(companyRepository.findById(did).isPresent()){

            if(!userRepository.UserByCompany(did).isEmpty()){
                user=userRepository.UserByDepartment(did);
            }else{

                throw new NoSuchElementException("There are no users under department with name"+departmentRepository.findById(did).get().getDepartmentName());
            }


        }else{
            throw  new NoSuchElementException("company with id "+did+" doesn't exist");

        }
        return  user;
    }






}
