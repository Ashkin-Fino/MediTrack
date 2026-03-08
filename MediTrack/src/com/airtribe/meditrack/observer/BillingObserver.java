package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;

public class BillingObserver implements AppointmentObserver {

    private final Bill bill;

    public BillingObserver(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("Bill cannot be null");
        }
        this.bill = bill;
    }

    @Override
    public void update(Appointment appointment) {
        /*
            This method is called when the appointment status changes. It checks if the
            appointment is null or if the appointment's bill does not match the
            observer's bill. If either condition is true, it returns without doing
            anything. Otherwise, it adds the consultation fee to the Bill items.
        */
        if (appointment == null) {
            return;
        }
        if (!bill.getAppointment().getId().equals(appointment.getId())) {
            return;
        }
        int fee = 250; //simulating user input
        bill.addItem("Consultation Fee", fee);
    }

    public Bill getBill() {
        return bill;
    }
}
