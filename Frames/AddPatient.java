package Frames;

import Classes.*;
import java.awt.*;
import javax.swing.*;

public class AddPatient extends JFrame{
    JLabel  hosLabel, pafLabel,pIdLabel,idFieldLabel, pnLabel, mbLabel, genLabel, ageLabel, addLabel, appLabel, roomLabel, disLabel, depLabel, imgLabel;
    JTextField pnTF, mbTF, addTF, ageTF, disTF, depTF;
    JRadioButton a1, a2, a3;
    JButton addBtn, bckBtn;
    ButtonGroup gBtn;
    JComboBox b1, b2;
    Color c1;
    ImageIcon img, icon;
    Font f1, f2;
    JPanel panel;
    PList pl;
    
    public AddPatient(PList pl){
        super("HealthMate");
        this.setSize(900,600);
        icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pl = pl;
        
        c1 = new Color(143,207,225);
        f1 = new Font("Georgia", Font.BOLD, 30);
        f2 = new Font("Georgia", Font.BOLD, 18);
            
        panel = new JPanel();
        panel.setLayout(null);
        
        hosLabel = new JLabel("HealthMate");
        hosLabel.setBounds(300,30,600,50);
        hosLabel.setFont(f1);
        panel.add(hosLabel);
            
        pafLabel = new JLabel("Patient Admission Form");
        pafLabel.setBounds(125,60,400,50);
        pafLabel.setFont(f2);
        panel.add(pafLabel);
        
        pIdLabel = new JLabel("Patient ID :");
        pIdLabel.setBounds(75,120,90,20);
        panel.add(pIdLabel);
            
        // FIXED: Use list size instead of pCount
        idFieldLabel = new JLabel("PID" + (1001 + pl.getInstance().getAllItemsSize()));
        idFieldLabel.setBounds(170,118,200,20);
        panel.add(idFieldLabel);      
        pnLabel = new JLabel("Patient Name :");
        pnLabel.setBounds(75,150,90,20);
        panel.add(pnLabel);
            
        pnTF = new JTextField();
        pnTF.setBounds(170,151,200,20);
        panel.add(pnTF);
            
        mbLabel = new JLabel("Mobile Number :");
        mbLabel.setBounds(75,190,110,20);
        panel.add(mbLabel);
            
        mbTF = new JTextField();
        mbTF.setBounds(170,190,200,20);
        panel.add(mbTF);
            
        genLabel = new JLabel("Gender :");
        genLabel.setBounds(75,220,80,20);
        panel.add(genLabel);
            
        a1 = new JRadioButton("Male");
        a1.setBounds(165,222,60,20);
        a1.setBackground(c1);
        panel.add(a1);
            
        a2 = new JRadioButton("Female");
        a2.setBounds(235,222,80,20);
        a2.setBackground(c1);
        panel.add(a2);
            
        a3 = new JRadioButton("Other");
        a3.setBounds(310,222,80,20);
        a3.setBackground(c1);
        panel.add(a3);
            
        gBtn = new ButtonGroup();
        gBtn.add(a1);
        gBtn.add(a2);
        gBtn.add(a3);
            
        ageLabel = new JLabel("Age :");
        ageLabel.setBounds(75,250,90,20);
        panel.add(ageLabel);
            
        ageTF = new JTextField();
        ageTF.setBounds(172,250,93,20);
        panel.add(ageTF);
            
        addLabel = new JLabel("Address :");
        addLabel.setBounds(75,280,90,20);
        panel.add(addLabel);
            
        addTF = new JTextField();
        addTF.setBounds(172,280,200,20);
        panel.add(addTF);
            
        appLabel = new JLabel("Appointed Dr. :");
        appLabel.setBounds(75,310,90,20);
        panel.add(appLabel);

        RegDoctorList rdl = RegDoctorList.getInstance();        
        String DrL[] = rdl.getDoctorNames();    
        b1 = new JComboBox(DrL);
        b1.setBounds(175,310,200,20);
        panel.add(b1);
            
        roomLabel = new JLabel("Allocated Room:");
        roomLabel.setBounds(75,340,100,20);
        panel.add(roomLabel);
            
        String rm[] = {" ", "G-301", "G-302", "G-303", "G-304", "G-305","ICU-401", "ICU-402", "CCU-403", "CCU-404", "ICCU-501", "ICCU-502", "HDU-503","HDU-504"};
        b2 = new JComboBox(rm);
        b2.setBounds(175,340,90,20);
        panel.add(b2);
            
        disLabel = new JLabel("Diagnosis and Appoinment :");
        disLabel.setBounds(75,390,90,20);
        panel.add(disLabel);
            
        disTF = new JTextField();
        disTF.setBounds(172,390,200,20);
        panel.add(disTF);
            
        depLabel = new JLabel("Deposit :");
        depLabel.setBounds(75,420,90,20);
        panel.add(depLabel);
            
        depTF = new JTextField();
        depTF.setBounds(172,420,200,20);
        panel.add(depTF);
            
        addBtn = new JButton("ADD");
        addBtn.setBounds(530,450,90,40);
        addBtn.setBackground(Color.BLACK);
        addBtn.setForeground(Color.WHITE);
        panel.add(addBtn);
            
        bckBtn = new JButton("BACK");
        bckBtn.setBounds(635,450,90,40);
        bckBtn.setBackground(Color.BLACK);
        bckBtn.setForeground(Color.WHITE);
        panel.add(bckBtn);
            
        img = new ImageIcon("images/patient.png");
        imgLabel = new JLabel(img);
        imgLabel.setBounds(450,100,300,300);
        panel.add(imgLabel);
        
        panel.setBackground(c1);
        this.add(panel);

        // Add listeners after construction
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent me) {
                addBtn.setBackground(Color.RED);
                addBtn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent me) {
                addBtn.setBackground(Color.BLACK);
                addBtn.setForeground(Color.WHITE);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                String pId = idFieldLabel.getText();
                String PName = pnTF.getText();
                String Mobile = mbTF.getText();
                String age = ageTF.getText();
                String address = addTF.getText(); 
                String Diagnosis = disTF.getText();
                String deposit = depTF.getText();
                
                String gender = "", room = "", appDoctor = "";
                if(a1.isSelected()){ gender = "Male"; }
                else if(a2.isSelected()){ gender = "Female"; }
                else if(a3.isSelected()){ gender = "Other"; }
                
                appDoctor = b1.getSelectedItem().toString();
                room = b2.getSelectedItem().toString();
                
                if(!pId.isEmpty() && !PName.isEmpty() && !Mobile.isEmpty() && !gender.isEmpty() && !age.isEmpty()  
                    && !address.isEmpty() && !appDoctor.isEmpty() && !room.isEmpty()  && !Diagnosis.isEmpty()  && !deposit.isEmpty()){
                    
                    Patient p = new Patient(pId, PName, Mobile, gender, age, address, appDoctor, room, Diagnosis, deposit);
                    pl.addPatient(p);
                    
                    JOptionPane.showMessageDialog(AddPatient.this, "Patient has added successfully");
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    AddPatient.this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(AddPatient.this, "You can not leave any field empty!");
                }
            }
        });
        
        bckBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent me) {
                bckBtn.setBackground(Color.GREEN);
                bckBtn.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent me) {
                bckBtn.setBackground(Color.BLACK);
                bckBtn.setForeground(Color.WHITE);
            }
        });
        bckBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                Menu pin = new Menu();
                pin.setVisible(true);
                AddPatient.this.setVisible(false);
            }
        });
    }
}