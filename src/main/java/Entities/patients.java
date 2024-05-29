package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class patients {
	@Id
	@Column(name = "IdPatient", length = 10, nullable = false)
	private String ID_patient;
	@Column(name = "Name", length = 45, nullable = false)
	private String name_patient;
	@Column(name = "Gender", length = 10, nullable = false)
	private String gender;
	@Column(name = "Date_of_birth", length = 20, nullable = false)
	private String dateOfBirth;
	@Column(name = "Address", length = 100, nullable = false)
	private String address;
	@Column(name = "Phone", length = 10, nullable = false)
	private String phone;
	@Column(name = "IdRoom", length = 10, nullable = false)
	private String ID_room;
	@Column(name = "num_bed", nullable = false)
	private String number_bed;
	@Column(name = "IdDisease", length = 10, nullable = false)
	private String id_disease;
	@Column(name = "IdDoctor", length = 10, nullable = false)
	private String ID_doctor;
	@Column(name = "DayIn", length = 20, nullable = false)
	private String day_in;
	@Column(name = "DayOut", length = 20, nullable = false)
	private String day_out;

	public patients() {

	}

	public patients(String iD_patient, String name_patient, String gender, String dateOfBirth, String address,
			String phone, String iD_room, String number_bed, String id_disease, String iD_doctor, String day_in,
			String day_out) {
		ID_patient = iD_patient;
		this.name_patient = name_patient;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phone = phone;
		ID_room = iD_room;
		this.number_bed = number_bed;
		this.id_disease = id_disease;
		ID_doctor = iD_doctor;
		this.day_in = day_in;
		this.day_out = day_out;
	}

	public String getID_patient() {
		return ID_patient;
	}

	public void setID_patient(String iD_patient) {
		ID_patient = iD_patient;
	}

	public String getName_patient() {
		return name_patient;
	}

	public void setName_patient(String name_patient) {
		this.name_patient = name_patient;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getID_room() {
		return ID_room;
	}

	public void setID_room(String iD_room) {
		ID_room = iD_room;
	}

	public String getNumber_bed() {
		return number_bed;
	}

	public void setNumber_bed(String number_bed) {
		this.number_bed = number_bed;
	}

	public String getId_disease() {
		return id_disease;
	}

	public void setId_disease(String id_disease) {
		this.id_disease = id_disease;
	}

	public String getID_doctor() {
		return ID_doctor;
	}

	public void setID_doctor(String iD_doctor) {
		ID_doctor = iD_doctor;
	}

	public String getDay_in() {
		return day_in;
	}

	public void setDay_in(String day_in) {
		this.day_in = day_in;
	}

	public String getDay_out() {
		return day_out;
	}

	public void setDay_out(String day_out) {
		this.day_out = day_out;
	}
	
	public String writePatientDownXML() {
		return 	"   <patient>\n"
				+ "	<IdPatient> " + this.getID_patient() + " </IdPatient>\n"
				+ "	<Name> " + this.getName_patient() + " </Name>\n"
				+ "	<Gender> " + this.getGender() + " </Gender>\n"
				+ "	<Date_of_birth> " + this.getDateOfBirth() + " </Date_of_birth>\n"
				+ "	<Address> " + this.getAddress() + " </Address>\n"
				+ "	<Phone> " + this.getPhone() + " </Phone>\n"
				+ "	<IdRoom> " + this.getID_room() + " </IdRoom>\n"				
				+ "	<num_bed> " + this.getNumber_bed() + " </num_bed>\n"
				+ "	<IdDisease> " + this.getId_disease() + " </IdDisease>\n"
				+ "	<IdDoctor> " + this.getID_doctor() + " </IdDoctor>\n"
				+ "	<DayIn> " + this.getDay_in() + " </DayIn>\n"
				+ "	<DayOut> " + this.getDay_out() + " </DayOut>\n"														
				+ "	</patient>\n";
	}
}
