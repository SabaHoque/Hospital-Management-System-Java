import Classes.AppointmentList;
import Classes.PList;
import Classes.PrescriptionList;
import Classes.RegDoctorList;
import Frames.Login;

public class Start1 {
    public static void main(String[] args) {
        initializeAllRepositories();
        launchLoginFrame();
    }

    private static void initializeAllRepositories() {
        System.out.println("🔄 Initializing Hospital Management System...");

        PList.getInstance();
        RegDoctorList.getInstance();
        AppointmentList.getInstance();
        PrescriptionList.getInstance();

        System.out.println("✅ All repositories loaded successfully.");
        System.out.println("✅ Design Patterns (Singleton + Template Method + Facade) active.");
    }

    private static void launchLoginFrame() {
        Login lg = new Login();
        lg.setVisible(true);
    }
}