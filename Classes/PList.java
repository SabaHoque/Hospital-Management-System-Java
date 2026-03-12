package Classes;
import Interfaces.*;
import java.io.*;
import java.util.Scanner;

public class PList implements IPList {
    public Patient[] patientList = new Patient[100];
    public static int pCount = 0;

    public PList() {
        loadPatientsFromFile();
    }

    // Extracted for SRP: File loading isolated
    private void loadPatientsFromFile() {
        try {
            File file = new File("Files/PatientList.txt");
            Scanner sc = new Scanner(file);
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
                if (sc.hasNextLine()) sc.nextLine(); // Skip empty line

                Patient p = new Patient(id, name, mobileNo, gender, age, address, appointedDoctor, room, diagnosis, deposit);
                patientList[pCount] = p;
                pCount++;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void addPatient(Patient p) {
        if (pCount >= patientList.length) {
            throw new ArrayIndexOutOfBoundsException("Array full");
        }
        patientList[pCount] = p;
        pCount++;
        savePatientToFile(p);
    }

    // Extracted for SRP: File saving isolated
    private void savePatientToFile(Patient p) {
        String patientDetails = p.getId() + "\n" + p.getName() + "\n" +
                                p.getMobileNo() + "\n" + p.getGender() + "\n" +
                                p.getAge() + "\n" + p.getAddress() + "\n" +
                                p.getAppointedDoctor() + "\n" + p.getRoom() + "\n" +
                                p.getDiagnosis() + "\n" + p.getDeposit() + "\n\n";
        try {
            FileWriter fw = new FileWriter("Files/PatientList.txt", true);
            fw.write(patientDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int searchPatient(String id) {
        int index = -1;
        for (int i = 0; i < pCount; i++) {
            if (patientList[i] != null && patientList[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Patient getPatient(int index) {
        return patientList[index];
    }

    public void deletePatient(Patient p) {
        for (int i = 0; i < pCount; i++) {
            if (patientList[i] == p) {
                for (int j = i; j < pCount - 1; j++) {
                    patientList[j] = patientList[j + 1];
                }
                pCount--;
                patientList[pCount] = null;
                break;
            }
        }
        removePatientFromFile(p);
    }

    // Extracted for SRP: File deletion isolated
    private void removePatientFromFile(Patient p) {
        String patientToDelete = p.getId() + "\n" + p.getName() + "\n" +
                                 p.getMobileNo() + "\n" + p.getGender() + "\n" +
                                 p.getAge() + "\n" + p.getAddress() + "\n" +
                                 p.getAppointedDoctor() + "\n" + p.getRoom() + "\n" +
                                 p.getDiagnosis() + "\n" + p.getDeposit() + "\n\n";
        try {
            String filepath = "Files/PatientList.txt";
            File originalFile = new File(filepath);
            String newDetails = "";
            Scanner sc = new Scanner(originalFile);
            while (sc.hasNext()) {
                String readUser = readPatientBlock(sc);
                if (readUser.equals(patientToDelete)) {
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

    // Helper method to read a patient block (refactored for readability)
    private String readPatientBlock(Scanner sc) {
        String readUser = sc.nextLine() + "\n";
        for (int i = 0; i < 9; i++) {
            if (sc.hasNextLine()) readUser += sc.nextLine() + "\n";
        }
        return readUser;
    }

    public void updatePatient(String patientToUpdate, Patient updatedPatient) {
        updatePatientInFile(patientToUpdate, updatedPatient);
    }

    // Extracted for SRP: File update isolated
    private void updatePatientInFile(String patientToUpdate, Patient updatedPatient) {
        String updatedDetails = updatedPatient.getId() + "\n" +
                                updatedPatient.getName() + "\n" +
                                updatedPatient.getMobileNo() + "\n" +
                                updatedPatient.getGender() + "\n" +
                                updatedPatient.getAge() + "\n" +
                                updatedPatient.getAddress() + "\n" +
                                updatedPatient.getAppointedDoctor() + "\n" +
                                updatedPatient.getRoom() + "\n" +
                                updatedPatient.getDiagnosis() + "\n" +
                                updatedPatient.getDeposit() + "\n\n";
        try {
            String filepath = "Files/PatientList.txt";
            File originalFile = new File(filepath);
            String newDetails = "";
            Scanner sc = new Scanner(originalFile);
            while (sc.hasNext()) {
                String readUser = readPatientBlock(sc);
                if (readUser.equals(patientToUpdate + "\n")) { // Adjust for extra newline if needed
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