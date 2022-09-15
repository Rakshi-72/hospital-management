package com.mindtree.hospitalmanagement.repository;

import com.mindtree.hospitalmanagement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
}
