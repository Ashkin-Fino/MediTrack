package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Doctor;

import java.io.File;
import java.io.IOException;
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

    public void persist() {
        // ObjectMapper objectMapper = new ObjectMapper();
        // try {
        //     objectMapper.writeValue(new File(FILE_PATH), doctorMap.values());
        // } catch (IOException e) {
        //     System.err.println("Error while saving doctors to file: " + FILE_PATH);
        //     e.printStackTrace();
        // }
    }

    public void load() {
        // ObjectMapper objectMapper = new ObjectMapper();
        // try {
        //     List<Doctor> doctors = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Doctor>>() {});
        //     doctorMap.clear();
        //     for (Doctor doctor : doctors) {
        //         doctorMap.put(doctor.getId(), doctor);
        //     }
        // } catch (IOException e) {
        //     System.err.println("Error while loading doctors from file: " + FILE_PATH);
        //     e.printStackTrace();
        // }
    }
}
