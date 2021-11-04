package com.example.youtube.controller;

import com.example.youtube.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("employeeList",employeeService.findAllEmployee());
        return "index";
    }


}
