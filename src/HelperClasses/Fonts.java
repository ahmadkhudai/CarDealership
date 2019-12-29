package HelperClasses;

import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Fonts  {

   public static Font normalFont(int size){
        return new Font("Subjectivity", Font.PLAIN,size);
   }

   public static Font mediumFont(){
       return new Font("Subjectivity Medium", Font.PLAIN,18);
   }

    public static Font mediumFont(int size){
        return new Font("Subjectivity Medium", Font.PLAIN,size);
    }

    public static Font mediumItalicFont(int size) {
        return new Font("Subjectivity Medium", Font.ITALIC, size);
    }

    public static Font boldFont(int size) {
        return new Font("Subjectivity", Font.BOLD,size);
    }

    public static Font boldItalicFont(int size) {
            return new Font("Subjectivity Bold", Font.ITALIC + Font.BOLD ,size);
    }


    public static Font SegoeRegular(int size) {
        return new Font("Segoe UI", Font.PLAIN, size );
    }

    public static Font SegoeSemiBold(int size) {
        return new Font("Segoe UI Semibold", Font.PLAIN, size );
    }
}
