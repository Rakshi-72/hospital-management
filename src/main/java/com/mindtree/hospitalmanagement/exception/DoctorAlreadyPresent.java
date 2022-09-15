package com.mindtree.hospitalmanagement.exception;

public class DoctorAlreadyPresent extends Exception {
    @Override
    public String getMessage() {
        return "doctor with the given id is already present";
    }
}
