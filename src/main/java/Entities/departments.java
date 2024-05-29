package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class departments {
	@Id
	@Column(name = "IdDepartment", length = 10, nullable = false)
	private String id_dep;
	@Column(name = "NameDep", length = 50, nullable = false)
	private String name_dep;

	public departments() {

	}

	public departments(String id_dep, String name_dep) {
		this.id_dep = id_dep;
		this.name_dep = name_dep;
	}

	public String getId_dep() {
		return id_dep;
	}

	public void setId_dep(String id_dep) {
		this.id_dep = id_dep;
	}

	public String getName_dep() {
		return name_dep;
	}

	public void setName_dep(String name_dep) {
		this.name_dep = name_dep;
	}
	
	public String writeDepartmentDownXML() {
		return 	"   <department>\n"
				+ "	<IdDepartment> " + this.getId_dep() + " </IdDepartment>\n"
				+ "	<NameDep> " + this.getName_dep() + " </NameDep>\n"			
				+ "	</department>\n";
	}
}
