package moviesClasses;

import Enums.Color;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Optional;

/**

 The Person class represents a person with their personal information.
 */
public class Person{


    private String name;

    private LocalDate birthday;

    private String passportID;

    private Color hairColor;

    private Location location;


    /**
     * Constructs a new person with the specified name, birthday, passport ID, hair color, and location.
     *
     * @param name the name of the person
     * @param birthday the birthday of the person
     * @param passportID the passport ID of the person
     * @param hairColor the hair color of the person
     * @param location the location of the person
     */
    @JsonCreator
    public Person(@JsonProperty(value = "name") String name, @JsonProperty("birthday") LocalDate birthday,
                  @JsonProperty("passportID") String passportID,
                  @JsonProperty("hairColor") Color hairColor, @JsonProperty(value = "location") Location location){
        this.name = name;
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
    }
    /**
     Constructs a new empty object for Json parsing.
     */
    public Person(){}

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName(){
        return name;
    }


    /**
     * Returns the birthday of the person.
     *
     * @return the birthday of the person
     */
    public LocalDate getBirthday(){
        return birthday;
    }


    /**
     * Returns the passport ID of the person.
     *
     * @return the passport ID of the person
     */
    public String getPassportID(){
        return passportID;
    }

    /**
     * Returns the hair color of the person.
     *
     * @return the hair color of the person
     */
    public Color getHairColor(){
        return hairColor;
    }

    /**
     * Returns the location of the person.
     *
     * @return the location of the person
     */
    public Location getLocation(){
        return location;
    }
}
