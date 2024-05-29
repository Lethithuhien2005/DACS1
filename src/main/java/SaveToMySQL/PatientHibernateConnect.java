package SaveToMySQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.patients;
import Utils.HibernateUtils;

public class PatientHibernateConnect {
	static final SessionFactory factory = HibernateUtils.getSessionFactory();
	public void addPatientToDatabase(patients p) {
		try {
			Session session = factory.openSession();
			Transaction ts = session.beginTransaction();
				p.getID_patient();
				p.getName_patient();
				p.getGender();
				p.getDateOfBirth();
				p.getAddress();
				p.getPhone();
				p.getID_room();
				p.getNumber_bed();
				p.getId_disease();
				p.getID_doctor();
				p.getDay_in();
				p.getDay_out();
			session.save(p);
			ts.commit();
			System.out.println("Add SUCCESSFULLY a new patient!!!");
		} catch (Exception e) {
			System.out.println("Add FAILED a new patient!!!");
		}
	}
}
