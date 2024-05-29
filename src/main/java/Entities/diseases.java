package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class diseases {
	@Id
	@Column(name = "IdDisease", length = 10, nullable = false)
	private String id_disease;
	@Column(name = "NameDisease", length = 50, nullable = false)
	private String name_disease;

	public diseases() {

	}

	public diseases(String id_disease, String name_disease) {
		this.id_disease = id_disease;
		this.name_disease = name_disease;
	}

	public String getId_disease() {
		return id_disease;
	}

	public void setId_disease(String id_disease) {
		this.id_disease = id_disease;
	}

	public String getName_disease() {
		return name_disease;
	}

	public void setName_disease(String name_disease) {
		this.name_disease = name_disease;
	}
	public String writeDiseaseDownXML() {
		return 	"   <disease>\n"
				+ "	<IdDisease> " + this.getId_disease() + " </IdDisease>\n"
				+ "	<NameDisease> " + this.getName_disease() + " </NameDisease>\n"																	
				+ "	</disease>\n";
	}
}
