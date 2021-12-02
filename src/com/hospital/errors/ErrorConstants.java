package com.hospital.errors;


import java.io.Serializable;

public final class ErrorConstants  implements Serializable {

    public static final String ERR_TYPE_NOT_FOUND = "Le type de l'assurance est introuvable";
    public static final String ERR_VALIDATION = "Le dossier est pas valide( le montant payé insuffisant)";
    public static final String ERR_VALIDATION_DATE = "Le dossier est pas valide - Date de fin de l'opération incorrect.";
    public static final String ERR_STATE = "Le statut du dossier est pas trouvé";

    // .... vous pouvez développer la dessus

    private ErrorConstants() {}

}
