package com.rajat.LearningManagementSystem.service.implementation;

import com.rajat.LearningManagementSystem.model.Subjects;
import com.rajat.LearningManagementSystem.repository.SubjectRepository;
import com.rajat.LearningManagementSystem.service.SubjectService;
import com.rajat.LearningManagementSystem.exception.SubjectNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SubjectServiceImp implements SubjectService {
    private SubjectRepository subjectRepository;

    @Override
    public Subjects saveSubjects(Subjects subjects) {
        return subjectRepository.save(subjects);
    }

    @Override
    public Subjects getSubjectsById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("Subject not found with given id "+ subjectId));
    }

    @Override
    public List<Subjects> getAllSubjectDetails() {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public void deleteSubjectById(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public Subjects updateSubjects(Long subjectId, Subjects subjects) {
        Subjects setNewSub = getSubjectsById(subjectId);
        setNewSub.setSubjectName(subjects.getSubjectName());

        return setNewSub;
    }

    @Override
    public Subjects listOfEnrolledSubjectByStudents(Long student_id) {
        return null;
    }
}
