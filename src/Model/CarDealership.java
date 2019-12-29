/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.joda.time.DateTime;


import java.io.*;
import java.util.*;

import static HelperClasses.Helper.*;

/**
 *
 * @author ahmad
 */
public class CarDealership implements Serializable {

   private String name;
   private String database = "dealership.dat";

//   private boolean isAdmin = false;

   private String adminPass = "ahmad";
   private String adminID = "ahmad";

   private ArrayList<Customer> customers = new ArrayList<>();


//   private HashMap<Integer, Vehicle> vehicles = new HashMap<>();
   private ArrayList<Vehicle> vehicles = new ArrayList<>();
   private ArrayList<Vehicle> suvs = new ArrayList<>();
   private ArrayList<Vehicle> sedans = new ArrayList<>();
   private ArrayList<Vehicle> pickups = new ArrayList<>();
   private ArrayList<Vehicle> sports = new ArrayList<>();

   private ArrayList<Object[]> approvals = new ArrayList<>();

   public CarDealership(String name) {
      this.name = name;
   }

//   Customer(String name, String ID, String password)
//
//   Customer(String name, String ID, String password, DateTime dateJoined)
//
//   Customer(String name, String ID, String password, DateTime dateJoined, int currentBalance)
//
//   Customer(String name, String ID, String password, DateTime dateJoined, int currentBalance)

   //todo: validation
   //String phoneNumber = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
   //public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
   //    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
   //
   //public static boolean validate(String emailStr) {
   //        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
   //        return matcher.find();
   //}

   public boolean createCustomer(String name, String id, String contactInfo, String password, int currentBalance){
      Customer customer = new Customer(name, id, contactInfo, password, new DateTime(), currentBalance);
      if(!customerExists(id)){
//         System.out.println(name + " added:" + password+"kf");
         customers.add(customer);

         return true;
      }
      System.out.println("NOT SAVED!");
      return false;
   }



// Vehicle(String manufacturer, String vehicleName, int yearMade, int yearAcquired, int horsePower,
// String color, String type, int price) {
//
// Vehicle(String manufacturer, String vehicleName, int yearMade, int yearAcquired, int horsePower,
// String color, String type, boolean isAuto, int price, boolean isNew) {



   public void addCar(Vehicle vehicle, int yearAcquired){
      vehicles.add(vehicle);
      sedans.add(vehicle);

//      return true;
   }
   public boolean addCar(String manufacturer, String vehicleName, int yearMade, int yearAcquired,
                         String color, String type, boolean isAuto, int price, boolean isNew){
      Vehicle vehicle = new Vehicle(manufacturer, vehicleName, yearMade, color, type,
              isAuto, price, isNew);

      vehicles.add(vehicle);

      switch (type){
         //Sedan         1
         //SUV           2
         //Pickup        3
         //Sports        4
         case ("1"):{
            sedans.add(vehicle);
            break;
         }
         case("2"):{
            suvs.add(vehicle);
            break;
         }
         case ("3"):{
            pickups.add(vehicle);
            break;
         }
         case ("4"):{
            sports.add(vehicle);
            break;
         }
         default:
            System.out.println(turnRed("BAD VEHICLE TYPE!"));
      }


      return true;
   }



   public ArrayList<Vehicle> getUsedVehicles(){
      ArrayList<Vehicle> used = new ArrayList<>();

      for(Vehicle vehicle: vehicles){
         if(!vehicle.isNew()){
            used.add(vehicle);
         }
      }
      return used;
   }


   public ArrayList<Vehicle> getNewVehicles(){
      ArrayList<Vehicle> newVehicles = new ArrayList<>();
      for(Vehicle vehicle: vehicles){
         if(!vehicle.isNew()){
            newVehicles.add(vehicle);
         }
      }
      return newVehicles;
   }



   public String getName(){
      return name;
   }


   private void setDatabase(String s) {
      this.database = s;
   }

   private String getDatabase() {
      return database;
   }

   public boolean customerExists(String id) {
      for(Customer customer: customers){
         if(customer.getID().equalsIgnoreCase(id)){
            return true;
         }
      }
      return false;
   }

   public Customer loginUser(String id, String password){
      for(Customer customer: customers){
         if((customer.getID().equalsIgnoreCase(id)&&customer.getPassword().equals(password))){
            return customer;
         }
      }
      return null;
   }




    public void displayAll() {

      int i = 0;
      for(Vehicle vehicle: vehicles){
         vehicle.displayToConsole();
         System.out.println("ksjflsj: " + i);
         i++;
      }
//       for (Object value : map.values()) {
//          // ...
//       }
    }



   // SEARCH
   public ArrayList<Vehicle> searchByKeywords(String keywords){
      return  searchByKeywords(keywords, vehicles);
   }

   private ArrayList<Vehicle> searchByKeywords(String keywords, ArrayList<Vehicle> vehicles) {
      String[] keywordList = keywords.toLowerCase().split("\\s+");
      //vehicleKeys and vehicles are in same order
      //ArrayList<String> vehicleKeys = getVehicleKeywords(vehicles);
      ArrayList<Vehicle> foundVehicles = new ArrayList<>();


      for (Vehicle vehicle : vehicles) {
         boolean foundAll = true;
         String[] keys = vehicle.getKeyWords().toLowerCase().split(",");
         for (String keyword : keywordList) {
            boolean contains = false;
            for(String vKeywords: keys){
               if (vKeywords.contains(keyword)){
                  contains = true;
               }
            }
            if(!contains){
               foundAll = false;
            }
         }
         if(foundAll){
            foundVehicles.add(vehicle);
         }
      }

      return foundVehicles;
   }


   //DELETE
   public void removeCustomer(Customer customer){
      customers.remove(customer);
   }


   public ArrayList<Vehicle> getVehicles() {
      return vehicles;
   }

   public ArrayList<String> getVehicleKeywords(ArrayList<Vehicle> vehicles){
      ArrayList<String> keywords = new ArrayList<>();
      for(Vehicle vehicle: vehicles){
         keywords.add(vehicle.getKeyWords().toLowerCase());
      }
      return keywords;
   }

   public boolean isAdmin(Customer customer) {
      return (customer.getPassword().equals(adminPass)&&customer.getID().equals(adminID));
   }

   public boolean buyVehicle(Customer customer, Vehicle vehicle){
      if(customer.getCurrentBalance()>=vehicle.getPrice()){
         vehicles.remove(vehicle);
         customer.buyVehicle(vehicle);
         return true;
      }
      return false;
   }


   public boolean approvePayment(boolean isApproved, Object[] cBPair){
      if(isApproved) {
         Customer customer = ((Customer) cBPair[0]);
         customer.setCurrentBalance(customer.getCurrentBalance() + ((Integer) cBPair[1]));
         return true;
      }else {
         return false;
      }
   }

   public void paymentRequest(Customer customer, int amount){
//      approvals.add(new Object[]{customer, amount});
      approvePayment(true, new Object[]{customer, amount});
   }

   //writes data to file database
   public void saveData() {
      saveData(database, 0);
   }

   public void saveData(String fileName, int i) {
      try {
         FileOutputStream fo = new FileOutputStream(fileName);
         ObjectOutputStream outputStream = new ObjectOutputStream(fo);
         outputStream.writeObject(this);
         outputStream.close();
         fo.close();



      } catch (IOException e) {
         //if a new file set by the user causes an error [like access denied],
         // the system tries to save to default "dealership[i].dat"
         e.printStackTrace();
         System.out.println(turnRed("UNABLE TO SAVE DATA TO : " + fileName +
                 "\nATTEMPTING RECOVERY..."));
         //The system will attempt recovery 10 times before sending an error message to the user
         //I couldn't find a solution for this without recursion
         if(i < 10) {
            setDatabase("dealership" + i +".dat");
            saveData("dealership" + i + ".dat", ++i);
         }else {
            System.out.println(redBackGround("Dealership Data not saved!"));
         }
         return;
      }
      System.out.println("Changes Successfully saved to : " + getDatabase());

   }


   public static CarDealership loadData() {
      CarDealership dealership = new CarDealership("The Car Dealership");
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

   public void printCustomers(){
      for(Customer customer1: customers){
         System.out.println(customer1.getName());
         System.out.println(customer1.getID());
         System.out.println(customer1.getPassword());
      }
   }

   public boolean contactExists(String input) {
      for(Customer customer: customers){
         if(customer.getContactInfo().equalsIgnoreCase(input)){
            return true;
         }
      }
      return false;
   }

   public void removeVehilce(Vehicle boughtVehicle) {
      vehicles.remove(boughtVehicle);
   }

   public ArrayList<Customer> getCustomers() {
      return customers;
   }
}
