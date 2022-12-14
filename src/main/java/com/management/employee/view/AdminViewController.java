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
    public String addRates(@PathVariable int id) {
        return "Admin/companyView";
    }
    @RequestMapping("/departmentView")
    public String energyReports() {
        return "Admin/department";
    }

    @RequestMapping("/viewrates")
    public String viewAllRates() {
        return "Admin/viewrates";
    }

    @RequestMapping("/generatebill")
    public String generateBill() {
        return "Admin/bill";
    }

}
