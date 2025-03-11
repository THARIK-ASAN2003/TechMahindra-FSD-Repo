import java.util.Scanner;

public class ReadFromConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text (press Enter to finish):");
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);
    }
}