package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.PaymentReceipt;
import java.util.*;

public class PaymentRepository {

    private final Map<String, PaymentReceipt> receiptMap = new HashMap<>();

    public void save(PaymentReceipt receipt) {
        receiptMap.put(receipt.getReceiptId(), receipt);
    }

    public PaymentReceipt findById(String id) {
        /*
            This method retrieves a payment receipt by its unique ID. It returns the 
            payment receipt object if found, or null if no receipt with the given ID exists in the repository.
        */
        return receiptMap.get(id);
    }

    public List<PaymentReceipt> findAll() {
        /*
            This method returns a List of all payment receipts currently stored in the repository.
        */
        return new ArrayList<>(receiptMap.values());
    }

    public List<PaymentReceipt> findByBillSummaryId(String billSummaryId) {
        /*
            This method searches for payment receipts associated with a specific bill summary ID 
            and returns a list of matching payment receipts.
        */
        List<PaymentReceipt> result = new ArrayList<>();

        for (PaymentReceipt r : receiptMap.values()) {
            if (r.getBillSummaryId().equals(billSummaryId)) {
                result.add(r);
            }
        }

        return result;
    }

    public void persist() {
        // Implement file or database persistence here
    }

    public void load() {
        // Implement file or database loading here
    }
}
