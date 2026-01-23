package org.example.demoapplication.student;

import org.example.demoapplication.student.dto.StudentCreateRequest;
import org.example.demoapplication.student.dto.StudentResponse;
import org.example.demoapplication.student.dto.StudentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    StudentRepository repository;

    StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentResponse> getStudents() {
        List<StudentResponse> responses = new ArrayList<>();
        List<Student> students = repository.findAll();
        for (Student student : students) {
            responses.add(new StudentResponse(student.getId(), student.getName(), student.getAge()));
        }
        return responses;
    }

    public StudentResponse getStudent(Long id) {
        Student student = repository.getStudentById(id);
        if (student != null) {
            return new StudentResponse(student.getId(), student.getName(), student.getAge());
        }
        return null;
    }

    public void addStudent(StudentCreateRequest request) {
        repository.save(new Student(request.getName(), request.getAge()));
    }

    public Boolean deleteStudent(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<StudentResponse> searchStudents(String name, Integer minAge, Integer maxAge) {

        return repository.search(name,minAge,maxAge).stream().map(student ->
                new StudentResponse(student.getId(),student.getName(),student.getAge())).toList();
    }

    public Boolean updateStudent(Long id, StudentUpdateRequest request) {
        Student student = repository.getStudentById(id);
        if (student == null) return false;

        if (request.getName() != null) student.setName(request.getName());
        if (request.getAge() != null) student.setAge(request.getAge());
        repository.save(student);
        return true;

    }
}
