package moviesClasses;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Movie(Integer id, String name, Coordinates coordinates, LocalDate creationDate, int oscarsCount, Integer length, MovieGenre genre, MpaaRating mpaaRating, Person director){
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
        this.coordinates = new Coordinates((double) data.get(1), (float) data.get(2));
        this.creationDate = LocalDate.now();
        this.oscarsCount = (int) data.get(3);
        this.length = (Integer) data.get(4);
        this.genre = (MovieGenre) data.get(5);
        if (data.get(6) == null){
            this.mpaaRating = null;
        }
        else {
            this.mpaaRating = (MpaaRating) data.get(6);
        }
        this.director = new Person((String) data.get(7), (LocalDate) data.get(8), (data.get(9) == null ? null : (String) data.get(9)),
                (data.get(10) == null ? null : (Color) data.get(10)),
                new Location((float) data.get(11), (Integer) data.get(12), (Float) data.get(13), (data.get(14) == null ? null : (String) data.get(14))));
    }

    /**

     Updates the movie with the specified data.
     @param data the data to update the movie with
     */
    public void updateMovies(HashMap data) {
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates((double) data.get(1), (float) data.get(2));
        this.creationDate = LocalDate.now();
        this.oscarsCount = (int) data.get(3);
        this.length = (Integer) data.get(4);
        this.genre = (MovieGenre) data.get(5);
        if (data.get(6) == null){
            this.mpaaRating = null;
        }
        else {
            this.mpaaRating = (MpaaRating) data.get(6);
        }
        this.director = new Person((String) data.get(7), (LocalDate) data.get(8), (data.get(9) == null ? null : (String) data.get(9)),
                (data.get(10) == null ? null : (Color) data.get(10)),
                new Location((float) data.get(11), (Integer) data.get(12), (Float) data.get(13), (data.get(14) == null ? null : (String) data.get(14))));
    }

    /**
     Constructs a new empty object for Json parsing.
     */
    public Movie(){}

    @JsonProperty("id")
    public Integer getId(){
        return id;
    }

    @JsonProperty(value = "name", required = true)
    public String getName(){
        return name;
    }

    @JsonProperty("length")
    public Integer getLength(){
        return length;
    }

    @JsonProperty("oscarsCount")
    public int getOscarsCount() {
        return oscarsCount;
    }

    @JsonProperty("genre")
    public MovieGenre getGenre(){
        return genre;
    }

    @JsonProperty("coordinates")
    public Coordinates getCoordinates(){
        return coordinates;
    }

    @JsonProperty("creationDate")
    public LocalDate getCreationDate(){
        return creationDate;
    }

    @JsonProperty("mpaaRating")
    public MpaaRating getMpaaRating(){
        return mpaaRating;
    }

    @JsonProperty("director")
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
                "mpaaRating=" + mpaaRating + ", director: [" + director.getName() + ", " +director.getPassportID() + ", " + director.getBirthday() + ", " +
                "location:[" + director.getLocation().getX() + ", " + director.getLocation().getY() + ", " + director.getLocation().getZ() + ", " + director.getLocation().getName() + "]]" + "}"};
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}