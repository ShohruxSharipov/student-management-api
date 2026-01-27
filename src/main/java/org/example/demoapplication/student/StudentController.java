package org.example.demoapplication.student;

import jakarta.validation.Valid;
import org.example.demoapplication.student.dto.StudentCreateRequest;
import org.example.demoapplication.student.dto.StudentResponse;
import org.example.demoapplication.student.dto.StudentUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        StudentResponse student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Void> addStudent(@Valid @RequestBody StudentCreateRequest student) {
        studentService.addStudent(student);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        List<StudentResponse> students = studentService.searchStudents(name, minAge, maxAge);
        return ResponseEntity.ok(students);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id,
                                               @Valid @RequestBody StudentUpdateRequest request) {

        return studentService.updateStudent(id, request)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
