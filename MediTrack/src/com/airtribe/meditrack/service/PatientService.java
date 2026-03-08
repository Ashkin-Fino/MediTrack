package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.repository.PatientRepository;

import java.util.List;
import java.util.Scanner;

public class PatientService implements Searchable<Patient> {

    private static final Scanner scanner = new Scanner(System.in);
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void registerPatient(Patient patient) {
        repository.save(patient);
    }

    @Override
    public Patient searchById(String id) {
        Patient patient = repository.findById(id);
        if (patient == null) {
            System.out.println("No patient found with ID: " + id);
        }
        return patient;
    }

    @Override
    public List<Patient> searchByName(String name) {
        List<Patient> patients = repository.findByName(name);
        if (patients.isEmpty()) {
            System.out.println("No patients found with the name: " + name);
        }
        return patients;
    }

    public Patient choosePatient(List<Patient> patients) {
/*
            This method displays the patients with their names and age, prompts 
            the user to choose a patient by entering the corresponding number, and 
            returns the selected Patient.
        */

            if (patients == null || patients.isEmpty()) {
                return null;
            }
    
            System.out.println("Select a patient:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". Name:" + patients.get(i).getName() + 
                        " |Age: " + patients.get(i).getAge() +
                        " |Email: " + patients.get(i).getEmail());
            }
    
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            if (choice < 1 || choice > patients.size()) {
                System.out.println("Invalid choice. Please try again.");
                return choosePatient(patients);
            } else {
                return patients.get(choice - 1);
            }
    }
}