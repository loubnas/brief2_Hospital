package com.hospital.models;

import com.hospital.helpers.ConsoleForeground;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static com.hospital.helpers.ConsoleHelper.Print;
import static com.hospital.helpers.ConsoleHelper.ReadInt;

public class Hospital  implements Serializable {

	private  int  refOperations=1;
	private Long id;
	private String name;
	private String city;
	private List<Doctor> doctors;
	private List<Nurse> nurses;
	private List<Room> rooms;
	private List<Patient> patients;

	// Constructeur :
	public Hospital(String name,String city) {
		this.id = 1L + (long) (Math.random() * (1000L - 1L));
		this.name = name;
		this.city = city;
		this.doctors=new ArrayList<>();
		this.nurses=new ArrayList<>();
		this.rooms=new ArrayList<>();
		this.patients=new ArrayList<>();
	}


	public  int getRefOperations() {
		return refOperations;
	}

	public  void setRefOperations(int refOperations) {
		this.refOperations = refOperations;
	}

   // Getters & Setters :
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Patient> getPatients() {
		return patients;
	}


	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", city=" + city + ", doctors=" + doctors + ", rooms=" + rooms
				+ "]";
	}

	// Menu pour selectionne un docteur : ----------------------

	public Doctor selectDoctor(){
		int choix=-1;
		do {
			Print("");
			Print("------- selectionner un docteur -------", ConsoleForeground.PURPLE);
			for (int i = 0; i < this.doctors.size(); i++) {
				Print((i + 1) + " - Dr. " + this.doctors.get(i).getFirstname() + " " + this.doctors.get(i).getLastname() + ".");
			}
			 choix = ReadInt("choisir un docteur de la liste : ");

		}while(choix<=0 || choix>this.doctors.size() );

		return this.doctors.get((choix-1));
	}



	// Menu pour selectionne une infermière  : ----------------------

	public Nurse selectNurse(){
		int choix=-1;
		do {
			Print("");
			Print("------- selectionner un(e) infermier(e) -------", ConsoleForeground.PURPLE);
			for (int i = 0; i < this.nurses.size(); i++) {
				Print((i + 1) + " - Infermière. " + this.nurses.get(i).getFirstname() + " " + this.nurses.get(i).getLastname() + ".");
			}
			choix = ReadInt("choisir un(e) infermier(e) de la liste : ");
		}while(choix<=0 || choix>this.nurses.size() );

		return this.nurses.get((choix-1));
	}

	// Menu pour selectionne une salle  : ----------------------

	public Room selectRoom(){
		int choix=-1;
		do {
			Print("");
			Print("------- selectionner une salle -------", ConsoleForeground.PURPLE);
			for (int i = 0; i < this.rooms.size(); i++) {
				Print((i + 1) + " - Salle N° : " + this.rooms.get(i).getNumber()+ " , Etage : " + this.rooms.get(i).getStage() + "");
			}
			choix = ReadInt("choisir une salle de la liste : ");
		}while(choix<=0 || choix>this.rooms.size() );

		return this.rooms.get((choix-1));
	}


	// Menu pour selectionne un patient  : ----------------------

	public Patient selectPatient(){
		int choix=-1;
		do {
			Print("");
			Print("------- selectionner un patient -------", ConsoleForeground.PURPLE);
			for (int i = 0; i < this.patients.size(); i++) {
				Print((i + 1) + " - Patient : " + this.patients.get(i).getFirstname() + " " + this.patients.get(i).getLastname() +" / "+ "Portfeuille : ("+this.patients.get(i).getWallet()+"DH)");
			}
			choix = ReadInt("choisir un patient de la liste : ");
		}while(choix<=0 || choix>this.patients.size() );

		return this.patients.get((choix-1));
	}

	// Ecriture d'un objet dans un flu binaire :
	public Hospital Serialize() {
		try {
			FileOutputStream fout = new FileOutputStream(this.name+".data");
			ObjectOutputStream oos = new ObjectOutputStream(fout); // chiffrer l objet dans un flu binaire afin de l ecrire
			oos.writeObject(this);
			fout.close();
		}catch (Exception ex){

	if(!(ex instanceof FileNotFoundException)) {
		Print(ex.getMessage());
		Print("Err. Impossible d'enregistrer le fichier.", ConsoleForeground.RED);
	}}
		return this;
	}

	// La lecture d'un objet dans un flu binaire :
	public static Hospital Deserialize(String name,String city) {
		try {
			FileInputStream finp = new FileInputStream(name+".data");
			ObjectInputStream ois = new ObjectInputStream(finp);
			Hospital h= (Hospital) ois.readObject();
			finp.close();
			return h;
		}catch (Exception ex){
			if(!(ex instanceof FileNotFoundException)) {
				Print(ex.getMessage());
				Print("Err. Impossible de lire le fichier.", ConsoleForeground.RED);
			}
			return new Hospital(name,city);
		}
	}
}
