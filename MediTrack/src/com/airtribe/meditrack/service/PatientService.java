package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.repository.PatientRepository;

import java.util.List;

public class PatientService implements Searchable<Patient> {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void registerPatient(Patient patient) {
        repository.save(patient);
    }

    @Override
    public Patient searchById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Patient> searchByName(String name) {
        return repository.findByName(name);
    }

    public Patient choosePatient(List<Patient> patients) {

        if (patients == null || patients.isEmpty()) {
            return null;
        }

        return patients.get(0);
    }
}