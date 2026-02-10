
import javax.swing.*;
import java.awt.*;

public class DrMenu extends JFrame {

    public DrMenu() {
        super("Doctor Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("This is the Doctor Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label);
        JButton addDoctorButton = new JButton("ADD DOCTOR");
        addDoctorButton.setBounds(75, 380, 200, 40); // adjust X, Y to fit below other buttons
        addDoctorButton.setBackground(Color.BLACK);
        addDoctorButton.setForeground(Color.WHITE);
        addDoctorButton.setFont(new Font("Cascadia Code SemiBold", Font.PLAIN, 16));
        addDoctorButton.addActionListener(e -> {
        IDoctorService doctorService = new DoctorService(); // Use the service
        AddDoctorUI addDoctorUI = new AddDoctorUI(doctorService);
        addDoctorUI.setVisible(true);
        this.setVisible(false); // Hide menu while AddDoctor form is open
});

        
    }
}
