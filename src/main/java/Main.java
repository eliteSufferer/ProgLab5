import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import functionalClasses.CommandManager;
import functionalClasses.Executor;
import functionalClasses.FileManager;
import functionalClasses.FilePathInitializer;
import moviesClasses.Movies;


import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**
     * The main method is the entry point of the program.
     * It initializes a path to a file, loads data from it and sets
     * the movies data for the CommandManager and Executor classes.
     * It manages the class and suggests new actions for users until they enter 'exit'.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        try {
            if (args.length != 1) {
                System.out.println("Путь к файлу не может быть пустой.");
                return;
            }

            FilePathInitializer.formPath(args);



            //FilePathInitializer.formPath(new Scanner(System.in).nextLine().split(" "));

            //System.out.println(FilePathInitializer.getPath().getClass());

            //Movies movies = new Movies();
            Movies movies = FileManager.fill(args);
//            assert movies != null;
//            System.out.println(movies.getMovies());
            CommandManager.setMovies(movies);
            Executor.setMovies(movies);
            FileManager.manageClass(movies);

            CommandManager.suggestNewAction();
            while (!Objects.equals(CommandManager.getExecutedCommand(), "exit")) {
                CommandManager.startNewAction(CommandManager.getExecutedCommand());
                CommandManager.suggestNewAction();
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println("Произошла ошибка при чтении JSON файла");
        }
    }
}