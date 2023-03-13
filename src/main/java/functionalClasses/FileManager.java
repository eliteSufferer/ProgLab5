package functionalClasses;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import moviesClasses.Movie;
import moviesClasses.Movies;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;


public class FileManager {
    /**

     FileManager class provides functionality to fill the Movies object from the input file, read a file
     and execute commands line by line and save the current Movies object to the file.
     */
    private static Movies movies;
    static ObjectMapper objectMapper = new ObjectMapper();

    static BufferedReader currentReader;
    static HashMap<Integer, Object> answers = new HashMap<>();


    /**
     This method reads the input file and fill the Movies object with the contents of the file.
     @return The Movies object created from the input file.
     */
    public static Movies fill(String[] value) {
        try {


            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(new File(value[0]), Movies.class);
            //return gson.fromJson(FilePathInitializer.getPath(), Movies.class);

        } catch (IOException e) {
            System.out.println("Уберите дичь из файла и повторите попытку");
            //e.printStackTrace();
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
     @param fileScanner The scanner object for the file.
     @param fileName The name of the file.
     */
    public static void readFile(Scanner fileScanner, String fileName) {
        try {
            int step = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals("add")) {
                    if (answers.size() <= 15) {
                        answers.put(step, line);
                        step += 1;
                    } else {
                        break;
                    }
                } else {
                    CommandManager.startNewAction(line);
                }
            }
            fileScanner.close();
            CommandManager.getExecutedFiles().remove(fileName);
            CommandManager.chosenScanner = new Scanner(System.in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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





//            while (fileScanner.hasNextLine()) {
//                String line = fileScanner.nextLine();
//                if (line.equals("add")) {
//                    // Clear the list and add the "add" command to it
//                    addList.clear();
//                } else if (addList.size() > 0) {
//                    if (line.startsWith(" ")){
//                        addList.add(null);
//                    }
//
//                    else {
//                        addList.add(line);
//                    }
//                    // Add the line to the list
//
//
//                    // Check if the list has reached the required size
//                    if (addList.size() == 16) {
//                        // Do something with the list here
//                        int step = 0;
//                        for (String el : addList){
//                            newAdd.put(step++, el);
//                        }
//                        System.out.println(addList);
//                        // Clear the list for the next "add" command
//                        addList.clear();
//                    }
//                } else {
//                    Executor.addMovie(newAdd);
//                    // Execute other commands
//                    CommandManager.startNewAction(line);
//                    CommandManager.chosenScanner = new Scanner(System.in);
//                }
//
//            }