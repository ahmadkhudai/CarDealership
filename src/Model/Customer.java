package Model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Customer implements Serializable {
    private String name;
    private String ID;
    private String contactInfo;

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setBoughtVehicles(ArrayList<Vehicle> boughtVehicles) {
        this.boughtVehicles = boughtVehicles;
    }

    private String password;
    private DateTime dateJoined;

    private int currentBalance;


//    //boughtVehicles<Object[DateTime, Vehicle]>: ArrayList
//    private ArrayList<Object[]> boughtVehicles = new ArrayList<>();
//
//    //soldVehicles<Object[DateTime, Vehicle]>: ArrayList
//    private ArrayList<Object[]> soldVehicles = new ArrayList<>();

    private ArrayList<Vehicle> boughtVehicles = new ArrayList<>();

    //transactionRecord<Object[DateTime, Amount[+/-], Vehicle]>: ArrayList
    private ArrayList<Object[]> transactionsRecord = new ArrayList<>();


    protected Customer(String name, String ID, String password, String contactInfo) {
        this(name, ID, contactInfo, password,new DateTime(),0);

    }

    protected Customer(String name, String ID, String password, DateTime dateJoined, String contactInfo) {
        this(name, ID, contactInfo, password, dateJoined,0);
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Vehicle> getBoughtVehicles() {
        return boughtVehicles;
    }


//    protected ArrayList<Object[]> getSoldVehicles() {
//        return soldVehicles;
//    }


    public ArrayList<Object[]> getTransactionsRecord() {
        return transactionsRecord;
    }

    public void setTransactionsRecord(ArrayList<Object[]> transactionsRecord) {
        this.transactionsRecord = transactionsRecord;
    }

    protected Customer(String name, String ID, String contactInfo, String password, DateTime dateJoined, int currentBalance) {
        this.name = name;
        this.ID = ID;
        this.contactInfo = contactInfo;
        this.password = password;
        this.dateJoined = dateJoined;
        this.currentBalance = currentBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public DateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(DateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    //if bought, the amount should be negative
    //if sold, the amount should be positive
    protected void buyVehicle(Vehicle vehicle){
            currentBalance = currentBalance-vehicle.getPrice();
            boughtVehicles.add(vehicle);
            transactionsRecord.add(new Object[]{new DateTime(), vehicle});
    }



}


