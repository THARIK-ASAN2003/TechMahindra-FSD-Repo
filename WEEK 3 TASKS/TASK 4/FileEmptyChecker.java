import java.io.File;
import java.io.IOException;

public class FileEmptyChecker {
    public static void checkIfFileIsEmpty(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.length() == 0) {
            throw new IOException("The file is empty: " + filePath);
        }
        System.out.println("The file is not empty: " + filePath);
    }

    public static void main(String[] args) {
        try {
            checkIfFileIsEmpty("empty_file.txt"); // Change this to a valid file path to test
        } catch (IOException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}