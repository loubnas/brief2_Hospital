package com.hospital.models;

import com.hospital.helpers.ConsoleForeground;
import java.io.Serializable;
import static com.hospital.helpers.ConsoleHelper.*;

public class Room  implements Serializable {

	private int number;
	private int stage;
	
	public Room(int number,int stage) {
		this.number = number;
		this.stage = stage;
	}

	// Getters & Setters :
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", stage=" + stage + "]";
	}


	// Ajouter une nouvelle Salle : --------
	public static   Room newRoom(){
		Print("");
		Print("------- Ajout d'une salle -------", ConsoleForeground.PURPLE);

		int stage=ReadInt("Donner l'étage : ");
		int number=ReadInt("Donner le numéro : ");

		return new Room(number,stage);

	}
}
