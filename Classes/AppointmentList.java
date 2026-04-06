package Classes;

import java.io.*;
import java.util.Scanner;

public class AppointmentList extends AbstractFileRepository<Appointment> {
    private static final String APPOINTMENT_FILE_PATH = "Files/AppointmentList.txt";
    private static AppointmentList instance;

    private AppointmentList() {
        super(APPOINTMENT_FILE_PATH);
    }

    public static synchronized AppointmentList getInstance() {
    if (instance == null) {
        instance = new AppointmentList();
    }
    return instance;
}

    @Override
    protected void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

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
                items.add(app);
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected String buildBlock(Appointment app) {
        return app.getAppId() + "\n" +
               app.getPatientName() + "\n" +
               app.getDiagnosis() + "\n" +
               app.getDoctorName() + "\n" +
               app.getAppDate() + "\n" +
               app.getAppTime() + "\n\n";
    }

    @Override
    protected boolean blocksMatch(Appointment app, String block) {
        return buildBlock(app).equals(block);
    }

    public void addAppointment(Appointment app) {
        add(app);
    }

    public int searchAppointment(String appId) {
        return searchByKey(appId, Appointment::getAppId);
    }

    public Appointment getAppointment(int index) {
        return get(index);
    }

    public void deleteAppointment(Appointment app) {
        delete(app);
    }

    public void updateAppointment(String appointmentToUpdate, Appointment updatedAppointment) {
        update(appointmentToUpdate, updatedAppointment);
    }
}