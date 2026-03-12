package Classes;
import Interfaces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PList implements IPList {
    private static final String PATIENT_FILE_PATH = "Files/PatientList.txt";
    private final List<Patient> patientList = new ArrayList<>();

    public PList() {
        loadPatientsFromFile();
    }

    private void loadPatientsFromFile() {
        File file = new File(PATIENT_FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String id = sc.nextLine();
                String name = sc.nextLine();
                String mobileNo = sc.nextLine();
                String gender = sc.nextLine();
                String age = sc.nextLine();
                String address = sc.nextLine();
                String appointedDoctor = sc.nextLine();
                String room = sc.nextLine();
                String diagnosis = sc.nextLine();
                String deposit = sc.nextLine();
                if (sc.hasNextLine()) sc.nextLine();

                Patient p = new Patient(id, name, mobileNo, gender, age, address, appointedDoctor, room, diagnosis, deposit);
                patientList.add(p);
            }
        } catch (FileNotFoundException ex) {
            // Empty list
        } catch (IOException ex) {
            throw new RuntimeException("Error loading patients", ex);
        }
    }

    public void addPatient(Patient p) {
        patientList.add(p);
        savePatientToFile(p);
    }

    private void savePatientToFile(Patient p) {
        String patientDetails = p.getId() + "\n" + p.getName() + "\n" +
                                p.getMobileNo() + "\n" + p.getGender() + "\n" +
                                p.getAge() + "\n" + p.getAddress() + "\n" +
                                p.getAppointedDoctor() + "\n" + p.getRoom() + "\n" +
                                p.getDiagnosis() + "\n" + p.getDeposit() + "\n\n";
        try (FileWriter fw = new FileWriter(PATIENT_FILE_PATH, true)) {
            fw.write(patientDetails);
        } catch (IOException ex) {
            throw new RuntimeException("Error saving patient", ex);
        }
    }

    public int searchPatient(String id) {
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Patient getPatient(int index) {
        if (index < 0 || index >= patientList.size()) {
            return null;
        }
        return patientList.get(index);
    }

    public void deletePatient(Patient p) {
        patientList.remove(p);
        removePatientFromFile(p);
    }

    private void removePatientFromFile(Patient p) {
        String patientToDelete = buildPatientFileString(p);
        try {
            List<String> lines = readAllLines(PATIENT_FILE_PATH);
            StringBuilder newContent = new StringBuilder();
            StringBuilder currentBlock = new StringBuilder();
            for (String line : lines) {
                currentBlock.append(line).append("\n");
                if (currentBlock.toString().endsWith("\n\n")) { // Block end
                    if (!currentBlock.toString().equals(patientToDelete)) {
                        newContent.append(currentBlock);
                    }
                    currentBlock = new StringBuilder();
                }
            }
            writeAllText(PATIENT_FILE_PATH, newContent.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Error deleting patient from file", ex);
        }
    }

    private String buildPatientFileString(Patient p) {
        return p.getId() + "\n" + p.getName() + "\n" +
               p.getMobileNo() + "\n" + p.getGender() + "\n" +
               p.getAge() + "\n" + p.getAddress() + "\n" +
               p.getAppointedDoctor() + "\n" + p.getRoom() + "\n" +
               p.getDiagnosis() + "\n" + p.getDeposit() + "\n\n";
    }

    public void updatePatient(String patientToUpdate, Patient updatedPatient) {
        // Assume update in memory first if needed; here focus on file
        updatePatientInFile(patientToUpdate, updatedPatient);
    }

    private void updatePatientInFile(String patientToUpdate, Patient updatedPatient) {
        String updatedDetails = buildPatientFileString(updatedPatient);
        try {
            List<String> lines = readAllLines(PATIENT_FILE_PATH);
            StringBuilder newContent = new StringBuilder();
            StringBuilder currentBlock = new StringBuilder();
            for (String line : lines) {
                currentBlock.append(line).append("\n");
                if (currentBlock.toString().endsWith("\n\n")) {
                    if (currentBlock.toString().equals(patientToUpdate)) {
                        newContent.append(updatedDetails);
                    } else {
                        newContent.append(currentBlock);
                    }
                    currentBlock = new StringBuilder();
                }
            }
            writeAllText(PATIENT_FILE_PATH, newContent.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Error updating patient in file", ex);
        }
    }

    // Helper methods to reduce duplication in file ops
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