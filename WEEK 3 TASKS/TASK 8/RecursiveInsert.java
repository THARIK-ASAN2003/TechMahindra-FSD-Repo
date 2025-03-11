import java.sql.*;
import java.util.Scanner;

public class RecursiveInsert {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter number of rows to insert: ");
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            insertRows(conn, scanner, n);
            System.out.println(n + " rows inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRows(Connection conn, Scanner scanner, int n) throws SQLException {
        if (n == 0) return;

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
        }

        insertRows(conn, scanner, n - 1);
    }
}