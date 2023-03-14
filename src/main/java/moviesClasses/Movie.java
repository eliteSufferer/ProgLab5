package moviesClasses;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.management.ObjectName;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
/**

 Represents a movie with an ID, name, coordinates, creation date, Oscars count, length, genre, MPAA rating, and director.

 Implements the Comparable interface to allow sorting by creation date.
 */
public class Movie implements Comparable<Object>{
    private Integer id;
    private String name;
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private Integer length; //Поле не может быть null, Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле не может быть null

    /**

     Constructs a Movie object with the specified parameters.
     @param id the ID of the movie
     @param name the name of the movie
     @param coordinates the coordinates of the movie
     @param creationDate the creation date of the movie
     @param oscarsCount the number of Oscars won by the movie
     @param length the length of the movie
     @param genre the genre of the movie
     @param mpaaRating the MPAA rating of the movie
     @param director the director of the movie
     */

    @JsonCreator
    public Movie(@JsonProperty("id") Integer id, @JsonProperty(value = "name") String name,
                 @JsonProperty("coordinates") Coordinates coordinates,
                 @JsonProperty("creationDate") LocalDate creationDate, @JsonProperty("oscarsCount") int oscarsCount,
                 @JsonProperty("length") Integer length, @JsonProperty("genre") MovieGenre genre,
                 @JsonProperty("mpaaRating") MpaaRating mpaaRating, @JsonProperty("director") Person director){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.length = length;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }
    /**

     Constructs a Movie object with the specified ID and data.
     @param id the ID of the movie
     @param data the data to specify the object
     */
    public Movie(int id, HashMap data) {
        this.id = id;
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates(Double.parseDouble(data.get(1).toString()), Float.parseFloat(data.get(2).toString()));
        this.creationDate = LocalDate.now();
        this.oscarsCount =  Integer.parseInt(data.get(3).toString());
        this.length = Integer.parseInt(data.get(4).toString());
        this.genre = Enum.valueOf(MovieGenre.class, data.get(5).toString());
        if (data.get(6) == null){
            this.mpaaRating = null;
        }
        else {
            this.mpaaRating = Enum.valueOf(MpaaRating.class, data.get(6).toString());
        }
        this.director = new Person((String) data.get(7), (data.get(8) != null ? LocalDate.parse(data.get(8).toString()) : null), (data.get(9) == null ? null : data.get(9).toString()),
                (data.get(10) == null ? null : Enum.valueOf(Color.class, data.get(10).toString())),
                new Location(Float.parseFloat(data.get(11).toString()), Integer.parseInt(data.get(12).toString()), Float.parseFloat(data.get(13).toString()), (data.get(14) == null ? null : (String) data.get(14))));
    }

    /**

     Updates the movie with the specified data.
     @param data the data to update the movie with
     */
    public void updateMovies(HashMap data) {
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates(Double.parseDouble(data.get(1).toString()), Float.parseFloat(data.get(2).toString()));
        this.creationDate = LocalDate.now();
        this.oscarsCount =  Integer.parseInt(data.get(3).toString());
        this.length = Integer.parseInt(data.get(4).toString());
        this.genre = Enum.valueOf(MovieGenre.class, data.get(5).toString());
        if (data.get(6) == null){
            this.mpaaRating = null;
        }
        else {
            this.mpaaRating = Enum.valueOf(MpaaRating.class, data.get(6).toString());
        }
        this.director = new Person((String) data.get(7), (data.get(8) != null ? LocalDate.parse(data.get(8).toString()) : null), (data.get(9) == null ? null : data.get(9).toString()),
                (data.get(10) == null ? null : Enum.valueOf(Color.class, data.get(10).toString())),
                new Location(Float.parseFloat(data.get(11).toString()), Integer.parseInt(data.get(12).toString()), Float.parseFloat(data.get(13).toString()), (data.get(14) == null ? null : (String) data.get(14))));
    }

    /**
     Constructs a new empty object for Json parsing.
     */
    public Movie(){}


    public Integer getId(){
        return id;
    }


    public String getName(){
        return name;
    }


    public Integer getLength(){
        return length;
    }



    public int getOscarsCount() {
        return oscarsCount;
    }


    public MovieGenre getGenre(){
        return genre;
    }


    public Coordinates getCoordinates(){
        return coordinates;
    }


    public LocalDate getCreationDate(){
        return creationDate;
    }


    public MpaaRating getMpaaRating(){
        return mpaaRating;
    }


    public Person getDirector(){
        return director;
    }

    /**
     * Returns the instance of the object which is convenient to read.
     *
     * @return the name of the person
     */
    @JsonIgnore
    public String[] getInstance(){
        return new String[]{"id=" + id + ", name=" + name + ", coordinates=[" + "x:" + coordinates.getX() + ", y:" + coordinates.getY() + "]" + ", " +
                "creationDate=" + creationDate + ", oscarsCount=" + oscarsCount + ", length=" + length + ", genre=" + genre + ", " +
                "mpaaRating=" + mpaaRating + ", director: [" + director.getName() + ", " +director.getPassportID() + ", " + director.getHairColor() + ", " + director.getBirthday() + ", " +
                "location:[" + director.getLocation().getX() + ", " + director.getLocation().getY() + ", " + director.getLocation().getZ() + ", " + director.getLocation().getName() + "]]" + "}"};
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}