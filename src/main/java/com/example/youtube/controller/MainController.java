package com.example.youtube.controller;

import com.example.youtube.models.EmployeeAndMessageDTO;
import com.example.youtube.models.MessageDto;
import com.example.youtube.service.EmployeeService;
import com.example.youtube.service.MessagesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final EmployeeService employeeService;
    private final MessagesService messagesService;

    public MainController(EmployeeService employeeService, MessagesService messagesService) {
        this.employeeService = employeeService;
        this.messagesService = messagesService;
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

    @PostMapping("/employee/message/{id}")
    public String createMessage(@PathVariable Long id,
                                @ModelAttribute MessageDto messageDto){
        messagesService.newMessageToEmployee(messageDto,id);
        return "redirect:/";
    }

    @GetMapping("/chatbot")
    public String chatbot(Model model){
        model.addAttribute("messagesList",messagesService.findAllMessages());
        return "chatbot";
    }

}
