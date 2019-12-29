package HelperClasses;

import Model.CarDealership;
import Model.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static HelperClasses.Helper.greenBackGround;
import static HelperClasses.Helper.redBackGround;

public class WriteSampleData {
    public static void main(String[] args) {



        CarDealership dealership = loadData();

//        dealership.printKeywords();
//        System.exit(0);

        // Vehicle(String manufacturer, String vehicleName, int yearMade, int yearAcquired, int horsePower,
        // String color, String type, int price) {
        // Vehicle(String manufacturer, String vehicleName, int yearMade, int yearAcquired,
        // String color, String type, boolean isAuto, int price, boolean isNew) {

        boolean autoOrNot[] = {true, false};
        boolean newOrNot[] = {true, false};


        String colors[] = {
                "Red", "Green", "Blue", "White", "Black", "Yellow"
        };
        String manufacturers[] = {
                "Toyota", "Honda", "Tesla", "Suzuki", "Lamborghini"
        };
        //, "KIA", "BMW", "Mercedes",

        String Lamborghini[] = {
                "Galardo.70000",
                "Murceilago.130000",
                "Aventador.90000",
                "Reventon.170000"
        };

        String Suzuki[] ={
            "Mehran.7000",
            "Swift.18000",
            "Baleno.20000"
        };


        String Toyota[] = {"4Runner.35310",
                            "86.26655",
                            "Avalon.35650",
                            "Camry.24095",
                            "C-HR.21145",
                            "Corolla.18700",
                            "Highlander.31830",
                            "Land Cruiser.85165"};

        String Honda[] = {
                        "City.9910",
                        "Amaze.5930",
                        "Civic.17930",
                        "WRV.8080",
                        "Jazz.7450",
                        "BRV.9520",
                        "Accord.43210",
                        "CR-V.28270"
        };

        String Tesla[] = {
                "Model X.90000",
                "Model S.100000",
                "Model 3.33000",
                "Model Y.44000"
        };


        Integer years[] = {
                2020, 2019, 2018, 2017, 2016, 2015, 2014
        };


        int acquiredYear = 2020;
        int yearMade = 2019;
        String color;
        String manufacturer = "";
        String vName = "";
        int price = -1;
                Random random = new Random();
        for(int i = 0; i<30; i++){
            yearMade = years[random.nextInt(years.length)];
            color = colors[random.nextInt(colors.length)];
            manufacturer = manufacturers[random.nextInt(manufacturers.length)];
            switch (manufacturer){
                case ("Toyota"):{
                    String car[] = Toyota[random.nextInt(Toyota.length)].split("\\.");
                    vName = car[0];
                    price = Integer.parseInt(car[1]);
                    break;
                }case ("Honda"):{
                    String car[] = Honda[random.nextInt(Honda.length)].split("\\.");
                    vName = car[0];
                    price = Integer.parseInt(car[1]);
                    break;
                }case ("Tesla"):{
                    String car[] = Tesla[random.nextInt(Tesla.length)].split("\\.");
                    vName = car[0];
                    price = Integer.parseInt(car[1]);
                    break;
                }case ("Suzuki"): {
                    String car[] = Suzuki[random.nextInt(Suzuki.length)].split("\\.");
                    vName = car[0];
                    price = Integer.parseInt(car[1]);
                    break;
                }case ("Lamborghini"): {
                    String car[] = Lamborghini[random.nextInt(Lamborghini.length)].split("\\.");
                    vName = car[0];
                    price = Integer.parseInt(car[1]);
                    break;
                }
            }
            boolean isAuto = autoOrNot[random.nextInt(autoOrNot.length)];
            boolean isNew = newOrNot[random.nextInt(newOrNot.length)];
            Vehicle vehicle = new Vehicle(manufacturer, vName, yearMade, color, "1",isAuto, price, isNew);

            dealership.addCar(vehicle, yearMade);
            System.out.println("jsdf: " + i);


//            Scanner scn = new Scanner(System.in);
//            while (true){
//                System.out.println("Enter keywords:");
//                String keywords = scn.nextLine();
//                ArrayList<Vehicle> vehicles = searchByKeywords(keywords, dealership.getVehicles());
//                for(Vehicle vehicle1: vehicles){
//                    System.out.println("FOUND FOR KEYWORD: " + keywords);
//                    vehicle1.displayToConsole();
//                }
//            }
            // Vehicle(String manufacturer, String vehicleName, int yearMade,
// String color, String type, boolean isAuto, int price, boolean isNew) {
        }

        dealership.createCustomer("Sample", "", "@","", 2000000);
        dealership.createCustomer("Ahmad Tufail", "fa18-bcs-005", "ahmad@outlook.com","", 2000000);
        dealership.createCustomer("Ahmad Tufail", "fa18bcs001", "ahmad@outlook.com","", 2000000);
        dealership.createCustomer("Ahmad Tufail", "fa18bcs002", "ahmad@outlook.com","", 2000000);
        dealership.createCustomer("Ahmad Tufail", "fa18bcs003", "ahmad@outlook.com","", 2000000);
        dealership.createCustomer("Ahmad Tufail", "fa18bcs005", "ahmad@outlook.com","", 2000000);
//        dealership.createCustomer("Ahmad Tufail", "a", "ahmad@outlook.com","", 2000000);


        dealership.displayAll();

        dealership.saveData();


    }
    private static CarDealership loadData() {
        CarDealership dealership = new CarDealership("The Car Dealership INC");
        //default database
        String database = "dealership.dat";

        //during the case of sample data, around 100 books are loaded and most of the diagnostics are not visible
        //this variable keeps track of all the messages printed while loading the data and prints them together
        String diagnosticString  = "";

        try {

            //Base file "state.dat" is used to keep track of the database file
            //If not found, the system will default back to 'dealership.dat'
            // In case that dealership.dat is not found, it will load sample data onto a newly created dealership.dat
            File base = new File("state.dat");
            if(base.exists()){
                Scanner reader = new Scanner(base);
                if(reader.hasNext()){
                    database = reader.next();
                    if(!(new File(database).exists())){
                        diagnosticString += "\nStorage file " + database + " NOT FOUND." + "\n";
                    }
                }
                reader.close();
            }else {
                diagnosticString += redBackGround("state.dat NOT FOUND. ") +
                        "\nThe system might read data from an old file" +
                        "\nSome of the variables will be set to their default values...\n";
            }


            File file = new File(database);



            //If the sample data was written successfully, the code bellow will load it into the system
            //Otherwise, the system will work without any data
            FileInputStream fi = new FileInputStream(database);
            ObjectInputStream inputStream = new ObjectInputStream((fi));
            dealership = (CarDealership) inputStream.readObject();
            inputStream.close();
            fi.close();
            diagnosticString += "\n"+greenBackGround("Saved Data Loaded Successfully!");
            diagnosticString +="\n______________________________";



        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            diagnosticString+= "\n"+redBackGround("Data not read.");
        }
        System.out.println(diagnosticString);
        return dealership;
    }

//    public ArrayList<Vehicle> searchByKeywords(String keywords){
//        return  searchByKeywords(keywords, vehicles);
//    }

    private static ArrayList<Vehicle> searchByKeywords(String keywords, ArrayList<Vehicle> vehicles) {
        String[] keywordList = keywords.toLowerCase().split("\\s+");
        //vehicleKeys and vehicles are in same order
//      ArrayList<String> vehicleKeys = getVehicleKeywords(vehicles);
        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            boolean foundAll = true;
            for (String keyword : keywordList) {
                if (!(vehicle.getKeyWords().toLowerCase().contains(keyword))){
                    foundAll = false;
                }
            }
            if(foundAll){
                foundVehicles.add(vehicle);
            }
        }

        return foundVehicles;
    }

}
