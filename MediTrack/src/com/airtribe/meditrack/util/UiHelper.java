package com.airtribe.meditrack.util;

public class UiHelper {
    
    public static void mainMenu() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║     MEDITRACK MAIN MENU     ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║1. Register Doctor           ║");
        System.out.println("║2. Register Patient          ║");
        System.out.println("║3. Search Doctor             ║");
        System.out.println("║4. Search Patient            ║");
        System.out.println("║5. Create Appointment        ║");
        System.out.println("║6. View Appointments         ║");
        System.out.println("║7. Cancel Appointment        ║");
        System.out.println("║8. Generate Bill             ║");
        System.out.println("║9. Make Payment              ║");
        System.out.println("║0. Exit                      ║");
        System.out.println("╚═════════════════════════════╝");

        
        System.out.print("Choose option: ");
    }

    public static void exitView() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║THANK YOU FOR USING MEDITRACK║");
        System.out.println("╚═════════════════════════════╝");
    }
}
