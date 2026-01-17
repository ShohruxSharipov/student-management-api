package org.example.demoapplication.service;

import org.example.demoapplication.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Boolean deleteStudent(int id) {
        Student student = getStudent(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    public List<Student> searchStudents(String name, Integer minAge, Integer maxAge) {
        List<Student> filteredStudents = new ArrayList<>();

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
                    filteredStudents.add(student);
                }
            }
        }

        return filteredStudents;
    }

    public Boolean updateStudent(int id, String name, Integer age) {
        Student student = getStudent(id);
        if (student == null) {
            return false;
        }
        if (name != null) student.setName(name);

        if (age != null) student.setAge(age);

        return true;

    }
}
