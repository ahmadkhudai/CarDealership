package Model;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String manufacturer;
    private String vehicleName;
    private int yearMade;
    private String color;


    //Sedan         1
    //SUV           2
    //Pickup        3
    //Sports        4
    private String type = "1";
    private boolean isAuto = false;
    private boolean isNew = true;
    private int price;


    public Vehicle(String manufacturer, String vehicleName, int yearMade, String color, String type, int price) {
        this(manufacturer, vehicleName, yearMade, color, type, true, price, true);
    }

    public Vehicle(String manufacturer, String vehicleName, int yearMade, String color, String type, boolean isAuto, int price, boolean isNew) {
        this.manufacturer = manufacturer;
        this.vehicleName = vehicleName;
        this.yearMade = yearMade;
//        this.horsePower = horsePower;
        this.color = color;
        this.type = type;
        this.isAuto = isAuto;
        this.price = price;
        this.isNew = isNew;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

//    public int getHorsePower() {
//        return horsePower;
//    }

//    public void setHorsePower(int horsePower) {
//        this.horsePower = horsePower;
//    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isNew() {
        return isNew;
    }


//    public Vehicle(String manufacturer, String vehicleName, int yearMade,
//                   String color, String type, boolean isAuto, int price,
//                   boolean isNew) {

        public void displayToConsole() {
            System.out.println("-------------------------\n" +
                    "Manufacturer: " + manufacturer +
                    "\nModel: " + vehicleName +
                    "\nProduction: " + yearMade +
                    "\nColor: " + color +
                    "\nTransmission: " + (isAuto ? "Automatic" : "Manual") +
                    "\nPrice: " + price +
                    "\nCondition: " + (isNew ? "New" : "Used"));
        }

        public String getKeyWords(){
            return manufacturer +
                    "," + vehicleName +
                    "," + yearMade +
                    "," + color +
                    "," + (isAuto ? "Automatic" : "Manual") +
                    "," + price +
                    "," + (isNew ? "New" : "Used");
        }
    }

