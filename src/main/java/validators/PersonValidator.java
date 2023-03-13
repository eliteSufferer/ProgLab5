package validators;

import Enums.Color;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonValidator {
    public static boolean isValidName(String name){
        return (name != null && name.length() > 0);
    }

    public static boolean isValidBirthday(LocalDate birthday){
        if (birthday == null){
            return true;
        }
        return birthday.toString().matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidPassport(String passportID) {
        return passportID == null || passportID.length() > 10;
    }

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
