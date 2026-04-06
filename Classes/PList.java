package Classes;

import Interfaces.*;
import java.io.*;
import java.util.Scanner;

public class PList extends AbstractFileRepository<Patient> implements IPList {
    private static final String PATIENT_FILE_PATH = "Files/PatientList.txt";
    private static PList instance;

    private PList() {
        super(PATIENT_FILE_PATH);
    }

    public static synchronized PList getInstance() {
        if (instance == null) {
            instance = new PList();
        }
        return instance;
    }

    @Override
    protected void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

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
                items.add(p);
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected String buildBlock(Patient p) {
        return p.getId() + "\n" + p.getName() + "\n" + p.getMobileNo() + "\n" + 
               p.getGender() + "\n" + p.getAge() + "\n" + p.getAddress() + "\n" + 
               p.getAppointedDoctor() + "\n" + p.getRoom() + "\n" + 
               p.getDiagnosis() + "\n" + p.getDeposit() + "\n\n";
    }

    @Override
    protected boolean blocksMatch(Patient p, String block) {
        return buildBlock(p).equals(block);
    }

    @Override
    public void addPatient(Patient p) {
        add(p);
    }

    @Override
    public int searchPatient(String id) {
        return searchByKey(id, Patient::getId);
    }

    @Override
    public Patient getPatient(int index) {
        return get(index);
    }

    @Override
    public void deletePatient(Patient p) {
        delete(p);
    }

    @Override
    public void updatePatient(String patientToUpdate, Patient updatedPatient) {
        update(patientToUpdate, updatedPatient);
    }
}