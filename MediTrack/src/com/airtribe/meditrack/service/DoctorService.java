package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.repository.DoctorRepository;

import java.util.List;
import java.util.Scanner;

public class DoctorService implements Searchable<Doctor> {

    private static final Scanner scanner = new Scanner(System.in);
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public void registerDoctor(Doctor doctor) {
        repository.save(doctor);
    }

    @Override
    public Doctor searchById(String id) {
        Doctor doctor = repository.findById(id);
        if (doctor == null) {
            System.out.println("No doctor found with ID: " + id);
        }
        return doctor;
    }

    @Override
    public List<Doctor> searchByName(String name) {
        List<Doctor> doctors = repository.findByName(name);
        if (doctors.isEmpty()) {
            System.out.println("No doctors found with the name: " + name);
        }
        return doctors;
    }

    public Doctor chooseDoctor(List<Doctor> doctors) {
        /*
            This method displays the doctors with their names and specializations, prompts 
            the user to choose a doctor by entering the corresponding number, and 
            returns the selected Doctor.
        */

        if (doctors == null || doctors.isEmpty()) {
            return null;
        }

        System.out.println("Select a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". Name:" + doctors.get(i).getName() + 
                    " |Speacialization: " + doctors.get(i).getSpecialization());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > doctors.size()) {
            System.out.println("Invalid choice. Please try again.");
            return chooseDoctor(doctors);
        } else {
            return doctors.get(choice - 1);
        }
    }
}