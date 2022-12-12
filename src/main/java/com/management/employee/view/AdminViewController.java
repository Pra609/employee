package com.management.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @RequestMapping(value = "/home")
    public String adminHome() {
        return "Admin/adminhome";
    }



    @RequestMapping("/companyView")
    public String addRates() {
        return "Admin/companyView";
    }
    @RequestMapping("/reports")
    public String energyReports() {
        return "Admin/monthly";
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
