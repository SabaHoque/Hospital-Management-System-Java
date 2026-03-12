package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegDoctorList {
    private static final String DOCTOR_FILE_PATH = "Files/DoctorList.txt";
    private final List<Doctor> doctorList = new ArrayList<>();

    public RegDoctorList() {
        loadDoctorsFromFile();
    }

    private void loadDoctorsFromFile() {
        File file = new File(DOCTOR_FILE_PATH);
        if (!file.exists()) {
            return; // Silently handle missing file, assume empty list
        }
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
                if (sc.hasNextLine()) sc.nextLine(); // Skip extra newline

                Doctor d = new Doctor(id, name, mobileNo, gender, department, joiningDate, bmdcReg, password);
                doctorList.add(d);
            }
        } catch (FileNotFoundException ex) {
            // Log or handle appropriately; for now, empty list
        } catch (IOException ex) {
            throw new RuntimeException("Error loading doctors", ex);
        }
    }

    public void addDoctor(Doctor d) {
        doctorList.add(d);
        saveDoctorToFile(d);
    }

    private void saveDoctorToFile(Doctor d) {
        String drDetails = d.getId() + "\n" + d.getName() + "\n" + d.getMobileNo() + "\n" + d.getGender() + "\n" +
                           d.getDepartment() + "\n" + d.getJoiningDate() + "\n" + d.getBmdcReg() + "\n" +
                           d.getPassword() + "\n\n";
        try (FileWriter fw = new FileWriter(DOCTOR_FILE_PATH, true)) {
            fw.write(drDetails);
        } catch (IOException ex) {
            throw new RuntimeException("Error saving doctor", ex);
        }
    }

    public int doctorExists(String name) {
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Doctor checkPassword(int index, String pass) {
        if (index < 0 || index >= doctorList.size()) {
            return null;
        }
        Doctor d = doctorList.get(index);
        return d.getPassword().equals(pass) ? d : null;
    }

    public Doctor getDoctor(int index) {
        if (index < 0 || index >= doctorList.size()) {
            return null;
        }
        return doctorList.get(index);
    }

    public String[] getDoctorNames() {
        String[] doctorNames = new String[doctorList.size()];
        for (int i = 0; i < doctorList.size(); i++) {
            doctorNames[i] = "Dr. " + doctorList.get(i).getName();
        }
        return doctorNames;
    }
}