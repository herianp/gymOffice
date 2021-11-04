package com.example.youtube.controller;

import com.example.youtube.models.EmployeeAndMessageDTO;
import com.example.youtube.models.MessageDto;
import com.example.youtube.service.EmployeeService;
import com.example.youtube.service.MessagesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private final EmployeeService employeeService;
    private final MessagesService messagesService;

    public MainController(EmployeeService employeeService, MessagesService messagesService) {
        this.employeeService = employeeService;
        this.messagesService = messagesService;
    }

    @GetMapping
    public String mainPage(Model model,@RequestParam(required = false) String error_message,
                           @RequestParam(required = false) String diacriticError){
        model.addAttribute("employeeList",employeeService.findAllEmployee());

        if (error_message != null){
            model.addAttribute("error_message",error_message);
        } else if (diacriticError != null){
            model.addAttribute("diacriticError",diacriticError);
        }
        return "index";
    }

    @PostMapping("/employee")
    public String createEmployee(@ModelAttribute EmployeeAndMessageDTO employeeAndMessageDTO,
                                 RedirectAttributes redirectAttributes){

        if(employeeService.diacriticHandler(employeeAndMessageDTO.getName())){
            redirectAttributes.addAttribute("diacriticError","Please don't use diacritic");
            return "redirect:/";
        }
        if (employeeService.save(employeeAndMessageDTO) == null){
            redirectAttributes.addAttribute("diacriticError","Employee already exists");
            return "redirect:/";
        }

        return "redirect:/";
    }

//    @PostMapping("/employee/message/{id}")
//    public String createMessage(@PathVariable Long id,
//                                @ModelAttribute MessageDto messageDto){
//        messagesService.newMessageToEmployee(messageDto,id);
//        return "redirect:/";
//    }

    @GetMapping("/chatbot")
    public String chatbotCheck(@RequestParam String employee_name,
                               RedirectAttributes redirectAttributes){
        if(employeeService.findByName(employee_name) == null){
            redirectAttributes.addAttribute("error_message","You have to use real Employee");
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
