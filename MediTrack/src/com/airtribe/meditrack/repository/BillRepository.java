package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;

import java.util.*;

public class BillRepository {

    private final Map<String, Bill> billMap = new HashMap<>();

    public void save(Bill bill) {
        /*
            This method saves a Bill object to the billMap using the bill's ID as the key.
        */
        billMap.put(bill.getId(), bill);
    }

    public Bill findById(String id) {
        /*
            This method retrieves a Bill object from the billMap using the provided ID. 
            If the ID does not exist in the map, it returns null.
        */
        return billMap.get(id);
    }

    public List<Bill> findAll() {
        /*
            This method returns a list of all Bill objects stored in the billMap. 
        */
        return new ArrayList<>(billMap.values());
    }

    public void delete(String id) {
        /*
            This method removes a Bill object from the billMap using the provided ID.
        */
        billMap.remove(id);
    }

    public Bill findByAppointmentId(String appointmentId) {
        /*
            This method iterates through the billMap values to find a Bill object that 
            has an associated appointment with the given appointmentId. If such a Bill 
            is found, it is returned; otherwise, null is returned.
        */
        for (Bill bill : billMap.values()) {
            if (bill.getAppointment().getId().equals(appointmentId)) {
                return bill;
            }
        }
        return null;
    }

    public void persist() {
        // Implement file or database persistence here
    }

    public void load() {
        // Implement file or database loading here
    }
}
