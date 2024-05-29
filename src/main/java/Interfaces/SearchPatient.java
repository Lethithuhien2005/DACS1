package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Entities.patients;
import Utils.HibernateUtils;

public class SearchPatient extends JFrame {

	private JLabel jLabel_img;
	public JTextField jTextField_id;
	private JLabel jLabel_name;
	public JTextField jTextField_name;
	private JButton jButton_search;
	private JTable jTable_patient;
	private ResultSet rs;
	private JScrollPane jsp;

	ArrayList<patients> arr = new ArrayList<patients>();
	private DefaultTableModel model;
	private JButton jButton_back;
	private DefaultTableModel model_search;

	private SessionFactory factory = HibernateUtils.getSessionFactory();
	private Session session;
	private Transaction ts;

	public SearchPatient() {
		this.setTitle("Search Patient");
		this.setSize(1200, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(SearchPatient.class.getResource("IconSearch.png")));

		Font font = new Font("Arial", Font.BOLD, 15);

		model_search = new DefaultTableModel();
		jTable_patient = new JTable(model_search);

		// Add name's columns to model:
		model_search.addColumn("IdPatient");
		model_search.addColumn("Name");
		model_search.addColumn("Gender");
		model_search.addColumn("Date_of_birth");
		model_search.addColumn("Address");
		model_search.addColumn("Phone");
		model_search.addColumn("IdRoom");
		model_search.addColumn("num_bed");
		model_search.addColumn("IdDisease");
		model_search.addColumn("IdDoctor");
		model_search.addColumn("DayIn");
		model_search.addColumn("DayOut");
		jsp = new JScrollPane(jTable_patient, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		loadDataToTable(model_search);
		
		jLabel_img = new JLabel();
		jLabel_img.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(SearchPatient.class.getResource("SearchPeople.png"))));

		JPanel jPanel_input = new JPanel();
		jPanel_input.setLayout(new GridLayout(1, 2, 10, 10));

		jLabel_name = new JLabel("Name patient", jLabel_name.RIGHT);
		jLabel_name.setFont(font);
		jTextField_name = new JTextField(8);
		jTextField_name.setFont(font);
		jTextField_name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterData();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterData();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterData();

			}
		});

		jPanel_input.add(jLabel_name);
		jPanel_input.add(jTextField_name);

		JPanel jPanel_search = new JPanel();
		jPanel_search.setLayout(new FlowLayout());
		jPanel_search.add(jLabel_img);
		jPanel_search.add(jPanel_input);

		jButton_search = new JButton("Search");
		jButton_search.setFont(font);
		jButton_search.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SearchPatient.class.getResource("Search.png"))));

		jButton_back = new JButton("Back");
		jButton_back.setFont(font);
		jButton_back.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SearchPatient.class.getResource("Back.png"))));
		jButton_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Back")) {
					new PatientManagement();
				}

			}
		});

		JPanel jPanel_button = new JPanel();
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_search);
		jPanel_button.add(jButton_back);

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(jPanel_search, BorderLayout.CENTER);
		jPanel.add(jPanel_button, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(jPanel, BorderLayout.NORTH);

		this.setVisible(true);
	}

	public void loadDataToTable(DefaultTableModel model) {
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
		} catch (HibernateException e) {
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
	}

	public void filterData() {
		String searchText = jTextField_name.getText();
		model_search.setRowCount(0); // Clear current rows
		// Create HQL query to retrieve patients
		Query<patients> query = session.createQuery("FROM patients WHERE name LIKE :name", patients.class);
		query.setParameter("name", "%" + searchText + "%");
		List<patients> patients = query.getResultList();
		// Add matching patients to table model
		for (patients patient : patients) {
			model_search.addRow(new Object[] { patient.getID_patient(), patient.getName_patient(), patient.getGender(),
					patient.getDateOfBirth(), patient.getAddress(), patient.getPhone(), patient.getID_room(),
					patient.getNumber_bed(), patient.getId_disease(), patient.getID_doctor(), patient.getDay_in(),
					patient.getDay_out() });
		}
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new SearchPatient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
