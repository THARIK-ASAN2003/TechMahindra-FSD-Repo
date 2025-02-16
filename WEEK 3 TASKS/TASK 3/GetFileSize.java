import java.io.File;

public class GetFileSize {
    public static void main(String[] args) {
        String pathname = "C:/example/test.txt";
        File file = new File(pathname);
        long bytes = file.length();
        double kb = bytes / 1024.0;
        double mb = kb / 1024.0;
        System.out.printf("File size:%n%d bytes%n%.2f KB%n%.2f MB%n", bytes, kb, mb);
    }
}