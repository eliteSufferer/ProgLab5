package validators;

/**
 The CoordsValidator class provides static methods to validate various properties of a Coordinates object.
 */
public class CoordsValidator {
    public static boolean isValidX(double x){
        return x > -201;
    }

    public static boolean isValidY(float y){
        return y > -838;
    }
}
