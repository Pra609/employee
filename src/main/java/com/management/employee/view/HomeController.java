package com.management.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
    @RequestMapping("/signin")
    public String customLogin(Model model)
    {
        model.addAttribute("title", "Login Page");
        return "signin";
    }
    @RequestMapping("/register")
    public String registerCustomer() {
        return "register";
    }
}
