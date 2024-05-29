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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.departments;
import Utils.HibernateUtils;

public class DepartmentView extends JFrame {
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	private JButton jButton_back;
	private DefaultTableModel model_department;
	private Session session;
	private Transaction ts;

	public DepartmentView() {
		this.setTitle("List department");
		this.setSize(300, 170);
		this.setLocation(334, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(DoctorView.class.getResource("Department.png")));

		model_department = new DefaultTableModel();
		JTable jTable_department = new JTable(model_department);
		model_department.addColumn("IdDepartment");
		model_department.addColumn("NameDep");
		loadDataToModel_dep(model_department);
		
	//	JScrollPane jScrollPane = new JScrollPane(jTable_department, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		
		JPanel jPanel_button = new JPanel();
		jPanel_button.setLayout(new FlowLayout());
		jPanel_button.add(jButton_back);
		
		this.setLayout(new BorderLayout());
		this.add(jTable_department, BorderLayout.CENTER);
		this.add(jPanel_button, BorderLayout.SOUTH);

		this.setVisible(true);
	}
	
	public void loadDataToModel_dep(DefaultTableModel model) {
		try {
			session = factory.openSession();
			ts = session.beginTransaction();
			List<departments> dep = session.createQuery("From departments").list();
			for (departments department : dep) {
				model.addRow(new Object[] {
						department.getId_dep(),
						department.getName_dep()
				});
			}
			ts.commit();
		} catch (HibernateException e) {
			if (ts != null) ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
