package com.rajat.LearningManagementSystem.service;

import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.model.Subjects;

import java.util.List;

public interface SubjectService {
    Subjects saveSubjects(Subjects subjects);
    Subjects getSubjectsById(Long subjectId);
    List<Subjects> getAllSubjectDetails();
    void deleteSubjectById(Long subjectId);
    Subjects updateSubjects(Long subjectId, Subjects subjects);
    public Students EnrolledSubjectByStudents(Long student_id, Long subject_id);
    public Students UnenrolledSubjectByStudents(Long student_id, Long subject_id);
}
