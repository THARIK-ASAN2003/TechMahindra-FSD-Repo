import java.io.File;
import java.util.Arrays;

public class GetFilesByExtension {
    public static void main(String[] args) {
        String directoryPath = "C:/example";
        String extension = ".txt";
        File directory = new File(directoryPath);
        String[] files = directory.list((dir, name) -> name.toLowerCase().endsWith(extension.toLowerCase()));
        if (files != null) {
            System.out.println("Files with extension " + extension + ":");
            Arrays.stream(files).forEach(System.out::println);
        }
    }
}