package com.hospital.app;

import com.hospital.Impl.OperationImpl;
import com.hospital.helpers.ConsoleForeground;
import com.hospital.models.*;
import static com.hospital.helpers.ConsoleHelper.*;

public class Main {
    public static void main(String[] args)
    {
        Hospital M5=Hospital.Deserialize("MohammedV","SAFI");
        OperationImpl operations=OperationImpl.Deserialize(M5.getName());

        Operation.setRefs_counter(M5.getRefOperations());

        int choix=-1;
        do{
            Print("");
            Print("---------- Menu principal ----------", ConsoleForeground.PURPLE);
            Print("1. Ajouter docteur.");
            Print("2. Ajouter infermier(e).");
            Print("3. Ajouter salle.");
            Print("4. Ajouter patient.");
            Print("5. Ajouter opération.");
            Print("6. Valider une operation.");
            Print("7. Afficher les transactions.");
            Print("8. Afficher liste des operations succes.");
            Print("9. Afficher liste des operations a discute dans le tribunal.");
            Print("10. Quitter.");

            Print("");
            choix=ReadInt("selectionner un option de la liste : ");
            switch (choix){
                case 1:
                    Doctor d=Doctor.newDoctor();
                    M5.getDoctors().add(d);
                    M5.Serialize();
                    break;
                case 2:
                    Nurse n=Nurse.newNurse();
                    M5.getNurses().add(n);
                    M5.Serialize();
                    break;
                case 3:
                    Room r=Room.newRoom();
                    M5.getRooms().add(r);
                    M5.Serialize();
                    break;
                case 4:
                    Patient p=Patient.newPatient();
                    M5.getPatients().add(p);
                    M5.Serialize();
                    break;
                case 5:
                    Operation op=Operation.newOperation(M5);
                    operations.addOperation(op);
                    M5.setRefOperations(Operation.getRefs_counter());
                    M5.Serialize();
                    operations.Serialize(M5.getName());
                    break;
                case 6:
                    operations.validateOperations();
                    operations.Serialize(M5.getName());
                    break;
                case 7:
                    Print("");
                    Print("La liste des transaction : ",ConsoleForeground.PURPLE);
                    for(int i=0;i<operations.getTransactions().size();i++){
                        Print((i+1)+" - "+ operations.getTransactions().get(i)+".",ConsoleForeground.PURPLE);
                    }

                    break;

                case 8:
                    Print("");
                    Print("La liste des operations succes : ",ConsoleForeground.PURPLE);
                    for(int i=0;i<operations.getSuccefulOperations().size();i++){
                        Print((i+1)+" - "+ operations.getSuccefulOperations().get(i)+".",ConsoleForeground.PURPLE);
                    }

                    break;


                case 9:
                    Print("");
                    Print("La liste des operations a discute dans le tribunal : ",ConsoleForeground.PURPLE);
                    for(int i=0;i<operations.getFailedOperations().size();i++){
                        Print((i+1)+" - "+ operations.getFailedOperations().get(i)+".",ConsoleForeground.PURPLE);
                    }

                    break;


            }
        }while(choix != 10);

    }
}
