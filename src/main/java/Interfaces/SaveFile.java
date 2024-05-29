package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import File.PatientIOStream;
import File.PatientWriteXML;
import Entities.patients;
import antlr.build.Tool;

public class SaveFile extends JFrame {
	private JLabel jLabel_nameFile;
	private JTextField jTextField_nameFile;
	private JButton jButton_save;
	private JButton jButton_back;
	private JLabel jLabel_image;
	PatientManagement pm;
	SaveFile sf;
	Vector<patients> listPt = new Vector<patients>();

	public SaveFile(Vector<patients> listPatient) {
		this.listPt = listPatient;

		this.setSize(450, 220);
		this.setTitle("Save file");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font = new Font("Arial", Font.BOLD, 15);

		jLabel_image = new JLabel();
		jLabel_image.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SaveFile.class.getResource("MyComputer.png"))));

		jLabel_nameFile = new JLabel("Path file:", jLabel_nameFile.RIGHT);
		jLabel_nameFile.setFont(font);
		jTextField_nameFile = new JTextField(8);
		jTextField_nameFile.setFont(font);

		JPanel jPanel_text = new JPanel();
		jPanel_text.setLayout(new GridLayout(1, 2, 10, 10));
		jPanel_text.add(jLabel_image);
		jPanel_text.add(jLabel_nameFile);
		jPanel_text.add(jTextField_nameFile);

		JPanel jPanel_file = new JPanel();
		jPanel_file.setLayout(new FlowLayout());
		jPanel_file.add(jLabel_image);
		jPanel_file.add(jPanel_text);

		jButton_save = new JButton("Save");
		jButton_save.setFont(font);
		jButton_save.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SaveFile.class.getResource("Save.png"))));
		jButton_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread patientIOStreamConnect = new PatientIOStream(listPt, jTextField_nameFile.getText());
				patientIOStreamConnect.start();
				Thread patientWriteXML = new PatientWriteXML(jTextField_nameFile.getText());
				patientWriteXML.start();
				JOptionPane.showMessageDialog(sf, "Successful saving!");

			}
		});

		jButton_back = new JButton("Back");
		jButton_back.setFont(font);
		jButton_save.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SaveFile.class.getResource("Back.png"))));
		jButton_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PatientManagement();

			}
		});

		JPanel jPanel_button = new JPanel();
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_save);
		jPanel_button.add(jButton_back);

		this.setLayout(new BorderLayout());
		this.add(jPanel_file, BorderLayout.NORTH);
		this.add(jPanel_button, BorderLayout.SOUTH);

		this.setVisible(true);

	}

//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			new SaveFile();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
