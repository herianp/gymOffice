package com.example.youtube.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAndMessageDTO {

    private Long id;
    private String name;
    private String password;
    private String text;
}
