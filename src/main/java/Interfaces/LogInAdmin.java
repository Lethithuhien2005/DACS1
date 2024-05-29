package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;


public class LogInAdmin extends JFrame {
	
	private JLabel jLabel_image;
	private JPanel jPanel_image;
	private JLabel jLabel_emailAdmin;
	private JTextField jTextField_emailAdmin;
	private JLabel jLabel_password;
	private JPasswordField jPasswordField;
	private JCheckBox showPass;
	private JLabel jLabel;
	private JButton jButton_logIn;
	private JLabel jLabel_question;
	private JButton jButton_logUp;

	public LogInAdmin() {
		this.setTitle("Log In");
		this.setSize(500, 220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font f = new Font("Arial", Font.BOLD, 15);
		jLabel_image = new JLabel();
		jLabel_image.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(LogInAdmin.class.getResource("User2.png"))));

		jPanel_image = new JPanel();
		jPanel_image.add(jLabel_image);

		jLabel_emailAdmin = new JLabel("Email");
		jLabel_emailAdmin.setFont(f);
		jTextField_emailAdmin = new JTextField(10);
		jTextField_emailAdmin.setFont(f);
		jLabel_password = new JLabel("Password");
		jLabel_password.setFont(f);
		jPasswordField = new JPasswordField(10);
		jPasswordField.setFont(f);
		showPass = new JCheckBox("Show password");
		showPass.setFont(f);
		jLabel = new JLabel("");

		// Transfer password to '*':
		showPass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPass.isSelected()) {
					jPasswordField.setEchoChar((char) 0);
				} else {
					jPasswordField.setEchoChar('*');
				}
			}
		});

		JPanel jPanel_input = new JPanel();
		jPanel_input.setLayout(new GridLayout(3, 2, 5, 5));
		jPanel_input.add(jLabel_emailAdmin);
		jPanel_input.add(jTextField_emailAdmin);
		jPanel_input.add(jLabel_password);
		jPanel_input.add(jPasswordField);
		jPanel_input.add(showPass);
		jPanel_input.add(jLabel);

		jButton_logIn = new JButton("Log in");
		jButton_logIn.setFont(f);
		jButton_logIn.setForeground(Color.black);
		jButton_logIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Log in")) {
					String email = jTextField_emailAdmin.getText();
					char[] passwordChars = jPasswordField.getPassword();
					String password = new String(passwordChars);
				//	String encryptPass = encryptPassword(password);

					// Send data to Server to check:
					try {
						Socket socket = new Socket("192.168.1.7", 7000);
						//send request to server:
						PrintWriter pr = new PrintWriter(socket.getOutputStream());
						pr.println("Log in");
						
						//send information:
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(email);
						oos.writeObject(password);

						// Receiving respond from server
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String message_from_server;
						message_from_server = br.readLine();
						System.out.println("Server: " + message_from_server);
						if (message_from_server.equals("Email is NOT EXIST!")
								|| message_from_server.equals("password is not valid")) {
							JOptionPane.showMessageDialog(rootPane, "Email or password is wrong!", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (message_from_server.equals("password is valid")) {
							JOptionPane.showMessageDialog(rootPane, "Log in successfully!");
							new HomePageView();
						}
					} catch (IOException ioe) {
						System.out.println("Couldn't connect to server");
						ioe.printStackTrace();
					}
				}
			}
		});

		JPanel jPanel_button = new JPanel();
		jPanel_button.add(jButton_logIn);

		JPanel jPanel_logIn = new JPanel();
		jPanel_image.setLayout(new FlowLayout());
		jPanel_logIn.add(jPanel_image);
		jPanel_logIn.add(jPanel_input);

		JPanel jPanel_account = new JPanel();
		jPanel_account.setLayout(new BorderLayout());
		jPanel_account.add(jPanel_logIn, BorderLayout.CENTER);
		jPanel_account.add(jPanel_button, BorderLayout.SOUTH);

		jLabel_question = new JLabel("You haven't account?", jLabel_question.CENTER);
		jLabel_question.setFont(new Font("Arial", Font.ITALIC, 12));
		jLabel_question.setForeground(Color.blue);

		jButton_logUp = new JButton("Log up");
		jButton_logUp.setFont(new Font("Arial", Font.BOLD, 12));
		jButton_logUp.setForeground(Color.blue);
		jButton_logUp.setBackground(Color.white);
		jButton_logUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LogUpAdmin();
			}
		});

		JPanel jPanel_logUp = new JPanel();
		jPanel_logUp.setLayout(new FlowLayout());
		jPanel_logUp.add(jLabel_question);
		jPanel_logUp.add(jButton_logUp);

		this.setLayout(new BorderLayout());
		this.add(jPanel_account, BorderLayout.CENTER);
		this.add(jPanel_logUp, BorderLayout.SOUTH);
		this.setVisible(true);
	}

//	public String encryptPassword(String pass) {
////		try {
////			String salf = "@killnet";
////			pass = pass + salf;
////			MessageDigest md = MessageDigest.getInstance("MD5");
////			byte[] encode = md.digest(pass.getBytes());
////			BigInteger number = new BigInteger(1, encode);
////			String hashText = number.toString(15);
////			return hashText;
////		} catch (NoSuchAlgorithmException e) {
////			throw new RuntimeException(e);
////		}
//
//		return BCrypt.hashpw(pass, BCrypt.gensalt());
//	}
}
