package com.rajat.LearningManagementSystem.service.implementation;

import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.repository.StudentRepository;
import com.rajat.LearningManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Save student details
    @Override
    public Students createStudent(Students students) {
        return studentRepository.save(students);
    }

    //Get all students details
    @Override
    public List<Students> getAllStudentsDetails() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "studentName"));
    }

    //Get particular student details basis on student id.
    @Override
    public Students getStudentDetailsById(Long student_id) {
        return studentRepository.findById(student_id).orElseThrow(() -> new StudentNotFoundException("There is no student exist with id "+ student_id));
    }

    //Delete student based on student id
    @Override
    public void deleteStudentById(Long student_id) {
        studentRepository.deleteById(student_id);
    }

    //Updating name, Enrolled subjects and registered exams
    @Override
    public Students updateStudentRecordById(Long student_id, Students student) {
        Students students = getStudentDetailsById(student_id);
        students.setStudentName(student.getStudentName());
        students.setEnrolledSubjects(student.getEnrolledSubjects());
        students.setRegisteredExams(student.getRegisteredExams());
        return studentRepository.save(students);
    }
}
