package org.example.demoapplication.controller;

import org.example.demoapplication.model.Student;
import org.example.demoapplication.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student added successfully !";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.badRequest().body("Student not found !");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        List<Student> students = studentService.searchStudents(name, minAge, maxAge);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) Integer age) {
        if (studentService.updateStudent(id, name, age)) {
            return ResponseEntity.ok("Student updated successfully");
        }
        return ResponseEntity.badRequest().build();
    }
}
