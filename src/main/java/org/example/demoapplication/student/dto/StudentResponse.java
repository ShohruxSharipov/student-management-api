package org.example.demoapplication.student.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentResponse {
    @Min(1)
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @Min(value = 1,message = "Age can not be less than 1")
    @Max(value = 150,message = "Age can not be greater than 150")
    private int age;

    public StudentResponse(Long id,String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getID(){return id;}

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
