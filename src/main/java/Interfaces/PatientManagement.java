package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.zip.CheckedOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import File.PatientIOStream;
import Entities.patients;
import Utils.HibernateUtils;

public class PatientManagement extends JFrame {

	private static SessionFactory factory = HibernateUtils.getSessionFactory();

	private JPanel jPanel_button;
	private JButton jButton_add;
	private JButton jButton_update;
	private JButton jButton_delete;
	private JButton jButton_search;
	private JButton jButton_back;
	public JTable jTable_patient;
	private String id_patient, namePatient, gender, date_of_birth, address, phone, id_room, numbed, id_disease,
			id_doctor, dayIn, dayOut;
	int selectedRow;

	private Transaction ts;

	private Session session;

	private DefaultTableModel model_patient;

	private JButton jButton_saveTXT;
	Vector<patients> listPatient = new Vector<patients>();

	private String id;

	private String name;

	private String dateOfBirth;

	private String idRoom;

	private String numBed;

	private String idDisease;

	private String day_out;

	private String day_in;

	private String idDoctor;

	private String address_patient;

	private String numbed_patient;

	private String phone_patient;

	public PatientManagement() {
		this.setTitle("Medical file ");
		this.setSize(1250, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(
				Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Medicalfile.png")));

		Font font = new Font("Arial", Font.BOLD, 15);

		// Load data from database to jTable:
		model_patient = new DefaultTableModel();
		jTable_patient = new JTable(model_patient);
		model_patient.addColumn("IdPatient");
		model_patient.addColumn("Name");
		model_patient.addColumn("Gender");
		model_patient.addColumn("Date_of_birth");
		model_patient.addColumn("Address");
		model_patient.addColumn("Phone");
		model_patient.addColumn("IdRoom");
		model_patient.addColumn("num_bed");
		model_patient.addColumn("IdDisease");
		model_patient.addColumn("IdDoctor");
		model_patient.addColumn("DayIn");
		model_patient.addColumn("DayOut");
		JScrollPane jsp = new JScrollPane(jTable_patient, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		loadData(model_patient);

		// Generate button "Add" to add new patient:
		jPanel_button = new JPanel();
		jButton_add = new JButton("Add");
		jButton_add.setFont(font);
		jButton_add.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Add.png"))));
		jButton_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					new AddPatient();
			}
		});

		// Generate button "Delete" to delete patient:
		jButton_delete = new JButton("Delete");
		jButton_delete.setFont(font);
		jButton_delete.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Delete2.png"))));
		jButton_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					delete();
			}
		});

		// Generate button "Update" to modify information:
		jButton_update = new JButton("Update");
		jButton_update.setFont(font);
		jButton_update.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Update.png"))));
		jButton_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					display_to_update();
			}
		});

		// Generate button "Search" to search for patient:
		jButton_search = new JButton("Search");
		jButton_search.setFont(font);
		jButton_search.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Search.png"))));
		jButton_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					new SearchPatient();
			}
		});
		jButton_saveTXT = new JButton("Save file");
		jButton_saveTXT.setFont(font);
		jButton_saveTXT.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("SaveTXT.png"))));
		jButton_saveTXT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getData();
				new SaveFile(listPatient);
			}
		});
		jButton_back = new JButton("Back");
		jButton_back.setFont(font);
		jButton_back.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(PatientManagement.class.getResource("Back.png"))));
		jButton_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					new HomePageView();
			}

		});
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_add);
		jPanel_button.add(jButton_delete);
		jPanel_button.add(jButton_update);
		jPanel_button.add(jButton_search);
		jPanel_button.add(jButton_saveTXT);
		jPanel_button.add(jButton_back);

		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(jPanel_button, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	// Delete a patient:
	public void delete() {
		try {
			selectedRow = jTable_patient.getSelectedRow();
			String idToDelete = (String) jTable_patient.getValueAt(selectedRow, 0);
			Session session = factory.openSession();
			Transaction trs = session.beginTransaction();
			patients p = session.get(patients.class, idToDelete);
			session.delete(p);
			model_patient.removeRow(selectedRow);
			trs.commit();
		} catch (Exception ect) {
			if (ts != null) {
				ts.rollback();
			}
			ect.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Display information of patient on jTextField / jComboBox to modify:
	public void display_to_update() {
		// Get information:
		model_patient = (DefaultTableModel) jTable_patient.getModel();
		selectedRow = jTable_patient.getSelectedRow();
		id_patient = model_patient.getValueAt(selectedRow, 0) + "";
		namePatient = model_patient.getValueAt(selectedRow, 1) + "";
		gender = model_patient.getValueAt(selectedRow, 2) + "";
		date_of_birth = model_patient.getValueAt(selectedRow, 3) + "";
		address_patient = model_patient.getValueAt(selectedRow, 4) + "";
		phone_patient = model_patient.getValueAt(selectedRow, 5) + "";
		id_room = model_patient.getValueAt(selectedRow, 6) + "";
		numbed_patient = model_patient.getValueAt(selectedRow, 7) + "";
		id_disease = model_patient.getValueAt(selectedRow, 8) + "";
		id_doctor = model_patient.getValueAt(selectedRow, 9) + "";
		day_in = model_patient.getValueAt(selectedRow, 10) + "";
		day_out = model_patient.getValueAt(selectedRow, 11) + "";

		// display information:
		UpdatePatient updatePatient = new UpdatePatient();
		updatePatient.jTextField_id.setText(id_patient);
		updatePatient.jTextField_name.setText(namePatient);
		// updatePatient.buttonGroup_gender.
		if (gender.equals("male")) {
			updatePatient.jRadioButton_male.setSelected(true);
		} else if (gender.equals("female")) {
			updatePatient.jRadioButton_female.setSelected(true);
		}
		updatePatient.jTextField_dateOfBirth.setText(date_of_birth);
		updatePatient.jTextField_address.setText(address_patient);
		updatePatient.jTextField_phone.setText(phone_patient);
		updatePatient.jComboBox_idRoom.setSelectedItem(id_room);
		updatePatient.jComboBox_numBed.setSelectedItem(numbed_patient);
		updatePatient.jComboBox_idDisease.setSelectedItem(id_disease);
		updatePatient.jTextField_idDoctor.setText(id_doctor);
		updatePatient.jTextField_dayIn.setText(day_in);
		updatePatient.jTextField_dayOut.setText(day_out);

		delete();
		System.out.println("delete");

	}
	
	//Display data on interface from database
	public void loadData(DefaultTableModel model) {
		try {
			session = factory.openSession();
			ts = session.beginTransaction();
			List<patients> p = session.createQuery("From patients").list();
			for (patients patient : p) {
				model.addRow(new Object[] { patient.getID_patient(), patient.getName_patient(), patient.getGender(),
						patient.getDateOfBirth(), patient.getAddress(), patient.getPhone(), patient.getID_room(),
						patient.getNumber_bed(), patient.getId_disease(), patient.getID_doctor(), patient.getDay_in(),
						patient.getDay_out() });
			}
			ts.commit();
		} catch (HibernateException e) {
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//Get data from table for saving list to the file XML and IOStream
	public void getData() {
		int rowCount = model_patient.getRowCount();
		System.out.println("rowCount: " + rowCount);
		for (int i=0; i<rowCount; i++) {	
				id = model_patient.getValueAt(i, 0) + "";
				name = model_patient.getValueAt(i, 1) + "";
				gender = model_patient.getValueAt(i, 2) + "";
				dateOfBirth = model_patient.getValueAt(i, 3) + "";
				address = model_patient.getValueAt(i, 4) + "";
				phone = model_patient.getValueAt(i, 5) + "";
				idRoom = model_patient.getValueAt(i, 6) + "";
				numBed = model_patient.getValueAt(i, 7) + "";
				idDisease = model_patient.getValueAt(i, 8) + "";
				idDoctor = model_patient.getValueAt(i, 9) + "";
				dayIn = model_patient.getValueAt(i, 10) + "";
				dayOut = model_patient.getValueAt(i, 11) + "";
				patients pt = new patients(id, name, gender, dateOfBirth, address, phone, idRoom, numBed, idDisease, idDoctor, dayIn, dayOut);
				listPatient.add(pt);
		}
		System.out.println("number of patient: " + listPatient.size());
		new SaveFile(listPatient);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new PatientManagement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
