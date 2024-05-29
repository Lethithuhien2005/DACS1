package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.doctors;
import Utils.HibernateUtils;


public class DoctorView extends JFrame {

	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	private JButton jButton_back;
	private Session session;
	private Transaction ts;

	public DoctorView() {
		this.setTitle("List doctor");
		this.setSize(615, 300);
		this.setLocation(334, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(DoctorView.class.getResource("IconDoctor.png")));;
		
		DefaultTableModel model_doctor = new DefaultTableModel();
		JTable jTable = new JTable(model_doctor);
		JScrollPane jScrollPane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		model_doctor.addColumn("IdDoctor");
		model_doctor.addColumn("Name");
		model_doctor.addColumn("Gender");
		model_doctor.addColumn("Date_of_birth");
		model_doctor.addColumn("Address");
		model_doctor.addColumn("Phone");
		model_doctor.addColumn("IdDepartment");
		loadDataToModel_doctor(model_doctor);
		
		jButton_back = new JButton("Back");
		jButton_back.setFont(new Font("Arial", Font.BOLD, 15));
		jButton_back.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DoctorView.class.getResource("Back.png"))));
		jButton_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Back")) {
					new HomePageView();
				}
				
			}
		});
		
		JPanel jPanel_button = new JPanel()	;
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_back);
		
		this.setLayout(new BorderLayout());
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(jPanel_button, BorderLayout.SOUTH);
		
		this.setVisible(true);

	}
	public void loadDataToModel_doctor(DefaultTableModel model) {
		try {
			session = factory.openSession();
			ts = session.beginTransaction();
			List<doctors> d = session.createQuery("From doctors").list();
			for (doctors doctor : d) {
				model.addRow(new Object[] {
						doctor.getID_doctor(),
						doctor.getName_doctor(),
						doctor.getGender(),
						doctor.getDate_of_birth(),
						doctor.getAddress(),
						doctor.getPhone(),
						doctor.getID_department()
				});
			}
		} catch (HibernateException e) {
			if (ts != null ) ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
