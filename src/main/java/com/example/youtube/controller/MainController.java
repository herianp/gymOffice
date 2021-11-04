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
    public String chatbotCheck(@RequestParam String employee_name){
        if(employeeService.findByName(employee_name).isEmpty()){
            return "redirect:/";
        }
        Long id = employeeService.findByName(employee_name).get().getId();
        return "redirect:/chatbot/" + id;
    }


    @GetMapping("/chatbot/{id}")
    public String chatbot(Model model, @PathVariable Long id){
        model.addAttribute("messagesList",messagesService.findAllMessages());
        model.addAttribute("currentId",id);
        return "chatbot";
    }

    @PostMapping("/chatbot/createMessage/{id}")
    public String createMessage(@PathVariable Long id,
                                @RequestParam String text){
        messagesService.save(text,id);
        return "redirect:/chatbot/" + id;
    }

}
