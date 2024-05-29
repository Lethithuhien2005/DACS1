package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class rooms {
	@Id
	@Column(name = "IdRoom", length = 10, nullable = false)
	private String ID_room;
	@Column(name = "NumOfBed", nullable = false)
	private int number_bed;
	@Column(name = "TypeOfRoom", length = 20, nullable = false)
	private String type_room;

	public rooms() {

	}

	public rooms(String iD_room, int number_bed, String type_room) {
		ID_room = iD_room;
		this.number_bed = number_bed;
		this.type_room = type_room;
	}

	public String getID_room() {
		return ID_room;
	}

	public void setID_room(String iD_room) {
		ID_room = iD_room;
	}

	public int getNumber_bed() {
		return number_bed;
	}

	public void setNumber_bed(int number_bed) {
		this.number_bed = number_bed;
	}

	public String getType_room() {
		return type_room;
	}

	public void setType_room(String type_room) {
		this.type_room = type_room;
	}
	
	public String writeRoomDownXML() {
		return 	"   <room>\n"
				+ "	<IdRoom> " + this.getID_room() + " </IdRoom>\n"
				+ "	<NumOfBed> " + this.getNumber_bed() + " </NumOfBed>\n"			
				+ "	<TypeOfRoom> " + this.getType_room() + " </TypeOfRoom>\n"
				+ "	</room>\n";
	}
}
