package com.rajat.LearningManagementSystem.service;

import com.rajat.LearningManagementSystem.exception.ExamNotFoundException;
import com.rajat.LearningManagementSystem.exception.StudentNotEnrolledForSubjectException;
import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Exams;
import com.rajat.LearningManagementSystem.model.Students;

import java.util.List;

public interface ExamService {
    public Students studentExamRegisteration(Long studentId, Long examId) throws StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException;
    public Students removeStudentExamRegisteration(Long studentId, Long examId) throws StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException;

    public Exams createExam(Exams exams);
    public Exams findExamById(Long id) throws ExamNotFoundException;
    public List<Exams> findAllExams() ;
    public void deleteExamById(Long id) throws ExamNotFoundException;
    public Exams udpateExam(Exams exams, Long examId);
}
