package functionalClasses;

import Enums.MovieGenre;
import moviesClasses.Movie;
import moviesClasses.Movies;

import java.io.IOException;
import java.util.*;

/**

 This class represents an Executor for movie-related commands. It provides methods for adding, updating, removing,
 and filtering movies, as well as keeping track of command history and printing movies in descending order.
 */
public class Executor {
    private static Movies movies;
    private static List<String> commandHistory = new ArrayList<>();
    private static List<String> matchingGenres = new ArrayList<>();
    private static List<String> reverse = new ArrayList<>();

    /**
     Sets the movies to be managed by the Executor.
     @param movies the movies to be managed
     */
    public static void setMovies(Movies movies){
        Executor.movies = movies;
    }

    /**

     Adds a new movie to the movie list.
     @param data the data for the new movie
     */
    public static void addMovie(HashMap data) {
        int newID;
        if (movies.getSortedMovies().size() > 0){
            newID = movies.getSortedMovies().get(movies.getSortedMovies().size() - 1).getId() + 1;
        }

        else {
            newID = 0;
        }

        movies.getMovies().add(new Movie(newID, data));
        System.out.println(movies.getMovies());
    }


    /**

     Updates a movie in the movie list with the given ID.
     @param id the ID of the movie to be updated
     @param data the new data for the movie
     @return true if the movie was found and updated, false otherwise
     @throws IOException if there is an error updating the movie
     */
    public static boolean updateMovie(int id, HashMap data) throws IOException {
        for (Movie movie : movies.getMovies()) {
            if (movie.getId() == id) {
                movie.updateMovies(data);
                return true;
            }
        }
        return false;
    }

    /**

     Removes a movie from the movie list with the given ID.
     @param enteredId the ID of the movie to be removed
     @return true if the movie was found and removed, false otherwise
     */
    public static boolean removeById(int enteredId) {
        for (Movie movie : movies.getMovies()) {
            if (movie.getId() == enteredId) {
                movies.getMovies().remove(movie);
                return true;
            }
        }
        return false;
    }

    /**
    Removes all movies from the movie list.
    */
    public static void clear() {
        movies.getMovies().clear();
    }

    /**
     Adds a new movie to the movie list if it has the shortest length of all movies in the list.
     @param data the data for the new movie
     @throws IOException if there is an error adding the movie
     */
    public static void addIfMin(HashMap data) throws IOException {
        boolean isMin = true;
        for (Movie movie : movies.getMovies()) {
            if (movie.getLength() <= (Integer) data.get(4)) {
                isMin = false;
                break;
            }
        }
        if (isMin) {
            addMovie(data);
        }
    }

    /**
    Removes a movie from the movie list with the given number of Oscars won.
    @param enteredCount the number of Oscars won by the movie to be removed
    @return true if a movie with the given number
    */
    public static boolean removeAnyByOscarsCount(long enteredCount) {
        for (Movie movie : movies.getMovies()) {
            if (movie.getOscarsCount() == enteredCount) {
                movies.getMovies().remove(movie);
                return true;
            }
        }
        return false;
    }

    /**
     * forms a history of entered commands
     * @param command an entered command
     */
    public static void formCommandHistory(String command){
        commandHistory.add(command);
    }

    /**
     Gets a sublist of the last 8 commands in the commandHistory list.
     @return a sublist of the last 8 commands in the commandHistory list.
     */
    public static List<String> getLast8Commands() {
        return commandHistory.subList(commandHistory.size() >= 8 ? commandHistory.size() - 8 : 0, commandHistory.size());
    }

    /**
     Removes all movies from the movieList whose length is greater than or equal to a given length.
     @param data a HashMap containing the data of the movie to be compared to.
     */
    public static void removeGreater(HashMap data) {
        movies.getMovies().removeIf(movie -> movie.getLength() >= (int) data.get(4));
    }


    /**
     Returns a list of all the strings contained in the instance variables of movies with a given genre.
     @param inputGenre the genre of movies to filter by.
     @return a list of all the strings contained in the instance variables of movies with a given genre.
     */
    public static List<String> filterByGenre(MovieGenre inputGenre) {
        for (Movie movie : movies.getMovies()){
            if (movie.getGenre() == inputGenre){
                for (String s : movie.getInstance()) {
                    matchingGenres.add(s);
                }

            }
        }
        return matchingGenres;
    }

    /**
     Prints the movie data for all movies in descending order by ID.
     */
    public static void printDescending(){
        for (Movie movie : movies.getReversedMovies()){
                for (String s : movie.getInstance()) {
                    System.out.println(s);
            }
        }
    }




}
