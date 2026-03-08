# Project Structure and LLD

## Patient and Doctor Entities

### Person (Abstract Class)

| Field | Type |
|------|------|
| id | String |
| name | String |
| email | String |
| age | int |

**Methods**

- `getId(): String`
- `getName(): String`
- `getEmail(): String`
- `getAge(): int`

---

### Patient (extends Person)

| Field | Type |
|------|------|
| medicalHistory | List<String> |

**Methods**

- `generateId(): String`

---

### Doctor (extends Person)

| Field | Type |
|------|------|
| specialization | String |

**Methods**

- `generateId(): String`
- `getSpecialization(): String`

---

# Appointment Entity and Observers

## AppointmentStatus (Enum)

```
APPOINTMENT_SCHEDULED
CONSULTED_DOCTOR
APPOINTMENT_CANCELLED
BILL_FINALIZED
PAYMENT_COMPLETED
```

---

## Appointment

| Field | Type |
|------|------|
| id | String |
| patient | Patient |
| doctor | Doctor |
| date | LocalDate |
| status | AppointmentStatus |
| observers | List<AppointmentObserver> |

**Methods**

- `generateId(): String`
- `setStatus(status: AppointmentStatus): void`
- `addObserver(o: AppointmentObserver): void`
- `removeObserver(o: AppointmentObserver): void`
- `notifyObservers(): void`

---

## AppointmentObserver (Interface)

```
update(appointment: Appointment): void
```

---

## PatientListener (Implements AppointmentObserver)

| Field | Type |
|------|------|
| patient | Patient |

**Methods**

```
update(appointment: Appointment): void
```

This follows the **Observer Design Pattern**, where the patient is notified whenever the appointment status changes.

---

# Bill and BillSummary Entities

## Bill

| Field | Type |
|------|------|
| id | String |
| appointment | Appointment |
| amount | double |
| items | HashMap<String, Integer> |
| discount | int |
| billSummary | BillSummary |

**Methods**

```
generateId(): String
addItem(item: BillItem): void
calculateTotal(): double
generateBillSummary(): BillSummary
```

The **Bill** class represents the billing details for an appointment.  
Items such as consultation fees, medicines, or tests can be added, and the total bill amount is calculated.

---

## BillSummary (Immutable)

| Field | Type |
|------|------|
| billSummaryId | String |
| billId | String |
| totalAmount | double |
| generatedAt | LocalDateTime |

**Methods**

```
generateBillSummaryId(billId: String): String
getTotalAmount(): double
```

`BillSummary` is designed as an **immutable class** so once the bill is finalized it cannot be modified.

---

# Payment Entities

## PaymentStatus (Enum)

```
SUCCESS
FAILED
PENDING
```

---

## PaymentReceipt (Immutable)

| Field | Type |
|------|------|
| receiptId | String |
| billSummaryId | String |
| paidAmount | double |
| paymentDate | LocalDateTime |
| paymentMethod | String |
| status | PaymentStatus |

**Methods**

```
getReceiptId(): String
getPaidAmount(): double
getStatus(): PaymentStatus
```

`PaymentReceipt` is immutable to preserve the integrity of payment records.

---

# Payment Service Classes

## Payable (Interface)

```
pay(billSummary: BillSummary): PaymentReceipt
```

---

## PaymentService (Implements Payable)

```
pay(billSummary: BillSummary): PaymentReceipt
```

The **Payable interface** defines the contract for handling payments.

`PaymentService` processes the payment and generates a `PaymentReceipt`.

---

# Service Classes and Searchable Service Classes

## Searchable<T> (Generic Interface)

```
searchById(id: String): T
searchByName(name: String): List<T>
```

This generic interface provides reusable search functionality for different entities.

---

## AppointmentService (Implements Searchable<Appointment>)

| Field | Type |
|------|------|
| repository | AppointmentRepository |
| doctorService | DoctorService |
| patientService | PatientService |

**Methods**

```
createAppointment(...): Appointment
updateStatus(status: String): Appointment
cancelAppointment(): Appointment
searchById(id: String): Appointment
searchByName(name: String): List<Appointment>
chooseAppointment(a: List<Appointment>): Appointment
```

The **AppointmentService** manages appointment creation, cancellation, and status updates.

---

## DoctorService (Implements Searchable<Doctor>)

| Field | Type |
|------|------|
| repository | DoctorRepository |

**Methods**

```
registerDoctor(doctor: Doctor): void
searchById(id: String): Doctor
searchByName(name: String): List<Doctor>
chooseDoctor(d: List<Doctor>): Doctor
```

Handles doctor registration and searching functionality.

---

## PatientService (Implements Searchable<Patient>)

| Field | Type |
|------|------|
| repository | PatientRepository |

**Methods**

```
registerPatient(patient: Patient): void
searchById(id: String): Patient
searchByName(name: String): List<Patient>
choosePatient(d: List<Patient>): Patient
```

Handles patient registration and searching functionality.

---

## BillingService

| Field | Type |
|------|------|
| repository | BillingRepository |
| appointmentService | AppointmentService |

**Methods**

```
finalizeBill(): BillSummary
proceedToPayment(billSummary: BillSummary): PaymentReceipt
```

`BillingService` is responsible for:

- Finalizing bills after consultation
- Generating a `BillSummary`
- Initiating payment through `PaymentService`

---

# Key Concepts Used

### Object-Oriented Programming

- Inheritance (`Person → Patient / Doctor`)
- Encapsulation
- Abstraction

### Design Patterns

- **Observer Pattern** → Appointment notifications
- **Immutable Objects** → BillSummary, PaymentReceipt

### Java Features

- Enums
- Generics
- Collections (`List`, `HashMap`)
- `LocalDate`
- `LocalDateTime`