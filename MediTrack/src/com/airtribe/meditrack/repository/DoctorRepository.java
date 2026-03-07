package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Doctor;
import java.util.*;

public class DoctorRepository {

    private final Map<String, Doctor> doctorMap = new HashMap<>();

    public void save(Doctor doctor) {
        doctorMap.put(doctor.getId(), doctor);
    }

    public Doctor findById(String id) {
        return doctorMap.get(id);
    }
    public List<Doctor> findByName(String name) {
        List<Doctor> result = new ArrayList<>();


        for (Doctor d : doctorMap.values()) {
            if (d.getName().equalsIgnoreCase(name)) {
                result.add(d);
            }
        }

        return result;
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorMap.values());
    }

    public void delete(String id) {
        doctorMap.remove(id);
    }
}
