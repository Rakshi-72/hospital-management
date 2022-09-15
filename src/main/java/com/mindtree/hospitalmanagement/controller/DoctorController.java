package com.mindtree.hospitalmanagement.controller;

import com.mindtree.hospitalmanagement.exception.DoctorAlreadyPresent;
import com.mindtree.hospitalmanagement.model.Doctor;
import com.mindtree.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody Doctor doctor) {
        if (doctorService.addDoctor(doctor))
            return new ResponseEntity<>(new DoctorAlreadyPresent().getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/load")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/load/{id}")
    public ResponseEntity loadDoctor(@PathVariable int id) {
        if (doctorService.isPresent(id))
            return ResponseEntity.ok(doctorService.getDocById(id));
        return new ResponseEntity<>("no doctor with the given id", HttpStatus.NOT_FOUND);
    }
}
