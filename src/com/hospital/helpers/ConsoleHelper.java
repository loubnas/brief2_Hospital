package com.hospital.helpers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;

public class ConsoleHelper  implements Serializable {
    public static void Print(String Message){

        System.out.println(Message);
    }
    // foreground & backghround
    public static void Print(String Message,ConsoleForeground ForeColor,ConsoleBackground BackgroundColor){
        System.out.print(ForeColor.getValue());
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
        System.out.print(ConsoleBackground.RESET.getValue());
    }
    // foreground
    public static void Print(String Message,ConsoleForeground ForeColor){
        System.out.print(ForeColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());

    }
    //backgound
    public static void Print(String Message,ConsoleBackground BackgroundColor){
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
    }

    public static int ReadInt(String Message){
        boolean erreur=false;
        do {
            erreur=false;
            try {
                System.out.print(Message);
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (Exception ex) {
                Print("Valeur entrée non valide.",ConsoleForeground.RED);
                erreur=true;
            }
        }while (erreur);
        return -1;
    }
    public static double ReadDouble(String Message){
        boolean erreur=false;
        do {
            erreur=false;
            try {
                System.out.print(Message);
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (Exception ex) {
                Print("Valeur entrée non valide.",ConsoleForeground.RED);
                erreur=true;
            }
        }while (erreur);
        return -1.0;
    }

    public static String ReadString(String Message){
        System.out.print(Message);
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Calendar ReadCalendar(String Message){
        System.out.println(Message);

        Calendar date=Calendar.getInstance();
        int annee=ReadInt("Donner l'annee : ");
        int mois=ReadInt("Donner le mois : ");
        int jour=ReadInt("Donner le jour : ");
        int heure=ReadInt("Donner l'heure : ");
        int minutes=ReadInt("Donner les minutes : ");

        date.set(Calendar.YEAR,annee);
        date.set(Calendar.MONTH,(mois-1)); // -1 , Calender mounth start with 0.
        date.set(Calendar.DAY_OF_MONTH,jour);
        date.set(Calendar.HOUR,heure);
        date.set(Calendar.MINUTE,minutes);
        date.set(Calendar.SECOND,00);
        date.set(Calendar.MILLISECOND,00);

        return date;
    }

    public static String padLeft(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return sb.toString();
    }
    public static String padRight(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(inputString);
        while (sb.length() < length ) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
