import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.repository.*;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.UiHelper;

import java.time.LocalDate;
import java.util.InputMismatchException;
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

        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        BillingService billingService = new BillingService(billRepository, appointmentService);

        boolean running = true;

        while (running) {
            UiHelper.mainMenu();
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 10;
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerDoctor(doctorService);
                case 2 -> registerPatient(patientService);
                case 3 -> searchDoctor(doctorService);
                case 4 -> searchPatient(patientService);
                case 5 -> createAppointment(appointmentService, patientService, doctorService);
                case 6 -> viewAppointments(appointmentService);
                case 7 -> cancelAppointment(appointmentService);
                case 8 -> generateBill(appointmentService, billingService);
                case 9 -> makePayment(billingService);
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        UiHelper.exitView();
    }

    private static void registerDoctor(DoctorService doctorService) {
        /*
            This method get the doctor details from user and creates a new doctor in
            the system. Prints the created doctor.
        */

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

        System.out.println("Doctor Registered: " + doctor.toString());
    }

    private static void registerPatient(PatientService patientService) {
        /*
            This method get the patient details from user and creates a new patient in
            the system. Prints the created patient.
        */
        System.out.print("Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Patient patient = new Patient(name, email, age);

        patientService.registerPatient(patient);

        System.out.println("Patient Registered: " + patient.toString());
    }

    private static void searchDoctor(DoctorService doctorService) {
        /*
            This method prompts the user to enter a doctor's name, searches for doctors 
            matching that name using the DoctorService, and prints the names and 
            specializations of the matching doctors.
        */
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();

        List<Doctor> doctors = doctorService.searchByName(name);

        doctors.forEach(d -> System.out.println(d.getName() + " | " + d.getSpecialization()));
    }

    private static void searchPatient(PatientService patientService) {
        /*
            This method prompts the user to enter a patient's name, searches for patients 
            matching that name using the PatientService, and prints the names and ages 
            of the matching patients.
        */
        System.out.print("Patient Name: ");
        String name = scanner.nextLine();

        List<Patient> patients = patientService.searchByName(name);

        patients.forEach(p -> System.out.println(p.getId() + " | " + p.getName()));
    }

    private static void createAppointment(AppointmentService appointmentService,
                        PatientService patientService, DoctorService doctorService) {
        /*
            This method prompts the user to enter a patient's name and a doctor's name, 
            searches for matching patients and doctors using the PatientService and
            DoctorService, allows the user to choose a patient and a doctor from the returned list.
            Then, it calls the createAppointment method to create the appointment.
        */
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        List<Patient> patients = patientService.searchByName(patientName);
        if (patients.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }
        Patient patient = patientService.choosePatient(patients);

        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();
        List<Doctor> doctors = doctorService.searchByName(doctorName);
        if (doctors.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }
        Doctor doctor = doctorService.chooseDoctor(doctors);

        Appointment appointment = appointmentService.createAppointment(patient, doctor);

        if (appointment != null)
            System.out.println("Appointment Created: " + appointment.getId());
        else
            System.out.println("Invalid doctor or patient");
    }

    private static void viewAppointments(AppointmentService appointmentService) {
        /*
            This method allows the user to view appointments based on different filters. 
            The user can choose to filter appointments by patient/doctor name or by date. 
            Depending on the user's choice, the method prompts for the necessary input and 
            retrieves and displays the matching appointments.
        */
        System.out.println("Choose filter: \n1. Patient/Doctor Name \n2. Date");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();
        List<Appointment> appointments;

        switch (filterChoice) {
            case 1:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                appointments = appointmentService.searchByName(name);
                appointments.forEach(a -> System.out.println("Appointment: Patient -" + a.getPatient().getName() + 
                        " |Doctor - " + a.getDoctor().getName() + " |Status - " + a.getStatus()));
                break;
            case 2:
                System.out.print("Enter Date: (YYYY-MM-DD)");
                String date = scanner.nextLine();
                try {
                    LocalDate.parse(date);
                } catch (Exception e) {
                    System.out.println("Invalid date format, showing all appointments:");
                    appointments = appointmentService.searchByName("");
                    appointments.forEach(a -> System.out.println("Appointment: Patient -" + a.getPatient().getName() + 
                            " |Doctor - " + a.getDoctor().getName() + " |Status - " + a.getStatus() + 
                            " |Date - " + a.getDate()));
                    return;
                }
                appointments = appointmentService.searchByDate(LocalDate.parse(date));
                appointments.forEach(a -> System.out.println("Appointment: Patient -" + a.getPatient().getName() + 
                        " |Doctor - " + a.getDoctor().getName() + " |Status - " + a.getStatus() +
                        " |Date - " + a.getDate()));
                break;
            default:
                System.out.println("Invalid option, showing all appointments:");
                appointments = appointmentService.searchByName("");
                appointments.forEach(a -> System.out.println("Appointment: Patient -" + a.getPatient().getName() + 
                        " |Doctor - " + a.getDoctor().getName() + " |Status - " + a.getStatus()));
                break;
        }
    }

    private static void cancelAppointment(AppointmentService service) {
        /*
            This method prompts the user to enter a doctor's or patient's name, 
            searches for appointments matching that name using the AppointmentService,
            allows the user to choose an appointment from the returned list, and then
            cancels the selected appointment.
        */
        System.out.print("Enter Doctor/Patient name: ");
        String name = scanner.nextLine();
        List<Appointment> appointments = service.searchByName(name);
        if (appointments.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }
        Appointment appointment = service.chooseAppointment(appointments);
        service.cancelAppointment(appointment);
    }

    private static void generateBill(AppointmentService appointmentService, BillingService billingService) {

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