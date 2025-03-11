import java.sql.*;
import java.util.Scanner;

public class MenuDrivenProgram {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            int choice;
            do {
                System.out.println("\nMENU:");
                System.out.println("1. Insert Row");
                System.out.println("2. Update Row");
                System.out.println("3. Delete Row");
                System.out.println("4. Select Row");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertRow(conn, scanner);
                        break;
                    case 2:
                        updateRow(conn, scanner);
                        break;
                    case 3:
                        deleteRow(conn, scanner);
                        break;
                    case 4:
                        selectRow(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRow(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Row inserted successfully.");
        }
    }

    private static void updateRow(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();

        String sql = "UPDATE students SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Row updated successfully.");
            else
                System.out.println("No matching record found.");
        }
    }

    private static void deleteRow(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Row deleted successfully.");
            else
                System.out.println("No matching record found.");
        }
    }

    private static void selectRow(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to fetch details: ");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            } else {
                System.out.println("No matching record found.");
            }
        }
    }
}