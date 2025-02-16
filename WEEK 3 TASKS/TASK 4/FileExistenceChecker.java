import java.io.File;
import java.io.FileNotFoundException;

public class FileExistenceChecker {
    public static void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        System.out.println("File found: " + filePath);
    }

    public static void main(String[] args) {
        try {
            readFile("non_existent_file.txt"); // Change this to a valid file path to test
        } catch (FileNotFoundException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}