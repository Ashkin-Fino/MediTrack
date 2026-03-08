package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import java.util.*;

public class PatientRepository {

    private final Map<String, Patient> patientMap = new HashMap<>();

    public void save(Patient patient) {
        /*
            This method saves a patient object to the repository. 
        */
        patientMap.put(patient.getId(), patient);
    }

    public Patient findById(String id) {
        /*
            This method retrieves a patient by their unique ID. It returns the patient 
            object if found, or null if no patient with the given ID exists in the repository.
        */
        return patientMap.get(id);
    }

    public List<Patient> findByName(String name) {
        /*
            This method searches for patients whose names match the given name 
            (case-insensitive) and returns a list of matching patients.
        */
        List<Patient> result = new ArrayList<>();
        for (Patient p : patientMap.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Patient> getAllPatients() {
        /*
            This method returns a List of all patients currently stored in the repository.
        */
        return new ArrayList<>(patientMap.values());
    }

    public void delete(String id) {
        /*
            This method removes a patient from the repository based on their unique ID. 
            If a patient with the specified ID exists, it will be removed from the patientMap.
        */
        patientMap.remove(id);
    }

    public void persist() {
        // Implement file or database persistence here
    }

    public void load() {
        // Implement file or database loading here
    }
}
