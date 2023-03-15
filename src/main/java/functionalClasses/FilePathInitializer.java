package functionalClasses;

/**
 The FilePathInitializer class provides methods to initialize and get the file path.
 */
public class FilePathInitializer {

    private static String filePath;

    /**
     Initializes the file path with the command line argument.
     @param args the command line arguments passed to the program
     */
    public static void formPath(String[] args) {
        filePath = args[0];
    }

    /**
     Returns the file path.
     @return the file path
     */
    public static String getPath(){
        return filePath;
    }

}
