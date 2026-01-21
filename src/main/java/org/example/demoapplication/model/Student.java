package org.example.demoapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @Min(value = 1,message = "Age must be greater than 1")
    @Max(value = 150,message = "Age must be realistic")
    private int age;
public Student(){}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public void setId1(Long id1) {
        this.id = id1;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
}
