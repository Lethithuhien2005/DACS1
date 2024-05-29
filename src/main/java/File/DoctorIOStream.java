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
import Entities.doctors;
import Entities.patients;
import Entities.rooms;
import Utils.HibernateUtils;
public class DoctorIOStream extends Thread{
		
		public DoctorIOStream() {
			
		}
		
		@Override
		public void run() {
			writeTXT(getDoctorDataFromMySQl());
		}

		private static Vector<doctors> getDoctorDataFromMySQl() {
			try {
				Session session = HibernateUtils.getSessionFactory().openSession();
				Query<doctors> query = session.createQuery("From doctors", doctors.class);
				List<doctors> doctorList = query.list();
				Vector<doctors> docList = new Vector<>(doctorList);
				return docList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void writeTXT(Vector<doctors> list) {
			String pathFile = "D:\\DoctorList.txt";
			try {
				FileWriter f = new FileWriter(pathFile);
				PrintWriter	pw = new PrintWriter(f);
				Enumeration vEnum = list.elements();
				while (vEnum.hasMoreElements()) {
					doctors doc = (doctors)vEnum.nextElement();
					String toString = "ID: " + doc.getID_doctor() + "\n"
									+ "Name: " + doc.getName_doctor()+ "\n"
									+ "Gender: " + doc.getGender()+ "\n"
									+ "Date of birth: " + doc.getDate_of_birth()+ "\n"
									+ "Address: " + doc.getAddress()+ "\n"
									+ "Phone: " + doc.getPhone()+ "\n"
									+ "ID Department: " + doc.getID_department()+ "\n"
									+ "--------------------------------------------" + "\n";
					pw.println(toString);
					pw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
