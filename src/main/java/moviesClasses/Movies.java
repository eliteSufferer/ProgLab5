package moviesClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**

 A class representing a collection of Movie objects.
 */
public class Movies{
    /**
     * The list of movies stored in a TreeSet, sorted by the movie ID.
     */
    private TreeSet<Movie> movieList = new TreeSet<>(Comparator.comparingInt(Movie::getId));
    /**
     * The date of initialization of the collection.
     */
    private Date initializationDate;


    /**
     * Creates a new Movies object and sets the initialization date to the current date and time.
     */
    public Movies(){
        this.initializationDate = new Date();
    }


    /**
     * Returns the list of movies stored in the collection.
     *
     * @return The TreeSet of Movie objects
     */
    public TreeSet<Movie> getMovies(){
        return this.movieList;
    }


    /**
     * Returns the date of initialization of the collection.
     *
     * @return The date and time of initialization as a java.util.Date object
     */
    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Returns the number of movies in the collection.
     *
     * @return The number of Movie objects in the TreeSet
     */
    @JsonIgnore
    public int getMoviesCount(){
        return movieList.size();
    }


    /**
     * Returns the list of movies sorted by the movie ID in ascending order.
     *
     * @return The List of Movie objects sorted by the movie ID in ascending order
     */
    @JsonIgnore
    public List<Movie> getSortedMovies() {
        List<Movie> sortedList = new ArrayList<>(movieList);
        sortedList.sort(new Comparator<>() {
            public int compare(Movie o1, Movie o2) {
                return (Integer.compare(o1.getId() - o2.getId(), 0));
            }
        });
        return sortedList;
    }

    /**
     * Returns the list of movies sorted by the movie ID in descending order.
     *
     * @return The List of Movie objects sorted by the movie ID in descending order
     */
    @JsonIgnore
    public List<Movie> getReversedMovies() {
        List<Movie> sortedList = new ArrayList<>(movieList);
        sortedList.sort(new Comparator<>() {
            public int compare(Movie o1, Movie o2) {
                return (Integer.compare(o2.getId() - o1.getId(), 0));
            }
        });
        return sortedList;
    }
}
