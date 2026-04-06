import javax.swing.*;

public class DoctorNotification implements DoctorObserver {

    @Override
    public void update(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}