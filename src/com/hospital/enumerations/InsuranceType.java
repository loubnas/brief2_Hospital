package com.hospital.enumerations;

import com.hospital.helpers.ConsoleForeground;
import java.io.Serializable;
import static com.hospital.helpers.ConsoleHelper.Print;
import static com.hospital.helpers.ConsoleHelper.ReadInt;

public enum InsuranceType  implements Serializable {

    CNSS("CNSS",0.7),
    RAMED("RAMED",0.8),
    CNOPS("CNOPS",1);

    private String value;
    private double discount; // %

    InsuranceType(String value,double discount) {
        this.value = value;
        this.discount=discount;
    }

    //  Menu pour Selectionner un type d'assurance :

    public static InsuranceType selectInsuranceType() {
        int choix=-1;
        do {
            Print("");
            Print("------- selectionner une assurance -------", ConsoleForeground.PURPLE);
            Print("1 - CNSS .");
            Print("2 - RAMED .");
            Print("3 - CNOPS .");
            choix = ReadInt("choisir un type d'assurance : ");

        }while(choix<=0 || choix>3 );

        switch (choix){
            case 1: return InsuranceType.CNSS;
            case 2: return InsuranceType.RAMED;
            case 3: return InsuranceType.CNOPS;
        }
        return null;
    }


    public String getValue() {
        return value;
    }
    public double getDiscount() { return discount;}

}
