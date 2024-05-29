package File;

import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.departments;
import Entities.rooms;
import Utils.HibernateUtils;

public class DepartmentWriteXML extends Thread {
	static List<departments> departmentList;
	
	public DepartmentWriteXML() {
		
	}

	@Override
	public void run() {
		departmentList = getDepartmentDataFromMySQl();
		writeXML(departmentList);
	}
	
	private static List<departments> getDepartmentDataFromMySQl() {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query<departments> query = session.createQuery("From departments", departments.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeXML(List<departments> list) {
		String pathFile = "D:\\DepartmentListXML.xml";
		StringBuilder builder = new StringBuilder();
		for (departments dep : departmentList) {
			builder.append(dep.writeDepartmentDownXML());
		}
		String body = builder.toString();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<departmentlist>\n" + body + "</departmentlist>";
		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] data = xml.getBytes();
			fos.write(data);
			fos.flush();
			System.out.println("Deparments added to XML file successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
