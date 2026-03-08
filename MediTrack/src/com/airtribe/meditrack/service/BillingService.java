package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.repository.BillRepository;

public class BillingService {

    private final BillRepository repository;
    private final PaymentService paymentService;

    public BillingService(BillRepository repository, PaymentService paymentService) {
        /*
            Parameterized constructor to initialize the BillRepository and PaymentService 
            dependencies for the BillingService class.
        */
        this.repository = repository;
        this.paymentService = paymentService;
    }

    public Bill createBill(Appointment appointment) {
        /*
            Creates a new Bill and returns it.
        */
        Bill bill = new Bill(appointment);
        repository.save(bill);
        return bill;
    }

    public BillSummary finalizeBill(Appointment appointment) {
        /*
            Finalizes the bill for the given appointment by adding items, applying discounts,
            and generating a BillSummary. It also updates the appointment status to BILL_FINALIZED.
        */
        Bill bill = repository.findByAppointmentId(appointment.getId());
        bill.addItem("Service Fee", 80);

        System.out.println("Discount given? Enter percentage:");
        int discount = 10; // Simulating user input for discount
        bill.setDiscount(discount);

        BillSummary summary = bill.generateBillSummary();
        appointment.setStatus(AppointmentStatus.BILL_FINALIZED);
        return summary;
    }

    public PaymentReceipt proceedToPayment(String billId, String paymentMethod) {
        /*
            Proceeds to payment for the given bill ID and returns a PaymentReceipt.
        */
        Bill bill = repository.findById(billId);
        if (bill == null) {
            System.out.println("Bill not found for ID: " + billId);
            return null;
        }
        BillSummary billSummary = bill.generateBillSummary();
        return paymentService.createPaymentReceipt(billSummary, paymentMethod);
    }
}