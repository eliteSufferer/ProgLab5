package functionalClasses;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.InvalidFieldException;
import moviesClasses.Movie;
import moviesClasses.Movies;
import validators.*;

import java.io.*;
import java.util.*;


public class FileManager {
    /**

     FileManager class provides functionality to fill the Movies object from the input file, read a file
     and execute commands line by line and save the current Movies object to the file.
     */
    private static Movies movies;
    static ObjectMapper objectMapper = new ObjectMapper();

    static HashMap<Integer, Object> answers = new HashMap<>();



    /**
     This method reads the input file and fill the Movies object with the contents of the file.
     @return The Movies object created from the input file.
     */
    public static Movies fill(String[] value) throws InvalidFieldException{
        try {
            objectMapper.registerModule(new JavaTimeModule());
            Movies mvs = objectMapper.readValue(new File(value[0]), Movies.class);
            for (Movie movie : mvs.getMovies()){
                if (!MovieValidator.isValidName(movie.getName())){
                    throw new InvalidFieldException("Недопустимое название фильма");
                }
                if (!CoordsValidator.isValidX(movie.getCoordinates().getX())){
                    throw new InvalidFieldException("Недопустимая координата Х");
                }
                if (!CoordsValidator.isValidY(movie.getCoordinates().getY())){
                    throw new InvalidFieldException("Недопустимая координата Y");
                }
                if (!MovieValidator.isValidCreationDate(movie.getCreationDate())){
                    throw new InvalidFieldException("Неправильная дата создания");
                }
                if (!MovieValidator.isValidOscarsCount(movie.getOscarsCount())){
                    throw new InvalidFieldException("Недопустимое кол-во оскаров");
                }
                if (!MovieValidator.isValidLength(movie.getLength())){
                    throw new InvalidFieldException("Недопустимая длина");
                }
                if (!PersonValidator.isValidName(movie.getDirector().getName())){
                    throw new InvalidFieldException("Недопустимое имя режиссера");
                }
                if (!PersonValidator.isValidBirthday(movie.getDirector().getBirthday())){
                    throw new InvalidFieldException("Недопустимая дата рождения режиссера");
                }
                if (!PersonValidator.isValidPassport(movie.getDirector().getPassportID())){
                    throw new InvalidFieldException("Недопустимые данные паспорта");
                }
                if (!PersonValidator.isValidHair(movie.getDirector().getHairColor())){
                    throw new InvalidFieldException("Недопустимый цвет волос");
                }
                if(!LocationValidator.isValidY(movie.getDirector().getLocation().getY())){
                    throw new InvalidFieldException("Недопустимая локация Y");
                }
                if(!LocationValidator.isValidZ(movie.getDirector().getLocation().getZ())){
                    throw new InvalidFieldException("Недопустимая локация Z");
                }
            }
            return mvs;

        } catch (InvalidFieldException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidFormatException e) {
            System.out.println("Неверный элемент в Енаме, исправьте.");
        } catch (IOException e){
            System.out.println("Ошибка при чтении данных из файла");
        }
        return null;
    }

    /**
     This method sets the Movies object to manage.
     @param movies The Movies object to manage.
     */
    public static void manageClass(Movies movies){
        FileManager.movies = movies;
    }

    /**
     This method reads a file and executes the commands line by line.
     @param bufferedReader The scanner object for the file.
     @param fileName The name of the file.
     */
    //public static void readFile(Scanner fileScanner, String fileName){
    public static void readFile(BufferedReader bufferedReader, String fileName) throws IOException {
        try {
            String line;
            boolean shouldAdd = false;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                if (line.equals("add")) {
                    shouldAdd = true;
                    continue;
                }
                if (shouldAdd) {
                    for (int i = 0; i < 15; i++) {
                        answers.put(i, line);
                        AddFromFileValidator.checkAnswers(i, answers);
                        line = bufferedReader.readLine();
                    }
                    shouldAdd = false;
                    Executor.addMovie(answers);

                }
                else {
                    CommandManager.startNewAction(line);
                }
            }
        }
        catch (InvalidFieldException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("Неверный формат данных, проверьте порядок");
        }
        catch (NoSuchElementException e){
            System.out.println("Достигнут конец файла");
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }

        bufferedReader.close();
        CommandManager.getExecutedFiles().remove(fileName);
        //CommandManager.chosenScanner = new Scanner(System.in);
        CommandManager.reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
    }


    /**
     This method saves the current state of Movies object to the input file.
     @throws IOException If there is an error during the writing process.
     */
    public static void save() throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(FilePathInitializer.getPath()), movies);
    }

}
