
public class Start {
    public static void main(String[] args) {
        IDoctorService doctorService = new DoctorService();
        AddDoctorUI addDoctorUI = new AddDoctorUI(doctorService);
        addDoctorUI.setVisible(true);
    }
}
