package File;

import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.rooms;
import Utils.HibernateUtils;

public class RoomWriteXML extends Thread {
	static List<rooms> roomList;
	
	public RoomWriteXML() {
		
	}
	
	
	@Override
	public void run() {
		roomList = getRoomDataFromMySQl();
		writeXML(roomList);
	}


	private static List<rooms> getRoomDataFromMySQl() {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query<rooms> query = session.createQuery("From rooms", rooms.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeXML(List<rooms> list) {
		String pathFile = "D:\\RoomListXMl.xml";
		StringBuilder builder = new StringBuilder();
		for (rooms r : list) {
			builder.append(r.writeRoomDownXML());
		}
		String body = builder.toString();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<roomlist>\n" + body + "</roomlist>";
		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] data = xml.getBytes();
			fos.write(data);
			fos.flush();
			System.out.println("Rooms added to XML file successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 }
