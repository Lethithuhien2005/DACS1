package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.rooms;
import Utils.HibernateUtils;

public class RoomIOStream extends Thread{
	
	public RoomIOStream() {
		
	}
	
	@Override
	public void run() {
		writeTXT(getRoomDataFromMySQl());
	}

	private static Vector<rooms> getRoomDataFromMySQl() {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query<rooms> query = session.createQuery("From rooms", rooms.class);
			List<rooms> roomList = query.list();
			Vector<rooms> rl = new Vector<>(roomList);
			return rl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeTXT(Vector<rooms> list) {
		String pathFile = "D:\\RoomList.txt";
		try {
			FileWriter f = new FileWriter(pathFile);
			PrintWriter	pw = new PrintWriter(f);
			Enumeration vEnum = list.elements();
			while (vEnum.hasMoreElements()) {
				rooms r = (rooms)vEnum.nextElement();
				String toString = "Room's ID: " + r.getID_room() + "\n"
								+ "Bed's number: " + r.getNumber_bed()+ "\n"
								+ "Type of room: " + r.getType_room()+ "\n"
								+ "--------------------------------------------" + "\n";
				pw.println(toString);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
