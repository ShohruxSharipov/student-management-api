package org.example.demoapplication.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentUpdateRequest {
    @NotBlank
    private String name;
    @Min(value = 1, message = "Age must be positive !")
    private int age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
