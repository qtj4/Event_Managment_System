import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qazwsx123";
    private static final String ADMIN_PASSWORD = "admin123";

    public static void saveUserToDatabase(User user) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (full_name, phone_number, email_address, password, balance) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getPhoneNumber());
            pstmt.setString(3, user.getEmailAddress());
            pstmt.setString(4, user.getPassword());
            pstmt.setDouble(5, user.getBalance());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByEmail(String email) {
        User user = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email_address = ?")) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String phoneNumber = rs.getString("phone_number");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");

                user = new User(fullName, phoneNumber, email, password, balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
