package com.rajat.LearningManagementSystem.controller;

import com.rajat.LearningManagementSystem.model.Subjects;
import com.rajat.LearningManagementSystem.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectService subjectService;

    //Save subject in subject table
    @PostMapping
    public ResponseEntity<Subjects> saveSubjects(@RequestBody Subjects subjects) {
        return ResponseEntity.ok(subjectService.saveSubjects(subjects));
    }

    @GetMapping
    public List<Subjects> getAllSubjectList() {
        return subjectService.getAllSubjectDetails();
    }

    @GetMapping("/{subjectId}")
    public Subjects getSubjectDetailsById(@PathVariable Long subjectId) {
        return  subjectService.getSubjectsById(subjectId);
    }

    @PostMapping("/updateSubjects/{subjectId}")
    public ResponseEntity<Subjects> updateSubjects(@PathVariable Long subjectId, @RequestBody Subjects subjects) {
            Subjects subjectsById = subjectService.getSubjectsById(subjectId);
            subjectsById.setSubjectName(subjects.getSubjectName());
            return ResponseEntity.ok(subjectsById);
    }

    @DeleteMapping("/delete/{subjectId}")
    public void deleteSubjectById(@PathVariable Long subjectId) {
        subjectService.deleteSubjectById(subjectId);
    }


}
