package com.hospital.models;

import com.hospital.enumerations.OperationStatus;
import com.hospital.errors.ErrorConstants;
import com.hospital.helpers.ConsoleForeground;
import java.io.Serializable;
import java.util.*;
import static com.hospital.helpers.ConsoleHelper.*;


public class Operation  implements Serializable {

    private static int refs_counter=1;
    private int ref;
    private Hospital hospital;
    private Room room;
    private Doctor doctor;
    private Nurse nurse;
    private Patient patient;
    private double price;
    private OperationStatus status;
    private Calendar operationStartDate;
    private Calendar operationEndDate;

    public Operation(Hospital hospital,Room room,Patient patient,Doctor doctor,Nurse nurse,double price,Calendar operationStartDate){
        this.ref=Operation.refs_counter;
        Operation.refs_counter++;

        this.hospital=hospital;
        this.room=room;
        this.patient=patient;
        this.doctor=doctor;
        this.nurse=nurse;
        this.price=price;
        this.status=OperationStatus.NONE;
        this.operationStartDate=operationStartDate;
    }

    public  static  void setRefs_counter(int rc){
        Operation.refs_counter=rc;
    }

    public static int getRefs_counter() {
        return refs_counter;
    }

    // Getters & Setters :
    public int getRef() {
        return ref;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Room getRoom() {
        return room;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public Calendar getOperationStartDate() {
        return operationStartDate;
    }

    public Calendar getOperationEndDate() {
        return operationEndDate;
    }

    public void setOperationEndDate(Calendar operationEndDate) {
        this.operationEndDate = operationEndDate;
    }



    // Ajouter une nouvelle Operation ------------------------------

    public static Operation newOperation(Hospital hospital){
        Print("");
        Print("-------- Ajout d'une opération --------", ConsoleForeground.PURPLE);
        Doctor doctor= hospital.selectDoctor();
        Nurse nurse=hospital.selectNurse();
        Room room = hospital.selectRoom();
        Patient patient=hospital.selectPatient();

        Calendar date=Calendar.getInstance();
        date.add(Calendar.HOUR,24);
        Calendar startDate;
        do {
           startDate= ReadCalendar("\n----> Date de debut de l'operation : ");
           if(startDate.compareTo(date)<0){
               Print("La date saise ne respect pas la difference d'une journée entre paiement et l'opération");
           }
        }
        while(startDate.compareTo(date)<0);

        double price = ReadDouble("Donner le prix de l'opération : ");
        return new Operation(hospital, room, patient, doctor,nurse,price,startDate);
    }


    // Validation d'une operation : ---------------------------

    public static boolean validateOperation(Operation op){
        Calendar endDate=ReadCalendar(" \n----> Date de fin de l'opération : ");
        // cas erreur date operation :
        if(endDate.compareTo(op.operationStartDate)<=0){
            Print(ErrorConstants.ERR_VALIDATION_DATE, ConsoleForeground.RED);
            return false;
        }
        //
        op.setOperationEndDate(endDate);
        op.setStatus(OperationStatus.selectOperationStatus());

        // test de status et retour d'argent
        return true;

    }

    @Override
    public String toString() {
        return "OP Ref "+this.ref+" - ("+this.patient.getFirstname()+" "+this.patient.getLastname()+") ";
    }

    // Une op�ration sera dans un hopital, dans une salle sp�cifique, et g�r� par un m�decin X qui fait partie de l'hopital :)
	
	// Il faut v�rifier l'assurance du patient avant de passer l'op�ration
	
	// Ajouter une classe infirmi�re pour aider le m�decin dans son travail
	
	// l'assurance rembourse un pourcentage de 70% si c'est CNSS et 80% pour RAMED et 100% pour CNOPS 
	
	// chaque op�ration a un prix, le patient doit payer le montant et apr�s l'hopital va lui remboursser (le cas du CNSS et CNOPS) , si RAMED le patient va juste 
	// payer la diff�rence d�s le d�but (20%)
	
	// Proposition : Ajouter un attribut portefeuille � la classe Patient pour g�rer le solde du patient, et du coup il faut avoir aussi une classe transaction qui tra�e les 
	// actions de payments avec la date...
	
	// Essayer de structurer votre travail avec la cr�ation des interfaces qui vous permettez d'impl�menter les m�thodes n�cessaires.
	
	// Apr�s le payement d'une op�ration et si c'est pass� avec succ�s le programme est termin�, sinon il faut renvoyer le montant vers la portefeuille patient
	// et dans ce cas son dossier est en stand by pour le discuter au tribunal. (vous devez g�rer les status de chaque op�ration)
}