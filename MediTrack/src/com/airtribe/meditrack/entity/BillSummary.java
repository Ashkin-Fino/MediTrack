package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public final class BillSummary {

    private final String billSummaryId;
    private final String billId;
    private final double totalAmount;
    private final LocalDateTime generatedAt;

    public BillSummary(String billId, double totalAmount, LocalDateTime generatedAt) {
        this.billSummaryId = generateBillSummaryId(billId);
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.generatedAt = generatedAt;
    }

    private String generateBillSummaryId(String billId) {
        return "BS-" + billId + "-" + UUID.randomUUID();
    }

    public String getBillSummaryId() {
        return billSummaryId;
    }

    public String getBillId() {
        return billId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}