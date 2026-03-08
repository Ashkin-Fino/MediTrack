package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.PaymentReceipt;
import com.airtribe.meditrack.enums.PaymentStatus;
import com.airtribe.meditrack.repository.PaymentRepository;

public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        /*
            Parameterized constructor to initialize the BillRepository dependency
            for the BillingService class.
        */
        this.paymentRepository = paymentRepository;
    }

    public PaymentReceipt createPaymentReceipt(BillSummary billSummary, String paymentMethod) {
        /*
            Creates a new PaymentReceipt and returns it.
        */
        PaymentReceipt receipt = new PaymentReceipt(billSummary.getBillSummaryId(), 
                billSummary.getTotalAmount(), paymentMethod,
                PaymentStatus.SUCCESS
        );
        paymentRepository.save(receipt);
        return receipt;
    }
    
}
