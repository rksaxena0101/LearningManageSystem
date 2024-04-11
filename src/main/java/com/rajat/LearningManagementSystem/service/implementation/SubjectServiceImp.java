package com.rajat.LearningManagementSystem.service.implementation;

import com.rajat.LearningManagementSystem.exception.StudentNotEnrolledForSubjectException;
import com.rajat.LearningManagementSystem.exception.StudentNotFoundException;
import com.rajat.LearningManagementSystem.model.Students;
import com.rajat.LearningManagementSystem.model.Subjects;
import com.rajat.LearningManagementSystem.repository.SubjectRepository;
import com.rajat.LearningManagementSystem.service.StudentService;
import com.rajat.LearningManagementSystem.service.SubjectService;
import com.rajat.LearningManagementSystem.exception.SubjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;



@Service
public class SubjectServiceImp implements SubjectService {
    public static final Logger log = LoggerFactory.getLogger(SubjectServiceImp.class);
    private SubjectRepository subjectRepository;
    private StudentService studentService;

    @Autowired
    public SubjectServiceImp(SubjectRepository subjectRepository, StudentService studentService) {
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
    }

    //Create or save subjects.
    @Override
    public Subjects saveSubjects(Subjects subjects) {
        return subjectRepository.save(subjects);
    }

    //get particular subject by subjectId
    @Override
    public Subjects getSubjectsById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("Subject not found with given id "+ subjectId));
    }

    //fetch all subject details
    @Override
    public List<Subjects> getAllSubjectDetails() {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, "subjectName"));
    }

    //Delete subjects by subjectId.
    @Override
    public void deleteSubjectById(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    //Update subjects
    @Override
    public Subjects updateSubjects(Long subjectId, Subjects subjects) {
        Subjects setNewSub = getSubjectsById(subjectId);
        setNewSub.setSubjectName(subjects.getSubjectName());

        return subjectRepository.save(setNewSub);
    }

    //Enrolling subjects for related students.
    @Override
    public Students EnrolledSubjectByStudents(Long student_id, Long subject_id) throws SubjectNotFoundException, StudentNotFoundException {
        Students students = studentService.getStudentDetailsById(student_id);
        Subjects subjects = getSubjectsById(subject_id);

        if(!subjects.getRegisteredStudents().contains(students)) {
            subjects.getRegisteredStudents().add(students);
            students.getEnrolledSubjects().add(subjects);
            Students updatedStudentsRecords = studentService.updateStudentRecordById(student_id, students);
            Subjects updatedSubjectsRecords = updateSubjects(subject_id, subjects);
            log.info("Successfully enrolled student [{}] to subjects [{}]", updatedStudentsRecords.getStudentName(),updatedSubjectsRecords.getSubjectName());
            return updatedStudentsRecords;
        } else {
            log.error("Student [{}] is already enrolled with subjects [{}]",
                    students.getStudentName(),
                    subjects.getSubjectName());
            return students;
        }
    }

    //Un-enrolling subjects for related students.
    @Override
    public Students UnenrolledSubjectByStudents(Long student_id, Long subject_id) throws SubjectNotFoundException, StudentNotFoundException {
        Students students = studentService.getStudentDetailsById(student_id);
        Subjects subjects = getSubjectsById(subject_id);

        if(subjects.getRegisteredStudents().contains(students)) {
            subjects.getRegisteredStudents().remove(students);
            students.getEnrolledSubjects().remove(subjects);
            Students updatedStudentsRecords = studentService.updateStudentRecordById(student_id, students);
            Subjects updatedSubjectsRecords = updateSubjects(subject_id, subjects);
            log.info("Successfully un-enrolled student [{}] to subjects [{}]", updatedStudentsRecords.getStudentName(),updatedSubjectsRecords.getSubjectName());
            return updatedStudentsRecords;
        } else {
            log.error("Student [{}] is not enrolled with subjects [{}].",
                    students.getStudentName(),
                    subjects.getSubjectName());
            throw new StudentNotEnrolledForSubjectException(
                    "Student is not enrolled for these subjects."
            );
        }
    }
}
