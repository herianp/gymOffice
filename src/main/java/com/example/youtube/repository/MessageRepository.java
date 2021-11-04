package com.example.youtube.repository;

import com.example.youtube.entity.Employee;
import com.example.youtube.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
