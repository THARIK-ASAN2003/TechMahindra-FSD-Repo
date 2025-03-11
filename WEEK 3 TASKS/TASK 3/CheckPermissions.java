import java.io.File;

public class CheckPermissions {
    public static void main(String[] args) {
        String pathname = "C:/VS Code Programs/Java Programs /techmahindra/
Week 3 Assignments /Task 3/MyDetails.txt";
        File file = new File(pathname);
        System.out.println("Read permission: " + file.canRead());
        System.out.println("Write permission: " + file.canWrite());
    }
}
