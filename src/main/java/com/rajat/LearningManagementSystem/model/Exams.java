package com.rajat.LearningManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subjects subjectDetails;

    @ManyToMany
    @JoinTable(name = "student_exam", joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Students> enrolledStudents;

}
