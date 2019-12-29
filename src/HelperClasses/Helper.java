package HelperClasses;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    //Methods to format strings
    //methods do exactly what their names describe
    public static String turnRed(String str){
        return turnBold("\033[31m"+str+"\033[0m");
    }

    public static String turnGreen(String str){
        return turnBold("\033[32m"+str+"\033[0m");
    }

    public static String turnYellow(String str){
        return turnBold("\033[33m"+str+"\033[0m");
    }

    public static String turnPurple(String str){return turnBold("\033[35m"+str+"\033[0m"); }

    public static String turnBold(String str){
        return "\033[0;1m" + str + "\033[0m";
    }

    public static String greenBackGround(String str){
        return "\033[42m"+str+"\033[0m";
    }

    public static String redBackGround(String str){
        return "\n"+ turnBold("\033[41m"+str+"\033[0m");
    }

    public static String ferozeBackGround(String str){
        return "\033[46m"+str+"\033[0m";
    }

    public static String yellowBackGround(String str){
        return "\033[43m"+str+"\033[0m";
    }

    public static String removeFormating(String text) {
        text = text.replaceAll("\\s+","");
        text = text.toLowerCase();
        return text;
    }

    //string -> ---STRING---
    public static String getSectionHeader(String text){
        text = text.toUpperCase();
        return turnPurple("---" + text + "---");
    }

    //returns the capitalized version of passed variable'name' [ahmad-> Ahmad]
    public static String capitalaize(String name){
        String[] strings = name.split("\\s+");
        name = (strings[0].substring(0, 1).toUpperCase() + strings[0].substring(1));
        for (int i = 1; i < strings.length; i++) {
            name = name  + " " + (strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1));
        }
        return name;
    }

    //checks if the string is a valid ID or not
    public static boolean isID(String id){
        return id.matches("((^|, )([A-Za-z]{2}[0-9]{2}-[A-Za-z]{3}-[0-9]{3}|[A-Za-z]{2}[0-9]{2}[A-Za-z]{3}[0-9]{3}))+$");
    }

    //Returns a formatted ID. This method helps in normalizing all the IDs since IDs are
    // the main method of identification in the Library Management System
    public static String formatID(String ID){
        ID = ID.toLowerCase();
        if(ID.matches("[A-Za-z]{2}[0-9]{2}[A-Za-z]{3}[0-9]{3}")){
            ID = ID.replaceAll(ID.substring(4,10), "-" + ID.substring(4,7)+ "-" + ID.substring(7,10));
        }
        return ID;
    }

    //check if name is valid
    public static boolean isValidName(String name){
        if(name.matches("[a-zA-Z \\-\\.\\']{2,}")){
            return true;
        }
            System.out.println(turnRed("Invalid Name!"));
            return false;
    }

    //checks if str is Int or not
    public static boolean isInt(String str){
        return str.matches("\\d+");
    }

    public static boolean isFileName(String database) {
        database = database.trim();
        String temp = database.replaceAll("\\s+", "-");
        if (temp.matches("^[a-zA-Z0-9_.-]+$")) {
            File file = new File(database);
            return !file.isDirectory();
        }
        return false;
    }



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean isValidPhone(String number){
        //number.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")||
        return number.matches("\\d{11}|(?:\\d{4}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{3}");
    }


    public static String getFormattedNumber(int price) {
        return "$"+(new DecimalFormat("#,###").format(price));
    }

    public static boolean isValidAmount(String text) {

        return text.replaceAll(",", "").matches("^[1-9]\\d*$");
    }

    public static int removeFormatting(String formattedString){
        try {
            return (int) NumberFormat.getNumberInstance(java.util.Locale.US).parse(formattedString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getFormattedNumberWithoutDollarSignYouIdiot(int number) {
        return (new DecimalFormat("#,###").format(number));
    }


}
