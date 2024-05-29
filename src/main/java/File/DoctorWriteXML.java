package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.doctors;
import Utils.HibernateUtils;

public class DoctorWriteXML extends Thread{
	static List<doctors> doctorList;
	public DoctorWriteXML(){
		
	}
	
	
	@Override
	public void run() {
		doctorList = getDoctorDataFromMySQL();
		writeXML(doctorList);
	}


	private static List<doctors> getDoctorDataFromMySQL() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Query<doctors> query = session.createQuery("FROM doctors", doctors.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeXML(List<doctors> list) {
		String pathFile = "D:\\DoctorListXML.xml";
		StringBuilder builder = new StringBuilder();
		for (doctors doc : list) {
			builder.append(doc.writeDoctorDownXML());
		}
		String body = builder.toString();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<doctorlist>\n" + body + "</doctorlist>";

		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] data = xml.getBytes();
			fos.write(data);
			fos.flush();
			System.out.println("Doctors added to XML file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
