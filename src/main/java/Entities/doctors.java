package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class doctors {
	@Id
	@Column(name = "IdDoctor", length = 10, nullable = false)
	private String ID_doctor;
	@Column(name = "Name", length = 50, nullable = false)
	private String name_doctor;
	@Column(name = "Gender", length = 10, nullable = false)
	private String gender;
	@Column(name = "Date_of_birth", length = 20, nullable = false)
	private String date_of_birth;
	@Column(name = "Address", length = 100, nullable = false)
	private String address;
	@Column(name = "Phone", length = 10, nullable = false)
	private String phone;
	@Column(name = "IdDepartment", length = 10, nullable = false)
	private String ID_department;

	public doctors() {

	}

	public doctors(String iD_doctor, String name_doctor, String gender, String date_of_birth, String address,
			String phone, String iD_department) {
		ID_doctor = iD_doctor;
		this.name_doctor = name_doctor;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.address = address;
		this.phone = phone;
		ID_department = iD_department;
	}

	public String getID_doctor() {
		return ID_doctor;
	}

	public void setID_doctor(String iD_doctor) {
		ID_doctor = iD_doctor;
	}

	public String getName_doctor() {
		return name_doctor;
	}

	public void setName_doctor(String name_doctor) {
		this.name_doctor = name_doctor;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getID_department() {
		return ID_department;
	}

	public void setID_department(String iD_department) {
		ID_department = iD_department;
	}
	
	public String writeDoctorDownXML() {
		return 	"   <doctor>\n"
				+ "	<IdDoctor> " + this.getID_doctor() + " </IdDoctor>\n"
				+ "	<Name> " + this.getName_doctor() + " </Name>\n"
				+ "	<Gender> " + this.getGender() + " </Gender>\n"
				+ "	<Date_of_birth> " + this.getDate_of_birth() + " </Date_of_birth>\n"
				+ "	<Address> " + this.getAddress() + " </Address>\n"
				+ "	<Phone> " + this.getPhone() + " </Phone>\n"
				+ "	<IdDepartment> " + this.getID_department() + " </IdDepartment>\n"																		
				+ "	</doctor>\n";
	}
}
