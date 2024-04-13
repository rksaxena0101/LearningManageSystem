package com.rajat.LearningManagementSystem.controller;

import com.rajat.LearningManagementSystem.exception.ExamNotFoundException;
import com.rajat.LearningManagementSystem.exception.StudentNotEnrolledForSubjectException;
import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Exams;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    private ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @PostMapping
    public ResponseEntity<Exams> saveExams(Exams exams) {
         return ResponseEntity.ok(examService.createExam(exams));
    }

    @GetMapping
    public ResponseEntity<List<Exams>> allExamsListForStudents() {
        return ResponseEntity.ok(examService.findAllExams());
    }

    @GetMapping("/getExamListById/{id}")
    public ResponseEntity<Exams> getExamListById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.findExamById(id));
    }

    @DeleteMapping("/deleteExamById")
    public void deleteExamById(@PathVariable Long id) {
        examService.deleteExamById(id);
        ResponseEntity.ok();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Exams> updateExamDetails(@RequestBody Exams exams, @PathVariable Long id) {
        return ResponseEntity.ok(examService.udpateExam(exams,id));
    }

    @PutMapping("/enroll_exam/{studentId}/{examId}")
    public ResponseEntity<Students> enrollStudentForExam(@PathVariable Long studentId, @PathVariable Long examId) throws
            StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        return ResponseEntity.ok(examService.studentExamRegisteration(studentId, examId));
    }

    @PutMapping("/unEnroll_exam/{studentId}/{examId}")
    public ResponseEntity<Students> unEnrollStudentForExam(@PathVariable Long studentId, @PathVariable Long examId) throws
            StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        return ResponseEntity.ok(examService.removeStudentExamRegisteration(studentId, examId));
    }


}

