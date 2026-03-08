package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.repository.BillRepository;

public class BillingService {

    private final BillRepository repository;
    private final AppointmentService appointmentService;

    public BillingService(BillRepository repository, AppointmentService appointmentService) {
        /*
            Parameterized constructor to initialize the BillRepository and 
            AppointmentService dependencies for the BillingService class.
        */
        this.repository = repository;
        this.appointmentService = appointmentService;
    }

    public BillSummary finalizeBill(String appointmentId) {

        Appointment appointment = appointmentService.searchById(appointmentId);

        if (appointment == null) {
            return null;
        }

        Bill bill = new Bill(appointment);

        bill.addItem("Consultation Fee", 500);
        bill.addItem("Medical Service", 300);

        BillSummary summary = bill.generateBillSummary();

        repository.save(bill);

        appointment.setStatus(AppointmentStatus.BILL_FINALIZED);

        return summary;
    }

    public PaymentReceipt proceedToPayment(BillSummary billSummary) {

        PaymentReceipt receipt = new PaymentReceipt(
                billSummary.getBillSummaryId(),
                billSummary.getTotalAmount(),
                "UPI",
                com.airtribe.meditrack.enums.PaymentStatus.SUCCESS
        );

        return receipt;
    }
}