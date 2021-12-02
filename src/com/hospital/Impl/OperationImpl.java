package com.hospital.Impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.hospital.enumerations.InsuranceType;
import com.hospital.enumerations.TransactionType;
import com.hospital.errors.ErrorConstants;
import com.hospital.helpers.ConsoleForeground;
import com.hospital.interfaces.OperationInterface;
import com.hospital.models.Operation;
import com.hospital.models.Transaction;

import static com.hospital.helpers.ConsoleHelper.Print;
import static com.hospital.helpers.ConsoleHelper.ReadInt;

public class OperationImpl implements OperationInterface, Serializable {

    List<Operation> operations=new ArrayList<Operation>(); // list pour toute les operations
    List<Transaction> transactions=new ArrayList<Transaction>();



    List<Operation> failedOperations=new ArrayList<Operation>();
    List<Operation> succefulOperations=new ArrayList<Operation>();


    public List<Operation> getOperations() {
        return operations;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public List<Operation> getFailedOperations() {
        return failedOperations;
    }
    public List<Operation> getSuccefulOperations() {
        return succefulOperations;
    }

    @Override
    public Optional<Operation> getOperationByRef(int ref) {
        for (Operation o:operations) {
            if(o.getRef()==ref)
                return Optional.of(o);
        }
        return null;
    }

    // Ajouter une operation :----------------
    @Override
    public void addOperation(Operation o) {
        double prixOperationAPayee=o.getPrice();
        // Cas de RAMED retun difference 20% :
        if(o.getPatient().getInsuranceType()== InsuranceType.RAMED){
            prixOperationAPayee-=(prixOperationAPayee*InsuranceType.RAMED.getDiscount());
        }

        if(o.getPatient().getWallet()>=prixOperationAPayee){
            o.getPatient().setWallet(o.getPatient().getWallet()-prixOperationAPayee);
            this.operations.add(o);
            Print("-- Prix Payee par le patient : "+prixOperationAPayee,ConsoleForeground.YELLOW);
            transactions.add(new Transaction(o.getPatient(),TransactionType.OUT,prixOperationAPayee));
        }
        else {
            // Cas prix operation > le prix du portefeille du patient
            Print(ErrorConstants.ERR_VALIDATION, ConsoleForeground.RED);
        }
    }

    // Validation operations :

    public void validateOperations() {
        if(this.operations.size()>0) {
            Operation op = null;
            int choix = -1;
            do {
                Print("");
                Print("------- selectionner une opération -------", ConsoleForeground.PURPLE);
                for (int i = 0; i < this.operations.size(); i++) {
                    Print((i + 1) + " - " + this.operations.get(i) + "");
                }
                choix = ReadInt("choisir une opération de la liste : ");

            } while (choix <= 0 || choix > this.operations.size());

            op = this.operations.get((choix - 1));

            if (Operation.validateOperation(op)) {
                switch (op.getStatus()) {
                    case SUCCESS:
                        succefulOperations.add(op);
                        this.operations.remove(op);
                        //-----------------------
                        op.getPatient().setWallet(op.getPatient().getWallet()+(op.getPatient().getInsuranceType().getDiscount()* op.getPrice()));
                        // wallet=wallet + (op.getPatient().getInsuranceType().getDiscount()* op.getPrice())
                        Print("Opération realisée avec succées.",ConsoleForeground.GREEN);
                        Print("Le montant retourné par l'assurance est : "+ op.getPatient().getInsuranceType().getDiscount()* op.getPrice(),ConsoleForeground.GREEN);

                        transactions.add(new Transaction(op.getPatient(), TransactionType.IN,op.getPatient().getInsuranceType().getDiscount()* op.getPrice()));
                        break;
                    case FAILED:
                        failedOperations.add(op);
                        this.operations.remove(op);
                        op.getPatient().setWallet(op.getPatient().getWallet()+op.getPrice()); // retour d'argent

                        Print("Opération n'a pas reussie.",ConsoleForeground.RED);
                        Print("Le montant total de l'operation est retourné "+  op.getPrice(),ConsoleForeground.RED);
                        transactions.add(new Transaction(op.getPatient(), TransactionType.IN,op.getPrice()));

                        break;
                }
            }
        }else{
            Print("Aucune Opération en attente de validation.",ConsoleForeground.YELLOW);
        }
    }


    // Ecriture d'un objet dans un flu binaire :
    public OperationImpl Serialize(String hospitalName) {
        try {
            FileOutputStream fout = new FileOutputStream(hospitalName+"-Operations.data",false);
            ObjectOutputStream oos = new ObjectOutputStream(fout); // chiffrer l objet dans un flu binaire afin de l ecrire
            oos.writeObject(this);
            oos.close();
        }catch (Exception ex){
            if(!(ex instanceof FileNotFoundException)) {
                Print("Err. Impossible d'enregistrer le fichier operations.", ConsoleForeground.RED);
            }
        }
        return this;
    }


    // La lecture d'un objet dans un flu binaire :
    public static OperationImpl Deserialize(String hospitalName) {
        try {
            FileInputStream finp = new FileInputStream(hospitalName+"-Operations.data");
            ObjectInputStream ois = new ObjectInputStream(finp);
            OperationImpl oi= (OperationImpl) ois.readObject();
            ois.close();
            return  oi;
        }catch (Exception ex){
            if(!(ex instanceof FileNotFoundException)) {
                Print("Err. Impossible de lire le fichier operations.", ConsoleForeground.RED);
            }
            return new OperationImpl();
        }
    }
}
