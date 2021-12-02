package com.hospital.enumerations;

import com.hospital.helpers.ConsoleForeground;
import java.io.Serializable;
import static com.hospital.helpers.ConsoleHelper.Print;
import static com.hospital.helpers.ConsoleHelper.ReadInt;

public enum OperationStatus  implements Serializable {
    SUCCESS,
    FAILED,
    NONE;

    public static OperationStatus selectOperationStatus() {
        int choix=-1;
        do {
            Print("");
            Print("------- selectionner un status d'opération -------", ConsoleForeground.PURPLE);
            Print("1 - Succées.");
            Print("2 - Echec.");
            choix = ReadInt("choisir un status d'operation: ");

        }while(choix<=0 || choix>2 );

        switch (choix){
            case 1: return OperationStatus.SUCCESS;
            case 2: return OperationStatus.FAILED;
        }
        return null;
    }


}
