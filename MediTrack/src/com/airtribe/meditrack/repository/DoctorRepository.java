package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Doctor;
import java.util.*;

public class DoctorRepository {

    private final Map<String, Doctor> doctorMap = new HashMap<>();

    public void save(Doctor doctor) {
        /*
            This method saves a doctor object to the repository. 
        */
        doctorMap.put(doctor.getId(), doctor);
    }

    public Doctor findById(String id) {
        /*
            This method retrieves a doctor by their unique ID. It returns the doctor 
            object if found, or null if no doctor with the given ID exists in the repository.
        */
        return doctorMap.get(id);
    }

    public List<Doctor> findByName(String name) {
        /*
            This method searches for doctors whose names contain the given name 
            (case-insensitive) and returns a list of matching doctors.
        */
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctorMap.values()) {
            if (d.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(d);
            }
        }
        return result;
    }

    public List<Doctor> getAllDoctors() {
        /*
            This method returns a List of all doctors currently stored in the repository.
        */
        return new ArrayList<>(doctorMap.values());
    }

    public void delete(String id) {
        /*
            This method removes a doctor from the repository based on their unique ID. 
            If a doctor with the specified ID exists, it will be removed from the doctorMap.
        */
        doctorMap.remove(id);
    }

    private void persist() {
        // Implement file or database persistence here
    }

    private Map<String, Doctor> load() {
        // Implement file or database loading here
        return new HashMap<>();
    }
}
