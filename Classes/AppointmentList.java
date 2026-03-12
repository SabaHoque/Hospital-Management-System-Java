package Classes;

import java.io.*;
import java.util.Scanner;

public class AppointmentList {
    public Appointment[] appointmentList = new Appointment[100];
    public static int appCount = 0;

    public AppointmentList() {
        loadAppointmentsFromFile();
    }

    // Extracted for SRP: File loading
    private void loadAppointmentsFromFile() {
        try {
            File file = new File("Files/AppointmentList.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String appID = sc.nextLine();
                String patientName = sc.nextLine();
                String diagnosis = sc.nextLine();
                String doctorName = sc.nextLine();
                String appDate = sc.nextLine();
                String appTime = sc.nextLine();
                if (sc.hasNextLine()) sc.nextLine(); // Skip empty line

                Appointment app = new Appointment(appID, patientName, diagnosis, doctorName, appDate, appTime);
                appointmentList[appCount] = app;
                appCount++;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void addAppointment(Appointment app) {
        if (appCount >= appointmentList.length) {
            throw new ArrayIndexOutOfBoundsException("Array full");
        }
        appointmentList[appCount] = app;
        appCount++;
        saveAppointmentToFile(app);
    }

    // Extracted for SRP: File saving
    private void saveAppointmentToFile(Appointment app) {
        String appointmentDetails = app.getAppId() + "\n" +
                                    app.getPatientName() + "\n" +
                                    app.getDiagnosis() + "\n" +
                                    app.getDoctorName() + "\n" +
                                    app.getAppDate() + "\n" +
                                    app.getAppTime() + "\n\n";
        try {
            FileWriter fw = new FileWriter("Files/AppointmentList.txt", true);
            fw.write(appointmentDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int searchAppointment(String appId) {
        int index = -1;
        for (int i = 0; i < appCount; i++) {
            if (appointmentList[i] != null && appointmentList[i].getAppId().equals(appId)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Appointment getAppointment(int index) {
        return appointmentList[index];
    }

    public void deleteAppointment(Appointment app) {
        for (int i = 0; i < appCount; i++) {
            if (appointmentList[i] == app) {
                for (int j = i; j < appCount - 1; j++) {
                    appointmentList[j] = appointmentList[j + 1];
                }
                appCount--;
                appointmentList[appCount] = null;
                break;
            }
        }
        removeAppointmentFromFile(app);
    }

    // Extracted for SRP: File deletion
    private void removeAppointmentFromFile(Appointment app) {
        String appointmentToDelete = app.getAppId() + "\n" +
                                     app.getPatientName() + "\n" +
                                     app.getDiagnosis() + "\n" +
                                     app.getDoctorName() + "\n" +
                                     app.getAppDate() + "\n" +
                                     app.getAppTime() + "\n\n";
        try {
            String filepath = "Files/AppointmentList.txt";
            File originalFile = new File(filepath);
            String newDetails = "";
            Scanner sc = new Scanner(originalFile);
            while (sc.hasNext()) {
                String readUser = readAppointmentBlock(sc);
                if (readUser.equals(appointmentToDelete)) {
                    continue;
                }
                newDetails += readUser;
            }
            sc.close();
            FileWriter fw = new FileWriter(filepath);
            fw.write(newDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Helper for reading block
    private String readAppointmentBlock(Scanner sc) {
        String readUser = sc.nextLine() + "\n";
        for (int i = 0; i < 5; i++) {
            if (sc.hasNextLine()) readUser += sc.nextLine() + "\n";
        }
        return readUser;
    }

    public void updateAppointment(String appointmentToUpdate, Appointment updatedAppointment) {
        updateAppointmentInFile(appointmentToUpdate, updatedAppointment);
    }

    // Extracted for SRP: File update
    private void updateAppointmentInFile(String appointmentToUpdate, Appointment updatedAppointment) {
        String updatedDetails = updatedAppointment.getAppId() + "\n" +
                                updatedAppointment.getPatientName() + "\n" +
                                updatedAppointment.getDiagnosis() + "\n" +
                                updatedAppointment.getDoctorName() + "\n" +
                                updatedAppointment.getAppDate() + "\n" +
                                updatedAppointment.getAppTime() + "\n\n";
        try {
            String filepath = "Files/AppointmentList.txt";
            File originalFile = new File(filepath);
            String newDetails = "";
            Scanner sc = new Scanner(originalFile);
            while (sc.hasNext()) {
                String readUser = readAppointmentBlock(sc);
                if (readUser.equals(appointmentToUpdate)) {
                    newDetails += updatedDetails;
                } else {
                    newDetails += readUser;
                }
            }
            sc.close();
            FileWriter fw = new FileWriter(filepath);
            fw.write(newDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}