import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRepository {
    public static Event getEventById(int eventId) {
        Event event = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM events WHERE id = ?")) {

            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                event = new Event(
                        rs.getString("title"),
                        rs.getString("data"),
                        rs.getString("location"),
                        rs.getDouble("ticket_price"),
                        rs.getInt("tickets_amount"),
                        rs.getString("event_type"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }
}
