package Classes;

import java.io.*;
import java.util.Scanner;

public class RegDoctorList {
    public Doctor[] doctorList = new Doctor[100];
    public static int doctorCount = 0;

    public RegDoctorList() {
        loadDoctorsFromFile();
    }

    // Extracted method for loading doctors (SRP: separates file reading from class initialization)
    private void loadDoctorsFromFile() {
        try {
            File file = new File("Files/DoctorList.txt");
            Scanner sc = new Scanner(file);
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
                doctorList[doctorCount] = d;
                doctorCount++;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void addDoctor(Doctor d) {
        doctorList[doctorCount] = d;
        doctorCount++;
        saveDoctorToFile(d);
    }

    // Extracted method for saving doctor (SRP: separates file writing from adding logic)
    private void saveDoctorToFile(Doctor d) {
        String drDetails = d.getId() + "\n" + d.getName() + "\n" + d.getMobileNo() + "\n" + d.getGender() + "\n" +
                           d.getDepartment() + "\n" + d.getJoiningDate() + "\n" + d.getBmdcReg() + "\n" +
                           d.getPassword() + "\n" + "\n";
        try {
            FileWriter fw = new FileWriter("Files/DoctorList.txt", true);
            fw.write(drDetails);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int doctorExists(String name) {
        int index = -1;
        for (int i = 0; i < doctorCount; i++) {
            if (doctorList[i] != null && doctorList[i].getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Doctor checkPassword(int index, String pass) {
        Doctor d = null;
        if (doctorList[index].getPassword().equals(pass)) {
            d = doctorList[index];
        }
        return d;
    }

    public Doctor getDoctor(int index) {
        return doctorList[index];
    }

    public String[] getDoctorNames() {
        String[] doctorNames = new String[doctorCount];
        for (int i = 0; i < doctorCount; i++) {
            if (doctorList[i] != null) {
                doctorNames[i] = "Dr. " + doctorList[i].getName();
            }
        }
        return doctorNames;
    }
}