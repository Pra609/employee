package com.management.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminViewController {


    @RequestMapping("/home")
    public String adminHome() {
        return "index";
    }
}
