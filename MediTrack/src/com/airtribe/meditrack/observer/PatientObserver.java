package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Patient;

public class PatientObserver implements AppointmentObserver {

    private final Patient patient;

    public PatientObserver(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        this.patient = patient;
    }

    @Override
    public void update(Appointment appointment) {
        if (appointment == null) {
            return;
        }

        if (!appointment.getPatient().getId().equals(patient.getId())) {
            return;
        }

        System.out.println(
                "Notification for Patient: " + patient.getName()
                        + " | Appointment ID: " + appointment.getId()
                        + " | Doctor: " + appointment.getDoctor().getName()
                        + " | New Status: " + appointment.getStatus()
        );
    }

    public Patient getPatient() {
        return patient;
    }
}
