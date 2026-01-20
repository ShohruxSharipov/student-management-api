package org.example.demoapplication.service;

import org.example.demoapplication.dto.request.StudentCreateRequest;
import org.example.demoapplication.dto.response.StudentResponse;
import org.example.demoapplication.dto.update.StudentUpdateRequest;
import org.example.demoapplication.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    static int idGen = 1;
    private int generateID(){
        return idGen++;
    }
    public List<StudentResponse> getStudents() {
        List<StudentResponse> responses = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            responses.add(new StudentResponse(student.getId(),student.getName(), student.getAge()));
        }
        return responses;
    }

    public StudentResponse getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return new StudentResponse(student.getId(),student.getName(), student.getAge());
            }
        }
        return null;
    }

    public void addStudent(StudentCreateRequest request) {
        Student student = new Student(generateID(), request.getName(), request.getAge());

        students.add(student);
    }

    public Boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId() == id) {
                students.remove(student);
                return true;
            }

        }
        return false;
    }

    public List<StudentResponse> searchStudents(String name, Integer minAge, Integer maxAge) {
        List<StudentResponse> filteredStudents = new ArrayList<>();

        String nameFilter = "";
        Integer minAgeFilter = Integer.MIN_VALUE;
        Integer maxAgeFilter = Integer.MAX_VALUE;
        if (name != null) {
            nameFilter = name.trim().toLowerCase();
        }
        if (minAge != null) {
            minAgeFilter = minAge;
        }
        if (maxAge != null) {
            maxAgeFilter = maxAge;
        }
        for (Student student : students) {
            if (student.getAge() >= minAgeFilter && student.getAge() <= maxAgeFilter) {
                if (student.getName().toLowerCase().contains(nameFilter)) {
                    filteredStudents.add(new StudentResponse(student.getId(),student.getName(),student.getAge()));
                }
            }
        }

        return filteredStudents;
    }

    public Boolean updateStudent(int id, StudentUpdateRequest request) {
        for (Student student : students) {
            if (student.getId() == id) {
                if (request.getName() != null) student.setName(request.getName());
                if (request.getAge() != null) student.setAge(request.getAge());
                return true;
            }

        }
        return false;

    }
}
