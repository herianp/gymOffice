package com.example.youtube.service;

import com.example.youtube.entity.Employee;
import com.example.youtube.entity.Message;
import com.example.youtube.models.EmployeeDto;
import com.example.youtube.models.MessageDto;
import com.example.youtube.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto save(EmployeeDto employeeDto, MessageDto messageDto){

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPassword(employeeDto.getPassword());

        Message message = new Message();
        message.setText(messageDto.getText());

        employee.addMessage(message);
        Employee savedEmployee = employeeRepository.save(employee);

        employeeDto.setId(savedEmployee.getId());

        return employeeDto;
    }

    public List<EmployeeDto> findAllEmployee() {
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee e : employeeRepository.findAll()){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(e.getId());
            employeeDto.setName(e.getName());
            employeeDto.setPassword(e.getPassword());

            List<String> messagesDto = new ArrayList<>();
            for(Message m : e.getMessages()){
                messagesDto.add(m.getText());
            }
            employeeDto.setMessages(messagesDto);
            dtoList.add(employeeDto);
        }
        return dtoList;
    }
}
