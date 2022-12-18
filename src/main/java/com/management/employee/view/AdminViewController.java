package com.management.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @RequestMapping(value = "/home")
    public String adminHome() {
        return "Admin/adminhome";
    }



    @RequestMapping("/companyView/{id}")
    public String addRates(@PathVariable("id") int id) {
        return "Admin/companyView";
    }
    @RequestMapping("/departmentView")
    public String departmentView() {
        return "Admin/department";
    }

    @RequestMapping("/addCompany")
    public String addCompany() {
        return "Admin/addCompany";
    }

    @RequestMapping("/addDepartment")
    public String addDepartment() {
        return "Admin/addDepartment";
    }

    @RequestMapping("/userView/{did}")
    public String viewUser(@PathVariable int did) {
        return "Admin/userView";
    }

}
