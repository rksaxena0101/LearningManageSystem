package com.rajat.LearningManagementSystem.service;

import com.rajat.LearningManagementSystem.model.Students;

import java.util.List;

public interface StudentService {
    Students createStudent(Students students);
    List<Students> getAllStudentsDetails();
    Students getStudentDetailsById(Long student_id);
    void deleteStudentById(Long student_id);
    Students updateStudentRecordById(Long student_id, Students student);
}
