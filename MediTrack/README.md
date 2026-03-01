## Project Structure and LLD

## Patient and Doctor Entities
---------------------------------
<<abstract>> Person
---------------------------------
- id: String
- name: String
- email: String
- int: age
---------------------------------
+ getId(): String
+ getName(): String
+ getEmail(): String
+ getAge(): String
---------------------------------

---------------------------------
Patient extends Person
---------------------------------
- medicalHistory: List<String>
---------------------------------
+ generateId(): String
---------------------------------

---------------------------------
Doctor extends Person
---------------------------------
- specialization: String
---------------------------------
+ generateId(): String
+ getSpeacialization(): String
---------------------------------

## Appointment Entity and Observors

-----------------------------
<<enum>> AppointmentStatus
-----------------------------
APPOINTMENT SCHEDULED
CONSULTED DOCTOR
APPOINTMENT CANCELLED
BILL FINALIZED
PAYMENT COMPLETED
-----------------------------

---------------------------------
Appointment
---------------------------------
- id: String
- patient: Patient
- doctor: Doctor
- date: LocalDate
- status: AppointmentStatus
- observers: List<AppointmentObserver>
---------------------------------
+ generateId(): String
+ setStatus(status: AppointmentStatus): void
+ addObserver(o: AppointmentObserver): void
+ removeObserver(o: AppointmentObserver): void
+ notifyObservers(): void
---------------------------------

---------------------------------
<<interface>> AppointmentObserver
---------------------------------
+ update(appointment: Appointment): void
---------------------------------

---------------------------------
PatientListener implements AppointmentObserver
---------------------------------
- patient: Patient
---------------------------------
+ update(appointment: Appointment): void
---------------------------------

## Bill and BillSummary Entities

---------------------------------
Bill
---------------------------------
- id: String
- appointment: Appointment
- amount: double
- items: HashMap<String, Integer>
- discount: int
- billSummary: BillSummary
---------------------------------
+ generateId(): String
+ addItem(item: BillItem): void
+ calculateTotal(): double
+ generateBillSummary(): BillSummary
---------------------------------

---------------------------------
<<immutable>> BillSummary
---------------------------------
- billSummaryId: String
- billId: String
- totalAmount: double
- generatedAt: LocalDateTime
---------------------------------
+ generateBillSummaryId(billId: String): String
+ getTotalAmount(): double
---------------------------------

## Payment Entities

-----------------------------
<<enum>> PaymentStatus
-----------------------------
SUCCESS
FAILED
PENDING
-----------------------------

---------------------------------
<<immutable>> PaymentReceipt
---------------------------------
- receiptId: String
- billSummaryId: String
- paidAmount: double
- paymentDate: LocalDateTime
- paymentMethod: String
- status: PaymentStatus
---------------------------------
+ getReceiptId(): String
+ getPaidAmount(): double
+ getStatus(): PaymentStatus
---------------------------------

## Payment Service Classes

---------------------------------
<<interface>> Payable
---------------------------------
+ pay(billSummary: BillSummary): PaymentReceipt
---------------------------------

---------------------------------
PaymentService implements Payable
---------------------------------
+ pay(billSummary: BillSummary): PaymentReceipt
---------------------------------

## Service Classes and Searchable Service Classes

---------------------------------
<<interface>> Searchable<T>
---------------------------------
+ searchById(id: String): T
+ searchByName(name: String): List<T>
---------------------------------

---------------------------------
AppointmentService implements Searchable<Appointment>
---------------------------------
- repository: AppointmentRepository
- doctorService: DoctorService
- patientService: PatientService
---------------------------------
+ createAppointment(...): Appointment
+ updateStatus(status: String): Appointment
+ cancelAppointment(): Appointment
+ searchById(id: String): Appointment
+ searchByName(name: String): List<Appointment>
+ chooseAppointment(a: List<Appointment>): Appointment
---------------------------------

---------------------------------
DoctorService implements Searchable<Doctor>
---------------------------------
- repository: DoctorRepository
---------------------------------
+ registerDoctor(doctor: Doctor): void
+ searchById(id: String): Doctor
+ searchByName(name: String): List<Doctor>
+ chooseDoctor(d: List<Doctor>): Doctor
---------------------------------

---------------------------------
PatientService implements Searchable<Patient>
---------------------------------
- repository: PatientRepository
---------------------------------
+ registerPatient(patient: Patient): void
+ searchById(id: String): Patient
+ searchByName(name: String): List<Patient>
+ choosePatient(d: List<Patient>): Patient
---------------------------------

---------------------------------
BillingService
---------------------------------
- repository: BillingRepository
- appointmentService: AppointmentService
---------------------------------
+ finalizeBill(): BillSummary
+ proceedToPayment(billSummary: BillSummary): PaymentReceipt
---------------------------------