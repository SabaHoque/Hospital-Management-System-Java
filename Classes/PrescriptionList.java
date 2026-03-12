package Classes;

import java.io.*;
import java.util.Scanner;

public class PrescriptionList {
    public Prescription[] prescriptionList = new Prescription[100];
    public static int pCount = 0;

    public PrescriptionList() {
        loadPrescriptionsFromFile();
    }

    // Extracted method for loading prescriptions (SRP: isolates file reading)
    private void loadPrescriptionsFromFile() {
        try {
            File file = new File("Files/prescriptions.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String patientId = sc.nextLine().split(": ")[1];
                String patientName = sc.nextLine().split(": ")[1];
                String diagnosis = sc.nextLine().split(": ")[1];
                String doctorName = sc.nextLine().split(": ")[1];
                String medicineName = sc.nextLine().split(": ")[1];
                String dosage = sc.nextLine().split(": ")[1];
                String usageInstructions = sc.nextLine().split(": ")[1];
                if (sc.hasNextLine()) sc.nextLine(); // Skip empty line

                Prescription p = new Prescription(patientId, patientName, diagnosis, doctorName, medicineName, dosage, usageInstructions);
                prescriptionList[pCount] = p;
                pCount++;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("File not found or error reading file.");
        }
    }

    public void addPrescription(Prescription p) {
        if (pCount >= prescriptionList.length) {
            throw new ArrayIndexOutOfBoundsException("Array full");
        }
        prescriptionList[pCount] = p;
        pCount++;
        savePrescriptionToFile(p);
    }

    // Extracted method for saving prescription (SRP: isolates file writing)
    private void savePrescriptionToFile(Prescription p) {
        String prescriptionDetails = "Patient ID: " + p.getPatientId() + "\n" +
                                     "Patient Name: " + p.getPatientName() + "\n" +
                                     "Diagnosis: " + p.getDiagnosis() + "\n" +
                                     "Doctor's Name: " + p.getDoctorName() + "\n" +
                                     "Medicine Name: " + p.getMedicineName() + "\n" +
                                     "Dosage: " + p.getDosage() + "\n" +
                                     "Usage Instructions: " + p.getUsageInstructions() + "\n\n";
        try {
            FileWriter fw = new FileWriter("Files/prescriptions.txt", true);
            fw.write(prescriptionDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int searchPrescription(String patientId) {
        int index = -1;
        for (int i = 0; i < pCount; i++) {
            if (prescriptionList[i] != null && prescriptionList[i].getPatientId().equalsIgnoreCase(patientId)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Prescription getPrescription(int index) {
        return prescriptionList[index];
    }
}