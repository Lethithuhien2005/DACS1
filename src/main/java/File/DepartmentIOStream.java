package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.departments;
import Entities.patients;
import Entities.rooms;
import Utils.HibernateUtils;

public class DepartmentIOStream extends Thread {
	
	public DepartmentIOStream() {
		
	}
	
	@Override
	public void run() {
		writeTXT(getDepartmentDataFromMySQl());
	}

	private static Vector<departments> getDepartmentDataFromMySQl() {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query<departments> query = session.createQuery("From departments", departments.class);
			System.out.println(query.list());
			List<departments> departmentList = query.list();
			Vector<departments> depList = new Vector<>(departmentList);
			return depList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeTXT(Vector<departments> list) {
		String pathFile = "D:\\DepartmentList.txt";
		try {
			FileWriter f = new FileWriter(pathFile);
			PrintWriter	pw = new PrintWriter(f);
			Enumeration vEnum = list.elements();
			while (vEnum.hasMoreElements()) {
				departments dep = (departments)vEnum.nextElement();
				String toString = "Department's ID: " + dep.getId_dep() + "\n"
								+ "Deparment's Name: " + dep.getName_dep()+ "\n"
								+ "--------------------------------------------" + "\n";
				pw.println(toString);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
