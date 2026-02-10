
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDoctorUI extends JFrame {

    private IDoctorService doctorService;

    private JTextField nameTF, mobileTF, joiningTF, salaryTF, roomTF, bmdcTF;
    private JRadioButton maleRB, femaleRB, otherRB;
    private JComboBox<String> deptCB;
    private JButton addButton, backButton;
    private ButtonGroup genderGroup;

    public AddDoctorUI(IDoctorService doctorService) {
        super("Add Doctor Form");
        this.doctorService = doctorService;

        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(143,207,225));

        Font labelFont = new Font("Cascadia Code SemiBold", Font.PLAIN, 18);

        // Labels & TextFields
        JLabel titleLabel = new JLabel("Add Doctor Form");
        titleLabel.setBounds(130, 20, 300, 40);
        titleLabel.setFont(new Font("Biome", Font.BOLD, 30));
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Doctor Name:");
        nameLabel.setBounds(75, 80, 120, 25);
        nameLabel.setFont(labelFont);
        panel.add(nameLabel);

        nameTF = new JTextField();
        nameTF.setBounds(200, 80, 150, 25);
        panel.add(nameTF);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setBounds(75, 120, 120, 25);
        mobileLabel.setFont(labelFont);
        panel.add(mobileLabel);

        mobileTF = new JTextField();
        mobileTF.setBounds(200, 120, 150, 25);
        panel.add(mobileTF);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(75, 160, 100, 25);
        genderLabel.setFont(labelFont);
        panel.add(genderLabel);

        maleRB = new JRadioButton("Male");
        maleRB.setBounds(150, 160, 80, 25);
        maleRB.setBackground(panel.getBackground());
        femaleRB = new JRadioButton("Female");
        femaleRB.setBounds(230, 160, 80, 25);
        femaleRB.setBackground(panel.getBackground());
        otherRB = new JRadioButton("Other");
        otherRB.setBounds(310, 160, 80, 25);
        otherRB.setBackground(panel.getBackground());

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRB);
        genderGroup.add(femaleRB);
        genderGroup.add(otherRB);

        panel.add(maleRB);
        panel.add(femaleRB);
        panel.add(otherRB);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(75, 200, 120, 25);
        deptLabel.setFont(labelFont);
        panel.add(deptLabel);

        String[] departments = {"CARDIOLOGY", "NEUROSURGERY", "ONCOLOGIST", "ORTHOPEDICS"};
        deptCB = new JComboBox<>(departments);
        deptCB.setBounds(200, 200, 150, 25);
        panel.add(deptCB);

        JLabel joiningLabel = new JLabel("Joining Date:");
        joiningLabel.setBounds(75, 240, 120, 25);
        joiningLabel.setFont(labelFont);
        panel.add(joiningLabel);

        joiningTF = new JTextField();
        joiningTF.setBounds(200, 240, 150, 25);
        panel.add(joiningTF);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(75, 280, 120, 25);
        salaryLabel.setFont(labelFont);
        panel.add(salaryLabel);

        salaryTF = new JTextField();
        salaryTF.setBounds(200, 280, 150, 25);
        panel.add(salaryTF);

        JLabel roomLabel = new JLabel("Room No:");
        roomLabel.setBounds(75, 320, 120, 25);
        roomLabel.setFont(labelFont);
        panel.add(roomLabel);

        roomTF = new JTextField();
        roomTF.setBounds(200, 320, 150, 25);
        panel.add(roomTF);

        JLabel bmdcLabel = new JLabel("BM&DC REG:");
        bmdcLabel.setBounds(75, 360, 120, 25);
        bmdcLabel.setFont(labelFont);
        panel.add(bmdcLabel);

        bmdcTF = new JTextField();
        bmdcTF.setBounds(200, 360, 150, 25);
        panel.add(bmdcTF);

        // Buttons
        addButton = new JButton("ADD");
        addButton.setBounds(500, 470, 100, 40);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> onAdd());
        panel.add(addButton);

        backButton = new JButton("BACK");
        backButton.setBounds(620, 470, 100, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> onBack());
        panel.add(backButton);

        this.add(panel);
    }

    private void onAdd() {
        String name = nameTF.getText();
        String mobile = mobileTF.getText();
        String gender = maleRB.isSelected() ? "Male" :
                        femaleRB.isSelected() ? "Female" : "Other";
        String department = (String) deptCB.getSelectedItem();
        String joiningDate = joiningTF.getText();
        double salary = Double.parseDouble(salaryTF.getText());
        String room = roomTF.getText();
        String bmdc = bmdcTF.getText();

        Doctor doctor = new Doctor(name, mobile, gender, department, joiningDate, salary, room, bmdc);
        doctorService.addDoctor(doctor);

        JOptionPane.showMessageDialog(this, "Doctor added successfully!");
        clearFields();
    }

    private void onBack() {
        this.setVisible(false);
        DrMenu dm = new DrMenu(); // Navigation to main menu
        dm.setVisible(true);
    }

    private void clearFields() {
        nameTF.setText("");
        mobileTF.setText("");
        genderGroup.clearSelection();
        deptCB.setSelectedIndex(0);
        joiningTF.setText("");
        salaryTF.setText("");
        roomTF.setText("");
        bmdcTF.setText("");
    }
}
