package com.management.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @RequestMapping("/home")
    public String adminHome() {
        return "Admin/adminhome";
    }
}
