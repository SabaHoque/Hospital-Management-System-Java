package Classes;

public class Prescription {
    private final String patientId;
    private final String patientName;
    private final String diagnosis;
    private final String doctorName;
    private final String medicineName;
    private final String dosage;
    private final String usageInstructions;

    public Prescription(String patientId, String patientName, String diagnosis, String doctorName, String medicineName, String dosage, String usageInstructions) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.usageInstructions = usageInstructions;
    }

    public String getPatientId() {
        return patientId;
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

    public String getMedicineName() {
        return medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getUsageInstructions() {
        return usageInstructions;
    }
}