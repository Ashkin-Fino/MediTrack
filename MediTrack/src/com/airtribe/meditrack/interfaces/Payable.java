package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.PaymentReceipt;

public interface Payable {

    PaymentReceipt pay(BillSummary billSummary);

}