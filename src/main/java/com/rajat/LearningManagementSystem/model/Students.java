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
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "students")
    private List<Subjects> enrolledSubjects;

    @ManyToMany
    @JoinTable(name = "student_exam", joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private List<Exams> registeredExams;

//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getName() {
//        return name;
//    }
}
