package com.hospital.models;

import com.hospital.helpers.ConsoleForeground;
import static com.hospital.helpers.ConsoleHelper.*;

public class Doctor extends Person{

	private String professionNumber;
	private TimeSlot shiftSlot;
	private double salary;
	
	public Doctor(Person person,String professionNumber,TimeSlot shiftSlot,double salary) {
		super(person);
		this.professionNumber = professionNumber;
		this.shiftSlot = shiftSlot;
		this.salary = salary;
	}

	// Getters & Setters :
	public String getProfessionNumber() {
		return professionNumber;
	}

	public void setProfessionNumber(String professionNumber) {
		this.professionNumber = professionNumber;
	}

	public TimeSlot getShiftSlot() {
		return shiftSlot;
	}

	public void setShiftSlot(TimeSlot shiftSlot) {
		this.shiftSlot = shiftSlot;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Doctor [professionNumber=" + professionNumber + ", shiftSlot=" + shiftSlot + ", salary=" + salary + "]";
	}


	// Ajouter un nouveau Docteur : --------------

	public static Doctor newDoctor(){
		Print("");
		Print("------- Ajout d'un docteur -------", ConsoleForeground.PURPLE);

		String firstName=ReadString("Donner le nom : ");
		String lastName=ReadString("Donner le prénom : ");
		String phone=ReadString("Donner le numéro de tel : ");
		String address=ReadString("Donner l'adresse : ");
		Person doctorPerson=new Person(firstName,lastName,phone,address);

		String professionNumber=ReadString("Donner le numero-profession : ");
		int startTime=ReadInt("Donner l'heure de debut : ");
		int endTime=ReadInt("Donner  l'heure de fin : ");
		TimeSlot doctorShiftSlot=new TimeSlot(startTime,endTime);

		double salary=ReadDouble("Donner le salaire : ");

		return new Doctor(doctorPerson,professionNumber,doctorShiftSlot,salary);

	}
}
