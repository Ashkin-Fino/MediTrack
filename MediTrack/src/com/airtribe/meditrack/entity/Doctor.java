package com.airtribe.meditrack.entity;

import java.util.UUID;

public class Doctor extends Person {

    private String specialization;

    public Doctor(String name, String email, int age, String specialization) {
        super(generateId(), name, email, age);
        this.specialization = specialization;
    }

    public static String generateId() {
        return "DOC-" + UUID.randomUUID();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
