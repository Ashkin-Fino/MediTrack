package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.observer.AppointmentObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Appointment {

    private final String id;
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDate date;
    private AppointmentStatus status;
    private final List<AppointmentObserver> observers;

    public Appointment(Patient patient, Doctor doctor) {
        this.id = generateId();
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDate.now();
        this.status = AppointmentStatus.APPOINTMENT_SCHEDULED;
        this.observers = new ArrayList<>();
    }

    public static String generateId() {
        return "APT-" + UUID.randomUUID();
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
        notifyObservers();
    }

    public void addObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AppointmentObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (AppointmentObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public List<AppointmentObserver> getObservers() {
        return Collections.unmodifiableList(observers);
    }


}
