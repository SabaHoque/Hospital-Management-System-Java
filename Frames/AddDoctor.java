package Frames;

import Classes.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddDoctor extends JFrame {
	
	JLabel idField, idLabel, userLabel, passLabel, genderLabel, programLabel, imgLabel, conpLabel;
	JTextField userTF, nmTF, dateTF, bmdcTF;
	JPasswordField passTF, conpTF;
	JButton add, backBtn;
	ButtonGroup bg1;
	JRadioButton r1, r2, r3;
	JComboBox<String> dept;
	Color color1, color2;
	Font font1, font4;
	ImageIcon img;
	JPanel panel;
	RegDoctorList rdl;
	
	public AddDoctor(RegDoctorList rdl){
		super("HealthMate - Doctor Registration");
		this.setSize(900,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.rdl = rdl;
	
		color1 = new Color(143,207,225);
		color2 = new Color(4,196,95);
		
		font1 = new Font("Biome", Font.BOLD, 30);
		font4 = new Font("Biome", Font.BOLD, 18);
	
		panel = new JPanel();
		panel.setLayout(null);
		
		userLabel = new JLabel("HealthMate ");
		userLabel.setBounds(300,10,400,100); 
		userLabel.setFont(font1);
		panel.add(userLabel);
		
		userLabel = new JLabel("Doctor Registration Form ");
		userLabel.setBounds(130,80,450,30);
        userLabel.setFont(font4);		
		panel.add(userLabel);
		
		idLabel = new JLabel("Doctor ID: ");
		idLabel.setBounds(75,91,100,100); 
		panel.add(idLabel);
		
		// FIXED: Use getAllItemsSize() instead of doctorCount
		idField = new JLabel("DID" + (2001 + RegDoctorList.getInstance().getAllItemsSize()));
		idField.setBounds(170,130,100,20);
		panel.add(idField);
		
		userLabel = new JLabel("Doctor Name: ");
		userLabel.setBounds(75,120,100,100); 
		panel.add(userLabel);	
		
		userTF = new JTextField();
		userTF.setBounds(170,162,200,20);
		panel.add(userTF);
		
		userLabel = new JLabel("Mobile Number: ");
		userLabel.setBounds(75,150,100,100); 
		panel.add(userLabel);

		nmTF = new JTextField();
		nmTF.setBounds(170,190,200,20);
		panel.add(nmTF);
		
		genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(75,220,115,20);
		panel.add(genderLabel);
		
		r1 = new JRadioButton("Male");
		r1.setBounds(163,220,70,20);
		r1.setBackground(color1);
		panel.add(r1);
		
		r2 = new JRadioButton("Female");
		r2.setBounds(230,220,80,20);
		r2.setBackground(color1);
		panel.add(r2);
		
		r3 = new JRadioButton("Other");
		r3.setBounds(310,220,80,20);
		r3.setBackground(color1);
		panel.add(r3);
		
		bg1 = new ButtonGroup();
		bg1.add(r1);
		bg1.add(r2);
		bg1.add(r3);
		
		programLabel = new JLabel("Department:");
		programLabel.setBounds(75,210,100,100);
		panel.add(programLabel);
		
		String items[] = {"MEDICINE","CARDIOLOGY", "NEUROSURGERY", "ONCOLOGIST", "ORTHOPEDICS","SURGERY","PSYCHIATRY","ANESTHESIOLOGY"};
		dept = new JComboBox<>(items);
		dept.setBounds(170,250,200,20);
		panel.add(dept);
		
		userLabel = new JLabel("Joining Date: ");
		userLabel.setBounds(75,240,100,100); 
		panel.add(userLabel);
		
		dateTF = new JTextField();
		dateTF.setBounds(170,280,200,20);
		panel.add(dateTF);
		
		userLabel = new JLabel("BM&DC REG: ");
		userLabel.setBounds(75,270,100,100); 
		panel.add(userLabel);
		
		bmdcTF = new JTextField();
		bmdcTF.setBounds(170,310,200,20);
		panel.add(bmdcTF);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(75,300,100,100); 
		panel.add(passLabel);
		
		passTF = new JPasswordField();
		passTF.setBounds(170,338,200,20);
		panel.add(passTF);
		
		conpLabel = new JLabel("Confirm Password : ");
		conpLabel.setBounds(75,328,120,100); 
		panel.add(conpLabel);
		
		conpTF = new JPasswordField();
		conpTF.setBounds(195,368,175,20);
		panel.add(conpTF);
		
		add = new JButton("Register");
		add.setBounds(500,470,100,40);
		add.setBackground(color2);
		add.setForeground(Color.WHITE);
		add.setOpaque(true);
		add.addMouseListener(new ButtonHoverListener(color2, Color.WHITE, Color.BLACK));
		add.addActionListener(new RegisterDoctorAction());
		panel.add(add);
		
		backBtn = new JButton("BACK");
		backBtn.setBounds(620,470,100,40);
		backBtn.setBackground(Color.RED);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(new ButtonHoverListener(Color.RED, Color.BLACK, Color.WHITE));
		backBtn.addActionListener(new BackAction());
		panel.add(backBtn);
		
		img = new ImageIcon("images/emp-info.png");
		
		imgLabel = new JLabel(img);
		imgLabel.setBounds(485,100,300,300);
		panel.add(imgLabel);

		panel.setBackground(color1);
		this.add(panel);
	}
		
	private void registerDoctor() {
		String id = idField.getText();
		String name = userTF.getText();
		String mobileNo = nmTF.getText();
		
		String department = dept.getSelectedItem().toString();
		String gender = "";
		if(r1.isSelected()){ gender = "Male"; }
		else if(r2.isSelected()){ gender = "Female"; }
		else if(r3.isSelected()){ gender = "Other"; }
		
		String joiningDate = dateTF.getText();
		String bmdcReg = bmdcTF.getText();
		
		String password = String.valueOf(passTF.getPassword());
		String confirmPass = String.valueOf(conpTF.getPassword());
		
		if(!id.isEmpty() && !name.isEmpty() && !password.isEmpty() && !confirmPass.isEmpty() &&
			!gender.isEmpty() && !department.isEmpty() && !bmdcReg.isEmpty()){
			if(password.equals(confirmPass)){
				Doctor d = new Doctor(id, name, mobileNo, gender, department, joiningDate, bmdcReg, password);
				rdl.addDoctor(d);
				DrLogin dlg = new DrLogin(rdl);
				dlg.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Password doesn't match!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You can not leave any field empty!");
		}
	}
	
	private void goBack() {
		Login lgn = new Login();
		lgn.setVisible(true);
		this.setVisible(false);
	}
	
	private static class RegisterDoctorAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			AddDoctor frame = (AddDoctor) SwingUtilities.getWindowAncestor((Component) ae.getSource());
			frame.registerDoctor();
		}
	}
	
	private static class BackAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			AddDoctor frame = (AddDoctor) SwingUtilities.getWindowAncestor((Component) ae.getSource());
			frame.goBack();
		}
	}
	
	private static class ButtonHoverListener extends MouseAdapter {
		private final Color bg;
		private final Color enterFg;
		private final Color exitFg;
		
		ButtonHoverListener(Color bg, Color enterFg, Color exitFg) {
			this.bg = bg;
			this.enterFg = enterFg;
			this.exitFg = exitFg;
		}
		
		@Override
		public void mouseEntered(MouseEvent me) {
			JButton btn = (JButton) me.getSource();
			btn.setBackground(bg);
			btn.setForeground(enterFg);
		}
		
		@Override
		public void mouseExited(MouseEvent me) {
			JButton btn = (JButton) me.getSource();
			btn.setBackground(bg);
			btn.setForeground(exitFg);
		}
	}
}