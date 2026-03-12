package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentList {
    private static final String APPOINTMENT_FILE_PATH = "Files/AppointmentList.txt";
    private final List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentList() {
        loadAppointmentsFromFile();
    }

    private void loadAppointmentsFromFile() {
        File file = new File(APPOINTMENT_FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String appID = sc.nextLine();
                String patientName = sc.nextLine();
                String diagnosis = sc.nextLine();
                String doctorName = sc.nextLine();
                String appDate = sc.nextLine();
                String appTime = sc.nextLine();
                if (sc.hasNextLine()) sc.nextLine();

                Appointment app = new Appointment(appID, patientName, diagnosis, doctorName, appDate, appTime);
                appointmentList.add(app);
            }
        } catch (FileNotFoundException ex) {
            // Empty list
        } catch (IOException ex) {
            throw new RuntimeException("Error loading appointments", ex);
        }
    }

    public void addAppointment(Appointment app) {
        appointmentList.add(app);
        saveAppointmentToFile(app);
    }

    private void saveAppointmentToFile(Appointment app) {
        String appointmentDetails = app.getAppId() + "\n" +
                                    app.getPatientName() + "\n" +
                                    app.getDiagnosis() + "\n" +
                                    app.getDoctorName() + "\n" +
                                    app.getAppDate() + "\n" +
                                    app.getAppTime() + "\n\n";
        try (FileWriter fw = new FileWriter(APPOINTMENT_FILE_PATH, true)) {
            fw.write(appointmentDetails);
        } catch (IOException ex) {
            throw new RuntimeException("Error saving appointment", ex);
        }
    }

    public int searchAppointment(String appId) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getAppId().equals(appId)) {
                return i;
            }
        }
        return -1;
    }

    public Appointment getAppointment(int index) {
        if (index < 0 || index >= appointmentList.size()) {
            return null;
        }
        return appointmentList.get(index);
    }

    public void deleteAppointment(Appointment app) {
        appointmentList.remove(app);
        removeAppointmentFromFile(app);
    }

    private void removeAppointmentFromFile(Appointment app) {
        String appointmentToDelete = buildAppointmentFileString(app);
        try {
            List<String> lines = readAllLines(APPOINTMENT_FILE_PATH);
            StringBuilder newContent = new StringBuilder();
            StringBuilder currentBlock = new StringBuilder();
            for (String line : lines) {
                currentBlock.append(line).append("\n");
                if (currentBlock.toString().endsWith("\n\n")) {
                    if (!currentBlock.toString().equals(appointmentToDelete)) {
                        newContent.append(currentBlock);
                    }
                    currentBlock = new StringBuilder();
                }
            }
            writeAllText(APPOINTMENT_FILE_PATH, newContent.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Error deleting appointment from file", ex);
        }
    }

    private String buildAppointmentFileString(Appointment app) {
        return app.getAppId() + "\n" +
               app.getPatientName() + "\n" +
               app.getDiagnosis() + "\n" +
               app.getDoctorName() + "\n" +
               app.getAppDate() + "\n" +
               app.getAppTime() + "\n\n";
    }

    public void updateAppointment(String appointmentToUpdate, Appointment updatedAppointment) {
        updateAppointmentInFile(appointmentToUpdate, updatedAppointment);
    }

    private void updateAppointmentInFile(String appointmentToUpdate, Appointment updatedAppointment) {
        String updatedDetails = buildAppointmentFileString(updatedAppointment);
        try {
            List<String> lines = readAllLines(APPOINTMENT_FILE_PATH);
            StringBuilder newContent = new StringBuilder();
            StringBuilder currentBlock = new StringBuilder();
            for (String line : lines) {
                currentBlock.append(line).append("\n");
                if (currentBlock.toString().endsWith("\n\n")) {
                    if (currentBlock.toString().equals(appointmentToUpdate)) {
                        newContent.append(updatedDetails);
                    } else {
                        newContent.append(currentBlock);
                    }
                    currentBlock = new StringBuilder();
                }
            }
            writeAllText(APPOINTMENT_FILE_PATH, newContent.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Error updating appointment in file", ex);
        }
    }

    // Helpers
    private List<String> readAllLines(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void writeAllText(String path, String content) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(content);
        }
    }
}