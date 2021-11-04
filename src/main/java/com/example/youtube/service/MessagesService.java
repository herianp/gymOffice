package com.example.youtube.service;

import com.example.youtube.entity.Employee;
import com.example.youtube.models.EmployeeDto;
import com.example.youtube.models.MessageDto;
import com.example.youtube.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private final MessageRepository messageRepository;

    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

}
