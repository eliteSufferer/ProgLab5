package moviesClasses;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
/**

 The Location class represents the coordinates of a person location and a name of a place where he is located.

 */
public class Location{
    private float x;
    private Integer y;
    private Float z;
    private String name;

    /**

     Constructs a new Location object with the specified x, y, z coordinates and name.
     @param x the x coordinate
     @param y the y coordinate
     @param z the z coordinate
     @param name the name of the location
     */
    public Location(float x, Integer y, Float z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     Constructs a new empty object for Json parsing.
     */
    public Location(){}

    @JsonProperty(value = "x", required = true)
    public float getX(){
        return x;
    }

    @JsonProperty(value = "y", required = true)
    public Integer getY(){
        return y;
    }

    @JsonProperty(value = "z", required = true)
    public Float getZ(){
        return z;
    }

    @JsonProperty(value = "name")
    public String getName(){
        return name;
    }

}
