package moviesClasses;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public Location(@JsonProperty(value = "x") float x, @JsonProperty(value = "y") Integer y,
                    @JsonProperty(value = "z") Float z, @JsonProperty(value = "name") String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     Constructs a new empty object for Json parsing.
     */
    public Location(){}


    public float getX(){
        return x;
    }


    public Integer getY(){
        return y;
    }


    public Float getZ(){
        return z;
    }


    public String getName(){
        return name;
    }

}
