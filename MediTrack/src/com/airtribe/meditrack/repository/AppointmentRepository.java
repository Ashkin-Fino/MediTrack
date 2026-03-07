package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Appointment;
import java.util.*;

public class AppointmentRepository {

    private final Map<String, Appointment> appointmentMap = new HashMap<>();

    public void save(Appointment appointment) {
        appointmentMap.put(appointment.getId(), appointment);
    }

    public Appointment findById(String id) {
        return appointmentMap.get(id);
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointmentMap.values());
    }

    public void delete(String id) {
        appointmentMap.remove(id);
    }

    public List<Appointment> findByPatientId(String patientId) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointmentMap.values()) {
            if (a.getPatient().getId().equals(patientId)) {
                result.add(a);
            }
        }

        return result;
    }

    public List<Appointment> findByDoctorId(String doctorId) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointmentMap.values()) {
            if (a.getDoctor().getId().equals(doctorId)) {
                result.add(a);
            }
        }

        return result;
    }
}
