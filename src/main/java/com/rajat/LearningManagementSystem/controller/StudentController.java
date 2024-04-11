package com.rajat.LearningManagementSystem.controller;

import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.repository.StudentRepository;
import com.rajat.LearningManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Save student details
    @PostMapping
    public ResponseEntity<Students> createStudent(@RequestBody Students students) {
        return ResponseEntity.ok(studentService.createStudent(students));
    }

    //Get all students details
    @GetMapping
    public ResponseEntity<List<Students>> getAllStudentsDetails() {
        return ResponseEntity.ok(studentService.getAllStudentsDetails());
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<Students> getStudentDetailsById(@PathVariable Long student_id) {
        Students studentsDetailsById = studentService.getStudentDetailsById(student_id);
        return ResponseEntity.ok(studentsDetailsById);
    }

    @DeleteMapping("/{student_id}")
    public void deleteStudentById(@PathVariable Long student_id) {
        studentService.deleteStudentById(student_id);
    }

    @PutMapping("/{student_id}")
    public Students updateStudentRecordById(@PathVariable Long student_id, @RequestBody Students student) {
        return studentService.updateStudentRecordById(student_id, student);
    }
}
