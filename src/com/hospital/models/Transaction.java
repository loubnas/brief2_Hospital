package com.hospital.models;

import com.hospital.enumerations.TransactionType;

import java.io.Serializable;
import java.util.Calendar;

public class Transaction implements Serializable {
    private Patient patient;
    private Calendar dateTransaction;
    private TransactionType transactionType;
    private double montant;

    public Transaction(Patient patient,TransactionType transactionType,double montant){
        this.patient=patient;
        this.dateTransaction=Calendar.getInstance();
        this.transactionType=transactionType;
        this.montant=montant;
    }

    public Patient getPatient() {
        return patient;
    }

    public Calendar getDateTransaction() {
        return dateTransaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getMontant() {
        return montant;
    }

    @Override
    public String toString() {
        return "Transaction -\n        PAT :"+this.patient.getFirstname()+" "+this.patient.getLastname() +"\n        TYPE:"+ this.transactionType+"\n        MONTANT:"+ this.montant+"\n        DATE:"+this.dateTransaction.getTime();
    }
}
