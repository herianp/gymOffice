package com.example.youtube.controller.api.rest;

import com.example.youtube.models.EmployeeDto;
import com.example.youtube.models.MessageDto;
import com.example.youtube.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployee(){
        return employeeService.findAllEmployee();
    }

    @PostMapping
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employee,
                                    @RequestBody MessageDto messageDto){
        return employeeService.save(employee,messageDto);
    }
}
