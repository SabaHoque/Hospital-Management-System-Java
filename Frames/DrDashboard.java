package Frames;
import Classes.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class DrDashboard extends JFrame implements ActionListener {
    
    JLabel userLabel, drLabel, dptLabel, imgLabel, ptListLabel;
    JButton exitBtn, dndBtn, presBtn, appBtn;
    Color color1, color2;
    Font font1, font4;
    ImageIcon img;
    JPanel panel;
    JTable table;
    DefaultTableModel model;
    Doctor d;
    RegDoctorList rdl;
    
    public DrDashboard(Doctor d, RegDoctorList rdl) {
        super("HealthMate - Doctor Dashboard");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        img = new ImageIcon("images/emp-info.png");
        this.setIconImage(img.getImage());
        this.d = d;
        this.rdl = rdl;
        
        color1 = new Color(143, 207, 225);
        color2 = new Color(182, 224, 232);
        
        font1 = new Font("Biome", Font.BOLD, 30);
        font4 = new Font("Biome", Font.BOLD, 18);
        
        panel = new JPanel();
        panel.setLayout(null);
        
        userLabel = new JLabel("HealthMate");
        userLabel.setBounds(350, 5, 400, 100);
        userLabel.setFont(font1);
        panel.add(userLabel);
        
        userLabel = new JLabel("Doctor Dashboard");
        userLabel.setBounds(350, 55, 400, 50);
        userLabel.setFont(font4);
        panel.add(userLabel);
        
        ptListLabel = new JLabel("Your patient list: ");
        ptListLabel.setBounds(70, 100, 300, 40);
        ptListLabel.setFont(font4);
        panel.add(ptListLabel);
        
        String dName = "Dr. " + d.getName();
        drLabel = new JLabel("Welcome, " + dName);
        drLabel.setBounds(670, 90, 180, 40);
        panel.add(drLabel);
        
        dptLabel = new JLabel("Department of " + d.getDepartment());
        dptLabel.setBounds(670, 110, 180, 40);
        panel.add(dptLabel);
        
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setBackground(color2);
        table.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        model.addColumn("PID");
        model.addColumn("Patient Name");
        model.addColumn("Room No");
        model.addColumn("Diagnosis");
        
        PList ptList = PList.getInstance();
        for (int i = 0; i < ptList.getInstance().getAllItemsSize(); i++) {
            Patient patient = ptList.getPatient(i);
            if (patient != null && patient.getAppointedDoctor().equals(dName)) {
                model.addRow(new Object[]{patient.getId(), patient.getName(), patient.getRoom(), patient.getDiagnosis()});
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 190, 750, 270);
        panel.add(scrollPane);

        appBtn = new JButton("APPOINTMENTS");
        appBtn.setBounds(140, 470, 150, 40);
        appBtn.setBackground(Color.RED);
        appBtn.setForeground(Color.WHITE);
        appBtn.setOpaque(true);
        appBtn.addActionListener(this);
        panel.add(appBtn);
        
        presBtn = new JButton("PRESCRIBE");
        presBtn.setBounds(315, 470, 115, 40);
        presBtn.setBackground(Color.RED);
        presBtn.setForeground(Color.WHITE);
        presBtn.setOpaque(true);
        presBtn.addActionListener(this);
        panel.add(presBtn);

        dndBtn = new JButton("DOWNLOAD LIST");
        dndBtn.setBounds(460, 470, 140, 40);
        dndBtn.setBackground(Color.RED);
        dndBtn.setForeground(Color.WHITE);
        dndBtn.setOpaque(true);
        dndBtn.addActionListener(this);
        panel.add(dndBtn);
        
        exitBtn = new JButton("LOGOUT");
        exitBtn.setBounds(615, 470, 100, 40);
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setOpaque(true);
        exitBtn.addActionListener(this);
        panel.add(exitBtn);
        
        panel.setBackground(color1);
        this.add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String dName = "Dr. " + d.getName();
        
        if (ae.getSource() == exitBtn) {
            Login lg = new Login();
            lg.setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource() == dndBtn) {
            JOptionPane.showMessageDialog(this, "Your patient list has downloaded. Check the Download folder");
            PList ptList = PList.getInstance();
            String PatList = "Your Patient List -- " + "\n" + "\n";
            for (int i = 0; i < ptList.getInstance().getAllItemsSize(); i++) {
                Patient patient = ptList.getPatient(i);
                if (patient != null && patient.getAppointedDoctor() != null && patient.getAppointedDoctor().equals(dName)) {
                    PatList += "Patient ID : " + patient.getId() + "\n" + "Patient Name : " + patient.getName() + "\n" + 
                                            "Room NO : " + patient.getRoom() + "\n" + "Diagnosis : " + patient.getDiagnosis() + "\n" + "\n";
                }
            }
            try {
                FileWriter fw = new FileWriter("Downloads/Patient List For " + dName + ".doc");
                fw.write("HealthMate" + "\n");
                fw.write("--------------------------------------" + "\n");
                fw.write("Date : " + "\n");
                fw.write("Doctor Name : " + dName + "\n");
                fw.write("--------------------------------------" + "\n");
                fw.write(PatList);
                fw.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } 
        else if (ae.getSource() == presBtn) {
            WritePres form = new WritePres();
            form.setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource() == appBtn) {
            ViewAppointments frame = new ViewAppointments();
            frame.setVisible(true);
            this.setVisible(false);
        }
    }
}