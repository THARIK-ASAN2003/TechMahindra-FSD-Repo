import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckPathExists {
    public static void main(String[] args) {
        String pathname = "C:/VS Code Programs/Java Programs /techmahindra/
Week 3 Assignments /Task 3/MyDetails.txt";
        Path path = Paths.get(pathname);
        boolean exists = Files.exists(path);
        System.out.println("Path " + pathname + " exists: " + exists);
    }
}
