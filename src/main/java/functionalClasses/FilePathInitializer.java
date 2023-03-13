package functionalClasses;

public class FilePathInitializer {
    private static String filePath;
    public static void formPath(String[] args) {
        filePath = args[0];
    }

    public static String getPath(){
        return filePath;
    }

}
