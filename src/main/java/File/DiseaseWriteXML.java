package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.diseases;
import Entities.doctors;
import Utils.HibernateUtils;

public class DiseaseWriteXML extends Thread {
	static List<diseases> diseaseList;
	
	public DiseaseWriteXML() {
		
	}
	
	
	@Override
	public void run() {
		diseaseList = getDiseaseDataFromMySQL();
		writeXML(diseaseList);
	}


	private static List<diseases> getDiseaseDataFromMySQL() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Query<diseases> query = session.createQuery("FROM diseases", diseases.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeXML(List<diseases> list) {
		String pathFile = "D:\\DiseaseListXML.xml";
		StringBuilder builder = new StringBuilder();
		for (diseases dis : diseaseList) {
			builder.append(dis.writeDiseaseDownXML());
		}
		String body = builder.toString();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<diseaselist>\n" + body + "</diseaselist>";

		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] data = xml.getBytes();
			fos.write(data);
			fos.flush();
			System.out.println("Diseases added to XML file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
