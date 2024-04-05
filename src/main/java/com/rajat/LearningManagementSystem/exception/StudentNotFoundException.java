package com.rajat.LearningManagementSystem.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
