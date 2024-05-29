package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.diseases;
import Entities.doctors;
import Entities.rooms;
import Utils.HibernateUtils;

public class DiseaseIOStream extends Thread {
	
	public DiseaseIOStream() {
		
	}
	
	@Override
	public void run() {
		writeTXT(getDiseaseDataFromMySQl());
	}

	private static Vector<diseases> getDiseaseDataFromMySQl() {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query<diseases> query = session.createQuery("From diseases", diseases.class);
			List<diseases> diseaseList = query.list();
			Vector<diseases> disList = new Vector<>(diseaseList);
			return disList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeTXT(Vector<diseases> list) {
		String pathFile = "D:\\DiseaseList.txt";
		try {
			FileWriter f = new FileWriter(pathFile);
			PrintWriter	pw = new PrintWriter(f);
			Enumeration vEnum = list.elements();
			while (vEnum.hasMoreElements()) {
				diseases dis = (diseases)vEnum.nextElement();
				String toString = "Disease's ID: " + dis.getId_disease() + "\n"
								+ "Disease's Name: " + dis.getName_disease()+ "\n"
								+ "--------------------------------------" + "\n";
				pw.println(toString);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
