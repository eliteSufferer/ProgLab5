package moviesClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import functionalClasses.FilePathInitializer;

import java.io.File;
import java.io.IOException;
/**

 The Coordinates class represents the coordinates of a movie location.

 The class includes two fields, x and y, that represent the values of the horizontal and vertical coordinates.
 */
public class Coordinates {

    private double x;

    private float y;

    /**

     Constructs a new Coordinates object with the specified values of x and y.
     @param x the horizontal coordinate value of the new Coordinates object
     @param y the vertical coordinate value of the new Coordinates object
     */
    @JsonCreator
    public Coordinates(@JsonProperty(value = "x") double x, @JsonProperty(value = "y") float y) {
        this.x = x;
        this.y = y;
    }
    /**
     Constructs a new empty object for Json parsing.
     */
    public Coordinates(){}
    /**

     Returns the horizontal coordinate value of this Coordinates object.
     @return the value of the x field
     */

    public double getX(){
        return x;
    }

    /**

     Returns the vertical coordinate value of this Coordinates object.
     @return the value of the y field
     */

    public float getY(){
        return y;
    }

}
