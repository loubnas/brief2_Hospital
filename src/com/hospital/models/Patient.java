package com.hospital.models;

import java.util.Calendar;
import com.hospital.enumerations.InsuranceType;
import com.hospital.helpers.ConsoleForeground;
import static com.hospital.helpers.ConsoleHelper.*;

public class Patient extends Person{

	private Calendar hospitalEntryDate;
	private String affiliationNumber;
	private InsuranceType insuranceType;
	private double wallet; //portefeuille
	
	public Patient(Person person,Calendar hospitalEntryDate,String affiliationNumber,InsuranceType insuranceType,double wallet) {
		super(person);
		this.hospitalEntryDate = hospitalEntryDate;
		this.affiliationNumber = affiliationNumber;
		this.insuranceType = insuranceType;
		this.wallet=wallet;
	}

	// Getters & Setters :
	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public Calendar getHospitalEntryDate() {
		return hospitalEntryDate;
	}

	public void setHospitalEntryDate(Calendar hospitalEntryDate) {
		this.hospitalEntryDate = hospitalEntryDate;
	}

	public String getAffiliationNumber() {
		return affiliationNumber;
	}

	public void setAffiliationNumber(String affiliationNumber) {
		this.affiliationNumber = affiliationNumber;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public String toString() {
		return "Patient [hospitalEntryDate=" + hospitalEntryDate + ", affiliationNumber=" + affiliationNumber
				+ ", insuranceType=" + insuranceType + "]";
	}


	// Ajouter un nouveau patient : -----------------
	public static Patient newPatient() {
		Print("");
		Print("------- Ajout d'un patient -------", ConsoleForeground.PURPLE);

		String firstName=ReadString("Donner le nom : ");
		String lastName=ReadString("Donner le prenom : ");
		String phone=ReadString("Donner le numero de tel : ");
		String address=ReadString("Donner l'adress : ");
		Person patientPerson=new Person(firstName,lastName,phone,address);

		Calendar entryDate=Calendar.getInstance();

		String affiliateNumber=ReadString("Donner le numéro d'affilliation : ");

		InsuranceType insuranceType=InsuranceType.selectInsuranceType();

		double wallet=ReadDouble("Donner le montant de la portefeuille : ");

		return new Patient(patientPerson,entryDate,affiliateNumber,insuranceType,wallet);
	}

}
