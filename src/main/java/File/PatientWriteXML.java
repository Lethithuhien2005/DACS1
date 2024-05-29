package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.patients;
import Utils.HibernateUtils;

public class PatientWriteXML extends Thread {
	static List<patients> patientList;
	String fileName;
	public PatientWriteXML() {

	}
	
	public PatientWriteXML(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void run() {
		patientList = getPatientDataFromMySQL();
		if (patientList != null) {
			writeXML(fileName);
		}
	}

	// lấy dữ liệu từ mySQL
	public static List<patients> getPatientDataFromMySQL() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Query<patients> query = session.createQuery("FROM patients", patients.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeXML(String path) {
		String pathFile = path + ".xml";
		StringBuilder builder = new StringBuilder();
		for (patients pt : patientList) {
			builder.append(pt.writePatientDownXML());
		}
		String body = builder.toString();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<patientlist>\n" + body + "</patientlist>";

		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] data = xml.getBytes();
			fos.write(data);
			fos.flush();
			System.out.println("Patients added to XML file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}