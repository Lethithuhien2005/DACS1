package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import Entities.patients;

public class PatientIOStream extends Thread {
	Vector<patients> list;
	FileWriter f;
	PrintWriter pw;
	String fileName;
	
	public PatientIOStream(Vector<patients> list, String fileName) {
		this.list = list;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		writeTXT(list, fileName);
	}


	public void writeTXT(Vector<patients> list, String path) {
		String pathFile = path + ".txt";
		try {
			f = new FileWriter(path);
			pw = new PrintWriter(f);
			Enumeration vEnum = list.elements();
			while (vEnum.hasMoreElements()) {
				patients pt = (patients)vEnum.nextElement();
				String toString = "ID: " + pt.getID_patient() + "\n"
								+ "Name: " + pt.getName_patient() + "\n"
								+ "Gender: " + pt.getGender() + "\n"
								+ "Date of birth: " + pt.getDateOfBirth() + "\n"
								+ "Address: " + pt.getAddress() + "\n" 
								+ "Phone: " + pt.getPhone() + "\n"
								+ "ID room: " + pt.getID_room() + "\n"
								+ "Number bed: " + pt.getNumber_bed() + "\n"
								+ "ID disease: " + pt.getId_disease() + "\n"
								+ "ID doctor: " + pt.getID_doctor() + "\n"
								+ "Day in: " + pt.getDay_in() + "\n"
								+ "Day out: " + pt.getDay_out() + "\n"
								+ "--------------------------------------------" + "\n";
				pw.println(toString);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
