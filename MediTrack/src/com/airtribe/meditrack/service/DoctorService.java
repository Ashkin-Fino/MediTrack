package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.repository.DoctorRepository;

import java.util.List;

public class DoctorService implements Searchable<Doctor> {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public void registerDoctor(Doctor doctor) {
        repository.save(doctor);
    }

    @Override
    public Doctor searchById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Doctor> searchByName(String name) {
        return repository.findByName(name);
    }

    public Doctor chooseDoctor(List<Doctor> doctors) {

        if (doctors == null || doctors.isEmpty()) {
            return null;
        }

        return doctors.get(0);
    }
}