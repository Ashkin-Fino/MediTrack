package com.airtribe.meditrack.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Patient extends Person {

    private final List<String> medicalHistory;

    public Patient(String name, String email, int age) {
        super(generateId(), name, email, age);
        this.medicalHistory = new ArrayList<>();
    }

    public static String generateId() {
        return "PAT-" + UUID.randomUUID();
    }

    public void addMedicalRecord(String record) {
        medicalHistory.add(record);
    }

    public List<String> getMedicalHistory() {
        return Collections.unmodifiableList(medicalHistory);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", age=" + getAge() +
                ", medicalHistory=" + medicalHistory +
                '}';
    }
}
