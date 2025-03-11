import java.sql.*;
import java.util.Scanner;

public class DeleteRow {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}