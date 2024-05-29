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

import Entities.rooms;
import Utils.HibernateUtils;

public class RoomView extends JFrame {

	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	private JButton jButton_back;
	private Session session;
	private Transaction ts;
	private DefaultTableModel modelRoom;
	public RoomView() {
		this.setTitle("List room");
		this.setSize(350, 300);
		this.setLocation(334, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(RoomView.class.getResource("Room.png")));

		modelRoom = new DefaultTableModel();
		JTable jTable_room = new JTable(modelRoom);
		modelRoom.addColumn("IdRoom");
		modelRoom.addColumn("NumOfBed");
		modelRoom.addColumn("TypeOfRoom");
		loadDataToModel_Room(modelRoom);
		
		JScrollPane jScrollPane = new JScrollPane(jTable_room, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

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
	public void loadDataToModel_Room(DefaultTableModel model) {
		try {
			session = factory.openSession();
			ts = session.beginTransaction();
			List<rooms> r = session.createQuery("From rooms").list();
			for (rooms room : r) {
				model.addRow(new Object[] {
						room.getID_room(),
						room.getNumber_bed(),
						room.getType_room()
				});
			}
		} catch (HibernateException e) {
			if (ts != null) ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
