package SaveToMySQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.admins;
import Utils.HibernateUtils;

public class AdminConnect {

	static final SessionFactory factory = HibernateUtils.getSessionFactory();
	
	public void updateAdmin(admins a) {
		try {
			Session session = factory.openSession();
			Transaction trs = session.beginTransaction();
			a.getId(); 
			a.getAdminName();
			a.getPassword();
			a.getDateOfBirthAdmin();
			a.getEmail();
			a.getGender();
			a.getPhone();
			session.save(a);
			trs.commit();
			System.out.println("Add SUCCESSFULLY!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Add FAILED!");
		}
	}	
}
