package com.rajat.LearningManagementSystem.controller;

import com.rajat.LearningManagementSystem.service.ExamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    private ExamService examService;

}
