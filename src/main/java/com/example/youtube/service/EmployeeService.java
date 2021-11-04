package com.example.youtube.service;

import com.example.youtube.entity.Employee;
import com.example.youtube.entity.Message;
import com.example.youtube.models.EmployeeAndMessageDTO;
import com.example.youtube.models.EmployeeDto;
import com.example.youtube.models.MessageDto;
import com.example.youtube.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto save(EmployeeAndMessageDTO employeeandMessageDto){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setPassword(employeeandMessageDto.getPassword());
        employeeDto.setName(employeeandMessageDto.getName());

        List<String> stringList = new ArrayList<>();
        stringList.add(employeeandMessageDto.getText());

        employeeDto.setMessages(stringList);

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPassword(employeeDto.getPassword());

//        Message message = new Message();
//        message.setText(employeeandMessageDto.getText());
//
//        employee.addMessage(message);
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

    public Optional<EmployeeDto> findByName(String employee_name) {
        if (employeeRepository.findByName(employee_name) == null){
            return null;
        }
        Optional<EmployeeDto> employeeDto = Optional.of(new EmployeeDto());
        Employee employee = employeeRepository.findByName(employee_name);

        employeeDto.get().setName(employee.getName());
        employeeDto.get().setId(employee.getId());
        employeeDto.get().setPassword(employee.getPassword());
        List<String> listOfMessages = new ArrayList<>();
        for(Message message : employee.getMessages()){
            listOfMessages.add(message.getText());
        }

        employeeDto.get().setMessages(listOfMessages);
        return employeeDto;
    }

    public boolean diacriticHandler(String name) {
        String diacritic = "¹²³áàâãäåāăąÀÁÂÃÄÅĀĂĄÆćčç©ĆČÇĐÐèéêёëēĕėęěÈÊËЁĒĔĖĘĚ€ğĞıìíîïìĩīĭÌÍÎÏЇÌĨĪĬłŁńňñŃŇÑòóôõöōŏőøÒÓÔÕÖŌŎŐØŒř®ŘšşșßŠŞȘùúûüũūŭůÙÚÛÜŨŪŬŮýÿÝŸžżźŽŻŹŠšČčŘřŽžóÓ";

        for (Character c : diacritic.toCharArray()){
            if (name.contains(c.toString())) {
                return true;
            }
        }
        return false;
    }
}
