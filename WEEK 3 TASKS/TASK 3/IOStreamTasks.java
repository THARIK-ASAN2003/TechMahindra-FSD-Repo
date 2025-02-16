import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class IOStreamTasks {
    // Task 1: List all files/directories in a directory
    public static void listFilesAndDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        String[] contents = directory.list();
        if (contents != null) {
            System.out.println("Contents of directory: " + directoryPath);
            Arrays.stream(contents).forEach(System.out::println);
        } else {
            System.out.println("Directory does not exist or is empty");
        }
    }

    // Task 2: Get files with specific extension
    public static void getFilesByExtension(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        String[] files = directory.list((dir, name) -> name.toLowerCase().endsWith(extension.toLowerCase()));
        if (files != null) {
            System.out.println("Files with extension " + extension + ":");
            Arrays.stream(files).forEach(System.out::println);
        }
    }

    // Task 3: Check if file/directory exists
    public static boolean checkPathExists(String pathname) {
        Path path = Paths.get(pathname);
        boolean exists = Files.exists(path);
        System.out.println("Path " + pathname + " exists: " + exists);
        return exists;
    }

    // Task 4: Check read/write permissions
    public static void checkPermissions(String pathname) {
        File file = new File(pathname);
        System.out.println("Read permission: " + file.canRead());
        System.out.println("Write permission: " + file.canWrite());
    }

    // Task 5: Check if path is directory or file
    public static void checkPathType(String pathname) {
        File path = new File(pathname);
        if (path.isDirectory()) {
            System.out.println(pathname + " is a directory");
        } else if (path.isFile()) {
            System.out.println(pathname + " is a file");
        } else {
            System.out.println(pathname + " is neither a file nor a directory");
        }
    }

    // Task 6: Get last modified date
    public static void getLastModifiedDate(String pathname) {
        File file = new File(pathname);
        Date lastModified = new Date(file.lastModified());
        System.out.println("Last modified: " + lastModified);
    }

    // Task 7: Read from console
    public static void readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text (press Enter to finish):");
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);
    }

    // Task 8: Get file size
    public static void getFileSize(String pathname) {
        File file = new File(pathname);
        long bytes = file.length();
        double kb = bytes / 1024.0;
        double mb = kb / 1024.0;
        System.out.printf("File size:%n%d bytes%n%.2f KB%n%.2f MB%n", bytes, kb, mb);
    }

    // Task 9: Read file to byte array
    public static byte[] readFileToByteArray(String pathname) throws IOException {
        Path path = Paths.get(pathname);
        return Files.readAllBytes(path);
    }

    // Task 10: Read file line by line
    public static void readFileLineByLine(String pathname) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathname))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 11: Read plain text file
    public static String readPlainTextFile(String pathname) {
        StringBuilder content = new StringBuilder();
        try {
            content.append(Files.readString(Paths.get(pathname)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        String directoryPath = "C:/example";
        String filePath = "C:/example/test.txt";

        // Example usage of each method
        System.out.println("=== Task 1: List Files and Directories ===");
        listFilesAndDirectories(directoryPath);

        System.out.println("\n=== Task 2: Get Files by Extension ===");
        getFilesByExtension(directoryPath, ".txt");

        System.out.println("\n=== Task 3: Check Path Exists ===");
        checkPathExists(filePath);

        System.out.println("\n=== Task 4: Check Permissions ===");
        checkPermissions(filePath);

        System.out.println("\n=== Task 5: Check Path Type ===");
        checkPathType(filePath);

        System.out.println("\n=== Task 6: Get Last Modified Date ===");
        getLastModifiedDate(filePath);

        System.out.println("\n=== Task 7: Read from Console ===");
        readFromConsole();

        System.out.println("\n=== Task 8: Get File Size ===");
        getFileSize(filePath);

        System.out.println("\n=== Task 9: Read File to Byte Array ===");
        try {
            byte[] bytes = readFileToByteArray(filePath);
            System.out.println("Bytes read: " + bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Task 10: Read File Line by Line ===");
        readFileLineByLine(filePath);

        System.out.println("\n=== Task 11: Read Plain Text File ===");
        String content = readPlainTextFile(filePath);
        System.out.println("File content: " + content);
    }
}