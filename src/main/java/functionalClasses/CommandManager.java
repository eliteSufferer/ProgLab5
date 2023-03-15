package functionalClasses;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;
import com.sun.tools.javac.Main;
import moviesClasses.Movie;
import moviesClasses.Movies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 This class provides a list of available commands and their descriptions as well as the settings for movies.

 It also provides methods for setting the movie collection and suggesting and executing commands.
 */
public class CommandManager {

    private static Movies movies;
    //static Scanner chosenScanner = new Scanner(System.in);

    static BufferedInputStream bis = new BufferedInputStream(System.in);

    static BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
    static String currentCommand;

    private static List<String> executedFiles = new ArrayList<>();

    static HashMap<Integer, Object> answers = new HashMap<>();

    static HashMap<String, String> commandList = CommandList.fillCommandList();

    static ArrayList<SetValues> settings = Settings.fillSettings();


    /**
     Sets the Movies collection to perform operations on.
     @param movies the Movies collection to set
     */
    public static void setMovies(Movies movies){
        CommandManager.movies = movies;
    }

    /**

     Prompts the user to enter a command and sets the current command.
     */
    public static void suggestNewAction(){
        try {
            System.out.println("Введите команду. Чтобы узнать перечень доступных команд введите help");
            //currentCommand = chosenScanner.nextLine();
            currentCommand = reader.readLine();
        } catch (NoSuchElementException e){
            System.out.println("Недопустимая строка, не надо так делать");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**

     Executes the specified command and performs the corresponding operation.
     @param executedCommand the command to execute
     */
    public static void startNewAction(String executedCommand){
        try {
            Executor.formCommandHistory(executedCommand.split(" ")[0]);
            if (Objects.equals(executedCommand.split(" ")[0], "execute_script") && !reader.equals(new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)))) && executedFiles.contains(executedCommand.split(" ")[1])) {
                System.out.println("Обнаружена рекурсия! Уберите. ");
                return;
            } else if (Objects.equals(executedCommand.split(" ")[0], "execute_script") && (reader.equals(new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)))) || !executedFiles.contains(executedCommand.split(" ")[1]))) {
                Path path = Paths.get(executedCommand.split(" ")[1]);
                reader = new BufferedReader(new FileReader(path.toFile()));
                //chosenScanner = new Scanner(path);

                executedFiles.add(executedCommand.split(" ")[1]);
            }
            switch (executedCommand.split(" ")[0]) {
                case "help" -> help();
                case "info" -> info();
                case ("add") -> Executor.addMovie(readInputNewMovieData());
                case ("add_if_min") -> Executor.addIfMin(readInputNewMovieData());
                case("remove_greater") -> Executor.removeGreater(readInputNewMovieData());
                case ("clear") -> Executor.clear();
                case ("history") -> System.out.println(Executor.getLast8Commands());
                case ("execute_script") -> FileManager.readFile(reader, executedCommand.split(" ")[1]);
                case ("save") -> FileManager.save();
                case ("show") -> show();
                case("filter_by_genre") -> System.out.println(Executor.filterByGenre(MovieGenre.valueOf(executedCommand.split(" ")[1])));
                case ("remove_by_id") -> {
                    if (executedCommand.matches("remove_by_id \\d*")) {
                        if (!Executor.removeById(Integer.parseInt(executedCommand.split(" ")[1]))) {
                            System.out.println("Фильма с таким id нет в коллекции");
                        };
                    } else {
                        System.out.println("id должно быть целым числом");
                    }
                }
                case ("remove_any_by_oscars_count") -> {
                    if (executedCommand.matches("remove_any_by_oscars_count \\d*")) {
                        if (!Executor.removeAnyByOscarsCount(Integer.parseInt(executedCommand.split(" ")[1]))) {
                            System.out.println("В коллекци нет ни 1 фильма с таким количеством оскаров");
                        }
                    } else {
                        System.out.println("Количество оскаров должно быть целым числом");
                    }
                }
                case ("update") -> {
                    if (executedCommand.matches("update \\d*") && Integer.parseInt(executedCommand.split(" ")[1]) >= 0) {
                        if (!Executor.updateMovie(Integer.parseInt(executedCommand.split(" ")[1]), readInputNewMovieData())) {
                            System.out.println("В коллекци нет ни одного фильма с таким id (введите add, чтобы создать)");
                        }
                    } else {
                        System.out.println("id должно быть целым числом");
                    }

                }
                case("print_descending") -> Executor.printDescending();
                default -> System.out.println("Введите команду из доступного перечня");
            }
        } catch (NoSuchFileException e){
            System.out.println("Такого файла не существует");
        }
        catch (Exception e) {
            System.out.println("Ошибка при выполнении команды");
            e.printStackTrace();
            System.exit(0);
        }

    }
    /**

     Reads input from the console and creates a HashMap of movie data.

     @return a HashMap of movie data
     */
    public static HashMap<Integer, Object> readInputNewMovieData() {
        int step = 0;
        while (step < CommandManager.settings.size()) {
            try {
                System.out.println(CommandManager.settings.get(step).getComment() + ". Тип этого значения: " + CommandManager.settings.get(step).getValueType() + (CommandManager.settings.get(step).getIsRequired() ? ". Обязательное значение" : ". Необязательное значение"));
                //String line = chosenScanner.nextLine().trim();
                String line = reader.readLine().trim();
                if (line.equals("exit")) {System.exit(0);}
                if (line.length() == 0 && CommandManager.settings.get(step).getIsRequired()) {
                    System.out.println("Значение не может быть пустым");
                    continue;
                } else {
                    if (line.length() == 0) {
                        answers.put(step, null);
                        step += 1;
                        continue;
                    }
                }
                switch (CommandManager.settings.get(step).getValueType()) {
                    case ("float"), ("Float") -> {
                        float parsedValue = (Float.parseFloat(line));
                        if ((settings.get(step).getKey() == 2 && parsedValue <= -838) ) {
                            System.out.println("Значение должно быть больше -838");
                        }
                        else {
                            answers.put(step, parsedValue);
                            step += 1;
                        }
                        answers.put(step, parsedValue);
                    }

                    case ("double") -> {
                        double parsedValue = Double.parseDouble(line);
                        if (settings.get(step).getKey() == 1 && parsedValue <= -201){
                            System.out.println("Значение должно быть больше -201");
                        }
                        else {
                            answers.put(step, parsedValue);
                            step += 1;
                        }
                    }

                    case ("LocalDate") -> {
                        try {
                            LocalDate parsedValue = LocalDate.parse(line);
                            answers.put(step, parsedValue);
                            step += 1;
                        }
                        catch (DateTimeParseException e){
                            System.out.println("Введите валидную дату в формате yyyy-mm-dd");
                        }
                    }

                    case ("int"), ("Integer") -> {
                        int parsedValue = Integer.parseInt(line);
                        if ((settings.get(step).getKey() == 3 || settings.get(step).getKey() == 4) && parsedValue <= 0) {
                            System.out.println("Значение должно быть больше нуля");
                        } else {
                            answers.put(step, parsedValue);
                            step += 1;
                        }

                    }
                    case ("String") -> {
                        if ((settings.get(step).getKey() == 0 || settings.get(step).getKey() == 7 || settings.get(step).getKey() == 9) && line.trim().isEmpty()) {
                            System.out.println("Значение не может быть пустым");
                        } else {
                            if (settings.get(step).getKey() == 9 && line.length() < 10) {
                                System.out.println("Значение должно состоять не менее чем из 10 символов");
                            } else {
                                answers.put(step, line);
                                step += 1;
                            }
                        }

                    }
                    case ("MovieGenre") -> {
                        MovieGenre parsedValue = Enum.valueOf(MovieGenre.class, line);
                        answers.put(step, parsedValue);
                        step += 1;
                    }
                    case ("MpaaRating") -> {
                        MpaaRating parsedValue = Enum.valueOf(MpaaRating.class, line);
                        answers.put(step, parsedValue);
                        step += 1;
                    }
                    case ("Color") -> {
                        Color parsedValue = Enum.valueOf(Color.class, line);
                        answers.put(step, parsedValue);
                        step += 1;
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Введите значение правильного типа данных: " + CommandManager.settings.get(step).getValueType());
            } catch (IllegalArgumentException e) {
                System.out.println("Введите значение из списка допустимых значений ->");
            } catch (IOException e) {
                System.out.println("Возникла ошибка");
            }
        }
        return answers;
    }
    /**
     * Prints the list of available commands and their descriptions.
     */
    public static void help() {
        for (var entry : commandList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    /**
     * Prints information about the current state of the collection, such as the class of the elements, the initialization date,
     * the number of elements, the list of movies in the collection and the executed files.
     */
    public static void info() {
        System.out.println("Класс элементов коллекции: " + movies.getClass());
        System.out.println("Дата и время ининциализации коллекции: " + movies.getInitializationDate());
        System.out.println("Количество элементов в колллекции: " + movies.getMoviesCount());

        System.out.println("Список имеющихся в коллекции фильмов (id + название)");

        for (Movie movie : movies.getMovies()) {
            System.out.println(movie.getId() + " - " + movie.getName());
        }

    }
    /**
     * Prints the information of all the movies in the collection, sorted by their natural order.
     */
    public static void show() {
        for (Movie movie : movies.getSortedMovies()) {
            for (String s : movie.getInstance()) {
                System.out.println(s);
            }
        }
    }

    /**
     * Returns the command that was last executed.
     *
     * @return the last executed command.
     */

    public static String getExecutedCommand() {
        return currentCommand;
    }
    /**
     * Returns a list with the names of the executed files.
     * @return a list with the names of the executed files.
     */
    public static List getExecutedFiles() {
        return executedFiles;
    }
}

