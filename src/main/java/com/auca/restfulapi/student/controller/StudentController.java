package com.auca.restfulapi.student.controller;

import com.auca.restfulapi.student.model.Student;
import com.auca.restfulapi.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        return ResponseEntity.ok(studentRepository.findByMajorIgnoreCase(major));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        return ResponseEntity.ok(studentRepository.findByGpaGreaterThanEqual(gpa));
    }

    @PostMapping
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        if (!studentRepository.findByEmailIgnoreCase(student.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Student with this email already exists", "email", student.getEmail()));
        }
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setStudentId(studentId);
            Student saved = studentRepository.save(updatedStudent);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }
}
