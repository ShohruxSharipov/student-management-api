package org.example.demoapplication.dto.response;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentResponse {
    @Min(1)
    @NotBlank
    private int id;
    @NotBlank
    private String name;
    @Min(value = 1,message = "Age can not be less than 1")
    @Max(value = 150,message = "Age can not be greater than 150")
    private int age;

    public StudentResponse(int id,String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getID(){return id;}

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
