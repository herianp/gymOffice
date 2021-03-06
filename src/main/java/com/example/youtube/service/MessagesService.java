package com.example.youtube.service;

import com.example.youtube.entity.Employee;
import com.example.youtube.entity.Message;
import com.example.youtube.models.EmployeeDto;
import com.example.youtube.models.MessageDto;
import com.example.youtube.repository.EmployeeRepository;
import com.example.youtube.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<MessageDto> findAllMessages() {
        List<MessageDto> dtoList = new ArrayList<>();
        for (Message m : messageRepository.findAll()){
            MessageDto messageDto = new MessageDto();
            messageDto.setId(m.getId());
            messageDto.setText(m.getText());
            messageDto.setDate(m.getDate().toString());
            messageDto.setEmployee(m.getEmployee().getName());
            dtoList.add(messageDto);
        }
        return dtoList;
    }

    public void save(String text, Long id) {
        Message message = new Message();
        message.setText(text);
        message.setEmployee(employeeRepository.findById(id).get());
        employeeRepository.findById(id).get().addMessage(message);
        employeeRepository.save(employeeRepository.findById(id).get());
    }
}
