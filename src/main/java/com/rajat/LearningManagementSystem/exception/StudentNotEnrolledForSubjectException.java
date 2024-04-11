package com.rajat.LearningManagementSystem.exception;

public class StudentNotEnrolledForSubjectException extends RuntimeException{
    public StudentNotEnrolledForSubjectException(String msg) {
        super(msg);
    }
}
