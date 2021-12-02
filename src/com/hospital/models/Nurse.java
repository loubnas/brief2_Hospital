package com.hospital.models;

import com.hospital.helpers.ConsoleForeground;
import static com.hospital.helpers.ConsoleHelper.*;

public class Nurse extends Person{

    private TimeSlot shiftSlot;
    private double salary;

    public Nurse(Person person,TimeSlot shiftSlot,double salary) {
        super(person);
        this.shiftSlot = shiftSlot;
        this.salary = salary;
    }

    // Getters & Setters :

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
        return "Nurse [shiftSlot=" + shiftSlot + ", salary=" + salary + "]";
    }

    // Ajouter une nouvelle infermière : -----------

    public static Nurse newNurse(){
        Print("");
        Print("------- Ajout d'un(e) infermier(e) -------", ConsoleForeground.PURPLE);

        String firstName=ReadString("Donner le nom : ");
        String lastName=ReadString("Donner le prenom : ");
        String phone=ReadString("Donner le numero de tel : ");
        String address=ReadString("Donner l'adress : ");

        Person nursePerson=new Person(firstName,lastName,phone,address);

        int startTime=ReadInt("Donner l'heure de debut : ");
        int endTime=ReadInt("Donner  l'heure de fin : ");

        TimeSlot nurseShiftSlot=new TimeSlot(startTime,endTime);

        double salary=ReadDouble("Donner le salaire : ");

        return new Nurse(nursePerson,nurseShiftSlot,salary);

    }
}
