package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class admins implements Serializable {
	@Id
	@Column(name="ID", length=10, nullable = false)
	private String id;
	@Column(name="Name", length=50, nullable = false)
	private String adminName;
	@Column(name="Password", length=100, nullable = false)
	private String password;
	@Column(name="DateOfBirth", length=20, nullable = false)
	private String dateOfBirthAdmin;
	@Column(name="Email", length=50, nullable = false)
	private String email;
	@Column(name="Gender", length=10, nullable = false)
	private String gender;
	@Column(name="Phone", length=10, nullable = false)
	private String phone;
	
	public admins() {

	}

	public admins(String id, String adminName, String password, String dateOfBirthAdmin, String email, String gender,
			String phone) {
		this.id = id;
		this.adminName = adminName;
		this.password = password;
		this.dateOfBirthAdmin = dateOfBirthAdmin;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirthAdmin() {
		return dateOfBirthAdmin;
	}

	public void setDateOfBirthAdmin(String dateOfBirthAdmin) {
		this.dateOfBirthAdmin = dateOfBirthAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
