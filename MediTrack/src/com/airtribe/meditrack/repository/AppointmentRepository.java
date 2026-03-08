package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;

import java.util.*;

public class AppointmentRepository {

    private final Map<String, Appointment> appointmentMap = new HashMap<>();

    public void save(Appointment appointment) {
        /*
            This method saves an appointment object to the repository. 
        */
        appointmentMap.put(appointment.getId(), appointment);
    }

    public Appointment findById(String id) {
        /*
            This method retrieves an appointment by its unique ID. It returns the 
            appointment object if found, or null if no appointment with the given ID 
            exists in the repository.
        */
        return appointmentMap.get(id);
    }

    public List<Appointment> findAll() {
        /*
            This method returns a list of all appointments in the repository.
        */
        return new ArrayList<>(appointmentMap.values());
    }

    public void delete(String id) {
        /*
            This method removes an appointment from the repository based on its unique ID.
        */
        appointmentMap.remove(id);
    }

    public List<Appointment> findByPatientName(String patientName) {
        /*
            This method searches for appointments based on the patient's name. It iterates
            through all appointments in the repository and checks if the patient's name 
            contains the provided search term (case-insensitive). If a match is found, 
            the appointment is added to the result list, which is returned at the end.
        */
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getPatient().getName().toLowerCase().contains(patientName.toLowerCase())) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Appointment> findByDoctorName(String doctorName) {
        /*
            This method searches for appointments based on the doctor's name. It iterates
            through all appointments in the repository and checks if the doctor's name 
            contains the provided search term (case-insensitive). If a match is found, 
            the appointment is added to the result list, which is returned at the end.
        */
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getDoctor().getName().toLowerCase().contains(doctorName.toLowerCase())) {
                result.add(a);
            }
        }
        return result;
    }

    private void persist() {
        // Implement file or database persistence here
    }

    private Map<String, Doctor> load() {
        // Implement file or database loading here
        return new HashMap<>();
    }
}
