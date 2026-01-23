package org.example.demoapplication.student.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentCreateRequest {
    @NotBlank(message = "Name can not be blank or empty !")
    private String name;
    @Min(value = 1,message = "Age can not be less than 1")
    @Max(value = 150,message = "Age can not be greater than 150")
    private int age;

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
