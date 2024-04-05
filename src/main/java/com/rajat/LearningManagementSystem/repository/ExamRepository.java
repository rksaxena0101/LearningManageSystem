package com.rajat.LearningManagementSystem.repository;

import com.rajat.LearningManagementSystem.model.Exams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exams, Long> {
}
