package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrescriptionList {
    private static final String PRESCRIPTION_FILE_PATH = "Files/prescriptions.txt";
    private final List<Prescription> prescriptionList = new ArrayList<>();

    public PrescriptionList() {
        loadPrescriptionsFromFile();
    }

    private void loadPrescriptionsFromFile() {
        File file = new File(PRESCRIPTION_FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String patientId = sc.nextLine().split(": ", 2)[1];
                String patientName = sc.nextLine().split(": ", 2)[1];
                String diagnosis = sc.nextLine().split(": ", 2)[1];
                String doctorName = sc.nextLine().split(": ", 2)[1];
                String medicineName = sc.nextLine().split(": ", 2)[1];
                String dosage = sc.nextLine().split(": ", 2)[1];
                String usageInstructions = sc.nextLine().split(": ", 2)[1];
                if (sc.hasNextLine()) sc.nextLine();

                Prescription p = new Prescription(patientId, patientName, diagnosis, doctorName, medicineName, dosage, usageInstructions);
                prescriptionList.add(p);
            }
        } catch (FileNotFoundException ex) {
            // Empty list
        } catch (IOException ex) {
            throw new RuntimeException("Error loading prescriptions", ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new RuntimeException("Invalid file format", ex);
        }
    }

    public void addPrescription(Prescription p) {
        prescriptionList.add(p);
        savePrescriptionToFile(p);
    }

    private void savePrescriptionToFile(Prescription p) {
        String prescriptionDetails = "Patient ID: " + p.getPatientId() + "\n" +
                                     "Patient Name: " + p.getPatientName() + "\n" +
                                     "Diagnosis: " + p.getDiagnosis() + "\n" +
                                     "Doctor's Name: " + p.getDoctorName() + "\n" +
                                     "Medicine Name: " + p.getMedicineName() + "\n" +
                                     "Dosage: " + p.getDosage() + "\n" +
                                     "Usage Instructions: " + p.getUsageInstructions() + "\n\n";
        try (FileWriter fw = new FileWriter(PRESCRIPTION_FILE_PATH, true)) {
            fw.write(prescriptionDetails);
        } catch (IOException ex) {
            throw new RuntimeException("Error saving prescription", ex);
        }
    }

    public int searchPrescription(String patientId) {
        for (int i = 0; i < prescriptionList.size(); i++) {
            if (prescriptionList.get(i).getPatientId().equalsIgnoreCase(patientId)) {
                return i;
            }
        }
        return -1;
    }

    public Prescription getPrescription(int index) {
        if (index < 0 || index >= prescriptionList.size()) {
            return null;
        }
        return prescriptionList.get(index);
    }
}