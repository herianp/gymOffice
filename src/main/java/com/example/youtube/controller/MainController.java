package com.example.youtube.controller;

import com.example.youtube.models.EmployeeAndMessageDTO;
import com.example.youtube.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/employee")
    public String createEmployee(@ModelAttribute EmployeeAndMessageDTO employeeAndMessageDTO){
        employeeService.save(employeeAndMessageDTO);

        return "redirect:/";
    }


}
