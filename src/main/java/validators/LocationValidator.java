package validators;

/**
 The LocationValidator class provides static methods to validate various properties of a Location object.
 */
public class LocationValidator {

    public static boolean isValidY(Integer y){
        return y != null;
    }

    public static boolean isValidZ(Float z){
        return z != null;
    }
}
