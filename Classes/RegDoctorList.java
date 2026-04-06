package Classes;

import java.io.*;
import java.util.Scanner;

public class RegDoctorList extends AbstractFileRepository<Doctor> {
    private static final String DOCTOR_FILE_PATH = "Files/DoctorList.txt";
    private static RegDoctorList instance;

    // Private constructor - only calls super once
    private RegDoctorList() {
        super(DOCTOR_FILE_PATH);
    }

    public static synchronized RegDoctorList getInstance() {
        if (instance == null) {
            instance = new RegDoctorList();
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
                String department = sc.nextLine();
                String joiningDate = sc.nextLine();
                String bmdcReg = sc.nextLine();
                String password = sc.nextLine();
                if (sc.hasNextLine()) sc.nextLine();

                Doctor d = new Doctor(id, name, mobileNo, gender, department, joiningDate, bmdcReg, password);
                items.add(d);
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected String buildBlock(Doctor d) {
        return d.getId() + "\n" + d.getName() + "\n" + d.getMobileNo() + "\n" + d.getGender() + "\n" +
               d.getDepartment() + "\n" + d.getJoiningDate() + "\n" + d.getBmdcReg() + "\n" +
               d.getPassword() + "\n\n";
    }

    @Override
    protected boolean blocksMatch(Doctor d, String block) {
        return buildBlock(d).equals(block);
    }

    // Original helper methods
    public int doctorExists(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Doctor checkPassword(int index, String pass) {
        if (index < 0 || index >= items.size()) return null;
        Doctor d = items.get(index);
        return d.getPassword().equals(pass) ? d : null;
    }

    public Doctor getDoctor(int index) {
        return get(index);
    }

    public String[] getDoctorNames() {
        String[] names = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            names[i] = "Dr. " + items.get(i).getName();
        }
        return names;
    }

    public void addDoctor(Doctor d) {
        add(d);
    }
}