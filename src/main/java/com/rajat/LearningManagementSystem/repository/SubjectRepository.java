package com.rajat.LearningManagementSystem.repository;

import com.rajat.LearningManagementSystem.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
}
