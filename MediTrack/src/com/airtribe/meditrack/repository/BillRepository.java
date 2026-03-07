package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Bill;
import java.util.*;

public class BillRepository {

    private final Map<String, Bill> billMap = new HashMap<>();

    public void save(Bill bill) {
        billMap.put(bill.getId(), bill);
    }

    public Bill findById(String id) {
        return billMap.get(id);
    }

    public List<Bill> findAll() {
        return new ArrayList<>(billMap.values());
    }

    public void delete(String id) {
        billMap.remove(id);
    }

    public Bill findByAppointmentId(String appointmentId) {

        for (Bill bill : billMap.values()) {
            if (bill.getAppointment().getId().equals(appointmentId)) {
                return bill;
            }
        }

        return null;
    }
}
