import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public static void saveUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection();
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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email_address = ?")) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        rs.getString("email_address"),
                        rs.getString("password"),
                        rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}

