import java.sql.*;
import java.util.Scanner;

public class UpdateRow {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}