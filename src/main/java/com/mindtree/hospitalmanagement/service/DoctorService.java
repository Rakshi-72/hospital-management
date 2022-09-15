package com.mindtree.hospitalmanagement.service;

import com.mindtree.hospitalmanagement.model.Doctor;
import com.mindtree.hospitalmanagement.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo repo;

    /**
     * > If the doctor is already present, return true. Otherwise, add the doctor to the repository and return false
     *
     * @param doc The doctor object that is to be added to the database.
     * @return A boolean value.
     */
    public boolean addDoctor(Doctor doc) {
        if (isPresent(doc.getId()))
            return true;
        repo.save(doc);
        return false;
    }

    /**
     * Get all doctors from the database, sort them, and return them.
     *
     * @return A list of doctors
     */
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = repo.findAll();
        return sortDoctors(doctors);
    }

    /**
     * > If the id exists in the database, return true. Otherwise, return false
     *
     * @param id The id of the entity to be checked for existence.
     * @return A boolean value.
     */
    public boolean isPresent(int id) {
        return repo.existsById(id);
    }

    /**
     * Get the doctor with the given id from the database.
     *
     * @param id The id of the doctor you want to get.
     * @return A Doctor object
     */
    public Doctor getDocById(int id) {
        return repo.findById(id).get();
    }

    /**
     * Sort the doctors by salary, and if the salaries are equal, sort by name
     *
     * @param doctors a list of doctors
     * @return A list of doctors sorted by salary and then by name.
     */
    public List<Doctor> sortDoctors(List<Doctor> doctors) {
        Collections.sort(doctors, (a, b) -> {
            if (a.getSalary() - b.getSalary() == 0) {
                return a.getName().compareTo(b.getName());
            }
            return a.getSalary() - b.getSalary();
        });
        return doctors;
    }
}
