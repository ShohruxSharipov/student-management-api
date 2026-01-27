package org.example.demoapplication.course;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.demoapplication.student.StudentEntity;

@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String name;
    @Min(1)
    Integer credits;
    @ManyToOne
    @JoinColumn(name = "student_id")
    StudentEntity studentEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public StudentEntity getStudent() {
        return studentEntity;
    }

    public void setStudent(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
