package com.example.youtube.service;

import com.example.youtube.entity.Employee;
import com.example.youtube.entity.Message;
import com.example.youtube.models.MessageDto;
import com.example.youtube.repository.EmployeeRepository;
import com.example.youtube.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessagesService {

    private final MessageRepository messageRepository;
    private final EmployeeRepository employeeRepository;

    public MessagesService(MessageRepository messageRepository, EmployeeRepository employeeRepository) {
        this.messageRepository = messageRepository;
        this.employeeRepository = employeeRepository;
    }

    public void newMessageToEmployee(MessageDto messageDto,Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Message message = new Message();
        message.setText(messageDto.getText());
        employee.get().addMessage(message);
        employeeRepository.save(employee.get());
    }
}
