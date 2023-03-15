package validators;

import Enums.Color;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 The PersonValidator class provides static methods to validate various properties of a Person object.
 */
public class PersonValidator {

    /**
     Checks if the given name is valid.
     @param name the name to be checked
     @return true if the name is not null and has a length greater than 0, false otherwise
     */
    public static boolean isValidName(String name){
        return (name != null && name.length() > 0);
    }

    /**
     Checks if the given birthday is valid.
     @param birthday the birthday to be checked
     @return true if the birthday is null or has a valid date format of yyyy-mm-dd, false otherwise
     */
    public static boolean isValidBirthday(LocalDate birthday){
        if (birthday == null){
            return true;
        }
        return birthday.toString().matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    /**
     Checks if the given passport ID is valid.
     @param passportID the passport ID to be checked
     @return true if the passport ID is null or has a length greater than 10, false otherwise
     */
    public static boolean isValidPassport(String passportID) {
        return passportID == null || passportID.length() > 10;
    }

    /**
     Checks if the given hair color is valid.
     @param color the hair color to be checked
     @return true if the hair color is null or one of the valid colors defined in the Color enum, false otherwise
     */
    public static boolean isValidHair(Color color) {
        if (color == null){
            return true;
        }
        Color[] validColors = Color.values();
        for (Color c : validColors){
            if (c == color) {
                return true;
            }
        }
        return false;
    }


}
