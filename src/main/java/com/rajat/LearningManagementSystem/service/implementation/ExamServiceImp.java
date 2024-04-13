package com.rajat.LearningManagementSystem.service.implementation;

import com.rajat.LearningManagementSystem.exception.ExamNotFoundException;
import com.rajat.LearningManagementSystem.exception.StudentNotEnrolledForSubjectException;
import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Exams;
import com.rajat.LearningManagementSystem.model.Subjects;
import com.rajat.LearningManagementSystem.repository.ExamRepository;
import com.rajat.LearningManagementSystem.service.ExamService;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ExamServiceImp implements ExamService {
    public static final Logger log = LoggerFactory.getLogger(ExamServiceImp.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamRepository examRepository;

    public ExamServiceImp(StudentService studentService) {
        this.studentService = studentService;
    }
    @Override
    public Students studentExamRegisteration(Long studentId, Long examId) throws StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        Students student = studentService.getStudentDetailsById(studentId);
        Exams exam = findExamById(examId);
        Subjects subjectEnrolledByStudents = exam.getSubject();

        //Checking if student is enrolled for subject or not
        if(student.getEnrolledSubjects().contains(subjectEnrolledByStudents)) {
            exam.getEnrolledStudents().add(student);
            student.getRegisteredExams().add(exam);
            Students studentReg = studentService.updateStudentRecordById(studentId, student);
            Exams examReg = udpateExam(exam, examId);
            log.info("Student Registered For Exam [{}]", student.getStudentName() + "-->" + exam.getId() + examId);
            return student;
        } else {
            log.error("Student {} NOT Enrolled for Subject {}",
                    student.getStudentName(),
                    exam.getSubject());
            throw new StudentNotEnrolledForSubjectException(
                    "Student not enrolled for subject."
            );
        }
    }

    @Override
    public Students removeStudentExamRegisteration(Long studentId, Long examId) throws StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        Students student = studentService.getStudentDetailsById(studentId);
        Exams exam = findExamById(examId);
        Subjects subjectEnrolledByStudents = exam.getSubject();

        //Checking if student is enrolled for subject or not
        if(student.getEnrolledSubjects().contains(subjectEnrolledByStudents)) {
            exam.getEnrolledStudents().remove(student);
            student.getRegisteredExams().remove(exam);
            Students studentReg = studentService.updateStudentRecordById(studentId, student);
            Exams examReg = udpateExam(exam, examId);
            log.info("Remove student For Exam [{}]", student.getStudentName() + "-->" + exam.getId() + examId);
            return student;
        } else {
            log.error("Student {} NOT Enrolled for Subject {}",
                    student.getStudentName(),
                    exam.getSubject());
            throw new StudentNotEnrolledForSubjectException(
                    "Student not enrolled for subject."
            );
        }
    }

    @Override
    public Exams createExam(Exams exams) {
        return examRepository.save(exams);
    }

    @Override
    public Exams findExamById(Long id) {
        return examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException("Exam not found for given id "+ id));
    }

    @Override
    public List<Exams> findAllExams() {
        return examRepository.findAll();
    }

    @Override
    public void deleteExamById(Long id) throws ExamNotFoundException{
         examRepository.deleteById(id);
    }

    @Override
    public Exams udpateExam(Exams exams, Long examId) {
        Exams findExamById = findExamById(examId);
        findExamById.setSubject(exams.getSubject());
        findExamById.setEnrolledStudents(exams.getEnrolledStudents());
        return findExamById;
    }
}
