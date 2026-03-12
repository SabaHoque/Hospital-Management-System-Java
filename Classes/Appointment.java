package Classes;

public class Appointment {
    private final String appID;
    private final String patientName;
    private final String diagnosis;
    private final String doctorName;
    private final String appDate;
    private final String appTime;

    public Appointment(String appID, String patientName, String diagnosis, String doctorName, String appDate, String appTime) {
        this.appID = appID;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
        this.appDate = appDate;
        this.appTime = appTime;
    }

    public String getAppId() {
        return appID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppDate() {
        return appDate;
    }

    public String getAppTime() {
        return appTime;
    }
}