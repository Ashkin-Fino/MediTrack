package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.observer.PatientObserver;
import com.airtribe.meditrack.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AppointmentService implements Searchable<Appointment> {

    private static final Scanner scanner = new Scanner(System.in);
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        /*
            Parameterized constructor for AppointmentService with dependency injection of
            AppointmentRepository.
        */
        this.repository = repository;
    }

    public Appointment createAppointment(Patient patient, Doctor doctor) {
        /*
            This method creates a new appointment for the given patient and doctor. It 
            also adds a PatientObserver to the appointment to notify the patient of any
            changes to the appointment status. Finally, it saves the appointment to the
            repository and returns the created appointment.
        */
        Appointment appointment = new Appointment(patient, doctor);
        appointment.addObserver(new PatientObserver(patient));
        repository.save(appointment);

        return appointment;
    }

    public Appointment updateStatus(String appointmentId, AppointmentStatus status) {

        Appointment appointment = repository.findById(appointmentId);

        if (appointment == null) {
            return null;
        }

        appointment.setStatus(status);

        return appointment;
    }

    public void cancelAppointment(Appointment appointment) {
        /*
            This method cancels the given appointment by setting its status to 
            APPOINTMENT_CANCELLED. If the appointment is null, it returns null.
        */
        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.APPOINTMENT_CANCELLED);
            System.out.println("Appointment cancelled successfully");
        }
    }

    @Override
    public Appointment searchById(String id) {
        Appointment appointment = repository.findById(id);
        if (appointment == null) {
            System.out.println("No appointment found with ID: " + id);
        }
        return appointment;
    }

    @Override
    public List<Appointment> searchByName(String name) {
        Set<Appointment> appointments = new HashSet<>();
        appointments.addAll(repository.findByDoctorName(name));
        appointments.addAll(repository.findByPatientName(name));
        if (appointments.isEmpty()) {
            System.out.println("No appointments found with the name: " + name);            
        }
        return new ArrayList<>(appointments);
    }

    public List<Appointment> searchByDate(LocalDate date) {
        List<Appointment> appointments = repository.findAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                result.add(appointment);
            }
        }
        if (appointments.isEmpty()) {
            System.out.println("No appointments found with on the given date: " + date);            
        }
        return result;
    }

    public Appointment chooseAppointment(List<Appointment> appointments) {
        /*
            This method displays the appointments with their doctor names, patient names, and dates, 
            prompts the user to choose an appointment by entering the corresponding number, and 
            returns the selected Appointment.
        */
        if (appointments == null || appointments.isEmpty()) {
            return null;
        }
        System.out.println("Select a appointment:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println((i + 1) + ". Doctor Name:" + appointments.get(i).getDoctor().getName() + 
                    " |Patient Name: " + appointments.get(i).getPatient().getName() + 
                    " |Date: " + appointments.get(i).getDate());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice < 1 || choice > appointments.size()) {
            System.out.println("Invalid choice. Please try again.");
            return chooseAppointment(appointments);
        } else {
            return appointments.get(choice - 1);
        }
    }
}