package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.mindrot.jbcrypt.BCrypt;

import Entities.admins;
import SaveToMySQL.AdminConnect;


public class LogUpAdmin extends JFrame {
	LogUpAdmin lua;
	private JLabel jLabel_id;
	private JTextField jTextField_id;
	private JLabel jLabel_adminName;
	private JTextField jTextField_adminName;
	private JLabel jLabel_pass;
	private JTextField jTextField_pass;
	private JLabel jLabel_dateOfbirth;
	private JTextField jTextField_dateOfBirth;
	private JLabel jLabel_email;
	private JTextField jTextField_email;
	private JLabel jLabel_gender;
	private JRadioButton jRadioButton_male;
	private JRadioButton jRadioButton_female;
	private ButtonGroup buttonGroup_gender;
	private JLabel jLabel_phone;
	private JTextField jTextField_phone;
	private JButton jButton_save;
	private JButton jButton_back;

	public LogUpAdmin() {
		this.setTitle("Log Up");
		this.setSize(800, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel jLabel_image = new JLabel();
		jLabel_image.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(LogUpAdmin.class.getResource("SignUp.png"))));

		Font f = new Font("Arial", Font.BOLD, 15);
		jLabel_id = new JLabel("ID");
		jLabel_id.setFont(f);
		jTextField_id = new JTextField(10);
		jTextField_id.setFont(f);
		jLabel_adminName = new JLabel("Name");
		jLabel_adminName.setFont(f);
		jTextField_adminName = new JTextField(10);
		jTextField_adminName.setFont(f);
		jLabel_pass = new JLabel("Password");
		jLabel_pass.setFont(f);
		jTextField_pass = new JTextField(10);
		jTextField_pass.setFont(f);
		jLabel_dateOfbirth = new JLabel("Date of birth");
		jLabel_dateOfbirth.setFont(f);
		jTextField_dateOfBirth = new JTextField(10);
		jTextField_dateOfBirth.setFont(f);
		jLabel_email = new JLabel("Email");
		jLabel_email.setFont(f);
		jTextField_email = new JTextField(10);
		jTextField_email.setFont(f);
		jLabel_gender = new JLabel("Gender");
		jLabel_gender.setFont(f);
		jRadioButton_male = new JRadioButton("Male");
		jRadioButton_male.setFont(f);
		jRadioButton_female = new JRadioButton("Female");
		jRadioButton_female.setFont(f);

		buttonGroup_gender = new ButtonGroup();
		buttonGroup_gender.add(jRadioButton_male);
		buttonGroup_gender.add(jRadioButton_female);

		jLabel_phone = new JLabel("Phone");
		jLabel_phone.setFont(f);
		jTextField_phone = new JTextField(10);
		jTextField_phone.setFont(f);

		JPanel jPanel_gender = new JPanel();
		jPanel_gender.setLayout(new GridLayout(1, 2, 10, 10));
		jPanel_gender.add(jRadioButton_male);
		jPanel_gender.add(jRadioButton_female);

		jLabel_phone = new JLabel("Phone");
		jLabel_phone.setFont(f);
		jTextField_phone = new JTextField(10);
		jTextField_phone.setFont(f);

		JPanel jPanel_input = new JPanel();
		jPanel_input.setLayout(new GridLayout(7, 2, 3, 3));
		jPanel_input.add(jLabel_id);
		jPanel_input.add(jTextField_id);
		jPanel_input.add(jLabel_adminName);
		jPanel_input.add(jTextField_adminName);
		jPanel_input.add(jLabel_pass);
		jPanel_input.add(jTextField_pass);
		jPanel_input.add(jLabel_dateOfbirth);
		jPanel_input.add(jTextField_dateOfBirth);
		jPanel_input.add(jLabel_email);
		jPanel_input.add(jTextField_email);
		jPanel_input.add(jLabel_gender);
		jPanel_input.add(jPanel_gender);
		jPanel_input.add(jLabel_phone);
		jPanel_input.add(jTextField_phone);

		jButton_save = new JButton("Save");
		jButton_save.setFont(f);
		jButton_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Save")) {
					saveAdmin();
				}
			}
		});
		jButton_back = new JButton("Back");
		jButton_back.setFont(f);
		jButton_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInAdmin();
			}
		});

		JPanel jPanel_button = new JPanel();
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_save);
		jPanel_button.add(jButton_back);

		JPanel jPanel_logUp = new JPanel();
		jPanel_logUp.setLayout(new BorderLayout());
		jPanel_logUp.add(jPanel_input, BorderLayout.CENTER);
		jPanel_logUp.add(jPanel_button, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(jPanel_logUp, BorderLayout.EAST);
		this.add(jLabel_image, BorderLayout.WEST);
		this.setVisible(true);
	}

	public void saveAdmin() {
		try {
			String idAdmin = jTextField_id.getText();
			String nameAdmin = jTextField_adminName.getText();
			String password = encrypt(jTextField_pass.getText());
			String dateAdmin = jTextField_dateOfBirth.getText();
			String emailAdmin = jTextField_email.getText();
			String gender;
			if (jRadioButton_male.isSelected()) {
				gender = jRadioButton_male.getText();
			} else {
				gender = jRadioButton_female.getText();
			}
			String phoneAdmin = jTextField_phone.getText();
			admins admin = new admins(idAdmin, nameAdmin, password, dateAdmin, emailAdmin, gender, phoneAdmin);
			
//			//send data to server:
			try {
				Socket socket = new Socket("192.168.1.7", 7000);
				//Send request to server:
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println("Register");
				
				//send information:
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(admin);
				System.out.println("Connect and sent data to server successfully!");
				JOptionPane.showMessageDialog(lua, "Successfully sign up!");
				socket.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(lua, "Log up failed because your devices coudn't connect to server!", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Couldn't connect to server");
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//function to hash pasword:
	public static String encrypt(String pass) {
//		try {
//			String salf = "@killnet";
//			pass = pass + salf;
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] encode = md.digest(pass.getBytes());
//			BigInteger number = new BigInteger(1, encode);
//			String hashText = number.toString(15);
//			return hashText;
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
		return BCrypt.hashpw(pass, BCrypt.gensalt());
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LogUpAdmin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
