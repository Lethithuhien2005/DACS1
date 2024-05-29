package Test;

import javax.swing.UIManager;

import File.DepartmentIOStream;
import File.DepartmentWriteXML;
import File.DiseaseIOStream;
import File.DiseaseWriteXML;
import File.DoctorIOStream;
import File.DoctorWriteXML;
import File.RoomIOStream;
import File.RoomWriteXML;
import Interfaces.WelcomePage;

public class RunProgram {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Thread welcome = new Thread(new WelcomePage());
			Thread diseaseIOStream = new DiseaseIOStream();
			Thread diseaseWriteXML = new DiseaseWriteXML();
			Thread doctorWriteXML = new DoctorWriteXML();
			Thread doctorIOStream = new DoctorIOStream();
			Thread departmentIOstream = new DepartmentIOStream();
			Thread departmentWriteXML = new DepartmentWriteXML();
			Thread roomIOStream = new RoomIOStream();
			Thread roomWriteXML = new RoomWriteXML();
			
			welcome.setPriority(Thread.MAX_PRIORITY);
			diseaseIOStream.start();
			diseaseWriteXML.start();
			doctorIOStream.start();
			doctorWriteXML.start();
			departmentIOstream.start();
			departmentWriteXML.start();
			roomIOStream.start();
			roomWriteXML.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
