package com.rajat.LearningManagementSystem.controller;

import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.exception.SubjectNotFoundException;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.model.Subjects;
import com.rajat.LearningManagementSystem.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    //Save subject in subject table
    @PostMapping
    public ResponseEntity<Subjects> saveSubjects(@RequestBody Subjects subjects) {
        return ResponseEntity.ok(subjectService.saveSubjects(subjects));
    }

    @GetMapping
    public ResponseEntity<List<Subjects>> getAllSubjectList() {
        return ResponseEntity.ok(subjectService.getAllSubjectDetails());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subjects> getSubjectDetailsById(@PathVariable Long subjectId) {
        return  ResponseEntity.ok(subjectService.getSubjectsById(subjectId));
    }

    @PutMapping("/updateSubjects/{subjectId}")
    public ResponseEntity<Subjects> updateSubjects(@PathVariable Long subjectId, @RequestBody Subjects subjects) {
            return ResponseEntity.ok(subjectService.updateSubjects(subjectId, subjects));
    }

    @DeleteMapping("/delete/{subject_Id}")
    public void deleteSubjectById(@PathVariable Long subject_Id) {
        subjectService.deleteSubjectById(subject_Id);
    }

    @PutMapping("/enroll_student_to_subjects/{student_id}/{subject_id}")
    public ResponseEntity<?> enrolledStudentToSubjects(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) throws StudentNotFoundException, SubjectNotFoundException {
        Students students = subjectService.EnrolledSubjectByStudents(studentId,subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PutMapping("/un-enroll_student_to_subjects/{student_id}/{subject_id}")
    public ResponseEntity<?> unEnrolledStudentToSubjects(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) throws StudentNotFoundException, SubjectNotFoundException {
        Students students = subjectService.UnenrolledSubjectByStudents(studentId,subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }


}
