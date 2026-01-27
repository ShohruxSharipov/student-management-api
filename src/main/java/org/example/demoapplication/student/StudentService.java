package org.example.demoapplication.student;

import org.example.demoapplication.exceptions.student.StudentNotFoundException;
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
        List<StudentEntity> studentEntities = repository.findAll();
        for (StudentEntity studentEntity : studentEntities) {
            responses.add(new StudentResponse(studentEntity.getId(), studentEntity.getName(), studentEntity.getAge()));
        }
        return responses;
    }

    public StudentResponse getStudent(Long id) {
        StudentEntity studentEntity = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return new StudentResponse(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getAge()
        );
    }


    public void addStudent(StudentCreateRequest request) {
        repository.save(new StudentEntity(request.getName(), request.getAge()));
    }

    public Boolean deleteStudent(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<StudentResponse> searchStudents(String name, Integer minAge, Integer maxAge) {

        return repository.search(name,minAge,maxAge).stream().map(studentEntity ->
                new StudentResponse(studentEntity.getId(), studentEntity.getName(), studentEntity.getAge())).toList();
    }

    public Boolean updateStudent(Long id, StudentUpdateRequest request) {
        StudentEntity studentEntity = repository.getStudentById(id);
        if (studentEntity == null) return false;

        if (request.getName() != null) studentEntity.setName(request.getName());
        if (request.getAge() != null) studentEntity.setAge(request.getAge());
        repository.save(studentEntity);
        return true;

    }
}
