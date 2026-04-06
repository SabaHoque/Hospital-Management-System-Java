package Classes;

public class HospitalFacade {
    private final PList patientManager = PList.getInstance();
    private final RegDoctorList doctorManager = RegDoctorList.getInstance();
    private final AppointmentList appointmentManager = AppointmentList.getInstance();
    private final PrescriptionList prescriptionManager = PrescriptionList.getInstance();

    // Patient operations
    public void addPatient(Patient p) {
        patientManager.addPatient(p);
    }

    public Patient findPatient(String id) {
        int idx = patientManager.searchPatient(id);
        return patientManager.getPatient(idx);
    }

    public void deletePatient(Patient p) {
        patientManager.deletePatient(p);
    }

    // Doctor operations
    public void addDoctor(Doctor d) {
        doctorManager.addDoctor(d);
    }

    public String[] getDoctorNames() {
        return doctorManager.getDoctorNames();
    }

    public Doctor findDoctorByName(String name) {
        int idx = doctorManager.doctorExists(name);
        return (idx != -1) ? doctorManager.getDoctor(idx) : null;
    }

    // Appointment operations
    public void bookAppointment(Appointment app) {
        appointmentManager.addAppointment(app);
    }

    public Appointment findAppointment(String appId) {
        int idx = appointmentManager.searchAppointment(appId);
        return appointmentManager.getAppointment(idx);
    }

    public void deleteAppointment(Appointment app) {
        appointmentManager.deleteAppointment(app);
    }

    // Prescription operations
    public void issuePrescription(Prescription p) {
        prescriptionManager.addPrescription(p);
    }

    public Prescription findPrescription(String patientId) {
        int idx = prescriptionManager.searchPrescription(patientId);
        return prescriptionManager.getPrescription(idx);
    }
}