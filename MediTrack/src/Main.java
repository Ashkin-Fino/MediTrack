import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.repository.*;
import com.airtribe.meditrack.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        BillRepository billRepository = new BillRepository();

        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);

        AppointmentService appointmentService =
                new AppointmentService(appointmentRepository, doctorService, patientService);

        BillingService billingService =
                new BillingService(billRepository, appointmentService);

        boolean running = true;

        while (running) {

            System.out.println("\n===== MediTrack Menu =====");
            System.out.println("1 Register Doctor");
            System.out.println("2 Register Patient");
            System.out.println("3 Search Doctor");
            System.out.println("4 Search Patient");
            System.out.println("5 Create Appointment");
            System.out.println("6 View Appointments");
            System.out.println("7 Cancel Appointment");
            System.out.println("8 Generate Bill");
            System.out.println("9 Make Payment");
            System.out.println("0 Exit");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> registerDoctor(doctorService);
                case 2 -> registerPatient(patientService);
                case 3 -> searchDoctor(doctorService);
                case 4 -> searchPatient(patientService);
                case 5 -> createAppointment(appointmentService);
                case 6 -> viewAppointments(appointmentRepository);
                case 7 -> cancelAppointment(appointmentService);
                case 8 -> generateBill(billingService);
                case 9 -> makePayment(billingService);
                case 0 -> running = false;

                default -> System.out.println("Invalid option");
            }
        }

        System.out.println("Application Closed");

    }

    private static void registerDoctor(DoctorService doctorService) {

        System.out.print("Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor(name, email, age, specialization);

        doctorService.registerDoctor(doctor);

        System.out.println("Doctor Registered: " + doctor.getId());
    }

    private static void registerPatient(PatientService patientService) {

        System.out.print("Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Patient patient = new Patient(name, email, age);

        patientService.registerPatient(patient);

        System.out.println("Patient Registered: " + patient.getId());
    }

    private static void searchDoctor(DoctorService doctorService) {

        System.out.print("Doctor Name: ");
        String name = scanner.nextLine();

        List<Doctor> doctors = doctorService.searchByName(name);

        doctors.forEach(d ->
                System.out.println(d.getId() + " | " + d.getName() + " | " + d.getSpecialization())
        );
    }

    private static void searchPatient(PatientService patientService) {

        System.out.print("Patient Name: ");
        String name = scanner.nextLine();

        List<Patient> patients = patientService.searchByName(name);

        patients.forEach(p ->
                System.out.println(p.getId() + " | " + p.getName())
        );
    }

    private static void createAppointment(AppointmentService appointmentService) {

        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();

        Appointment appointment =
                appointmentService.createAppointment(patientId, doctorId, LocalDate.now());

        if (appointment != null)
            System.out.println("Appointment Created: " + appointment.getId());
        else
            System.out.println("Invalid doctor or patient");
    }

    private static void viewAppointments(AppointmentRepository repository) {

        List<Appointment> appointments = repository.findAll();

        for (Appointment a : appointments) {

            System.out.println(
                    a.getId() + " | "
                            + a.getPatient().getName() + " | "
                            + a.getDoctor().getName() + " | "
                            + a.getStatus()
            );
        }
    }

    private static void cancelAppointment(AppointmentService service) {

        System.out.print("Appointment ID: ");
        String id = scanner.nextLine();

        Appointment appointment = service.cancelAppointment(id);

        if (appointment != null)
            System.out.println("Appointment Cancelled");
        else
            System.out.println("Appointment not found");
    }

    private static void generateBill(BillingService billingService) {

        System.out.print("Appointment ID: ");
        String id = scanner.nextLine();

        BillSummary summary = billingService.finalizeBill(id);

        if (summary != null) {
            System.out.println("Bill ID: " + summary.getBillSummaryId());
            System.out.println("Total: " + summary.getTotalAmount());
        } else {
            System.out.println("Appointment not found");
        }
    }

    private static void makePayment(BillingService billingService) {

        System.out.print("BillSummary ID: ");
        String id = scanner.nextLine();

        BillSummary summary = new BillSummary(id, 0, java.time.LocalDateTime.now());

        PaymentReceipt receipt = billingService.proceedToPayment(summary);

        System.out.println("Payment Status: " + receipt.getStatus());
    }
}