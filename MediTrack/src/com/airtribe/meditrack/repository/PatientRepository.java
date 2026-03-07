package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Patient;
import java.util.*;

public class PatientRepository {

    private final Map<String, Patient> patientMap = new HashMap<>();

    public void save(Patient patient) {
        patientMap.put(patient.getId(), patient);
    }

    public Patient findById(String id) {
        return patientMap.get(id);
    }

    public List<Patient> findByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patientMap.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    public void delete(String id) {
        patientMap.remove(id);
    }
}
