package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bill {

    private final String id;
    private final Appointment appointment;
    private final Map<String, Integer> items;
    private double amount;
    private int discount;
    private BillSummary billSummary;

    public Bill(Appointment appointment) {
        this.id = generateId();
        this.appointment = appointment;
        this.items = new HashMap<>();
        this.discount = 0;
    }

    public static String generateId() {
        return "BILL-" + UUID.randomUUID();
    }

    public void addItem(String item, int price) {
        items.put(item, price);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double calculateTotal() {
        double total = items.values().stream().mapToDouble(Integer::doubleValue).sum();
        total -= (total * discount / 100.0);
        this.amount = total;
        return total;
    }

    public BillSummary generateBillSummary() {
        calculateTotal();
        this.billSummary = new BillSummary(id, amount, LocalDateTime.now());
        return billSummary;
    }

    public String getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getAmount() {
        return amount;
    }

    public int getDiscount() {
        return discount;
    }

    public BillSummary getBillSummary() {
        return billSummary;
    }
}
