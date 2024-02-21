import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventManagement {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qazwsx123";

    private Connection connection;

    public EventManagement() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllEvents() {
        String sql = "SELECT * FROM events";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". \n" +
                        "event type: " + rs.getString("event_type") + ", \n" +
                        "title: " + rs.getString("title") + ", \n" +
                        "date: " + rs.getString("data") + ", \n" +
                        "location: " + rs.getString("location") + ", " +
                        "amounts of tickets: " + rs.getInt("tickets_amount") + ", \n" +
                        "ticket price: " + rs.getDouble("ticket_price") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showEventsByType(String eventType) {
        String sql = "SELECT * FROM events WHERE event_type = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, eventType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". \n" +
                        "event type: " + rs.getString("event_type") + ", \n" +
                        "title: " + rs.getString("title") + ", \n" +
                        "date: " + rs.getString("data") + ", \n" +
                        "location: " + rs.getString("location") + ", " +
                        "amounts of tickets: " + rs.getInt("tickets_amount") + ", \n" +
                        "ticket price: " + rs.getDouble("ticket_price") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


/*
//unused methods
 public void findEvent(Event event) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, event.getId());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("title"));
            } else {
                System.out.println("Event not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void addEvent(Event event) {
        String sql = "INSERT INTO events (id, event_type, data, location, title, tickets_amount, ticket_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, event.getId());
            pstmt.setString(2, event.getEventType());
            pstmt.setString(3, event.getDate());
            pstmt.setString(4, event.getLocation());
            pstmt.setString(5, event.getTitle());
            pstmt.setInt(6, event.getAmountOfTickets());
            pstmt.setDouble(7, event.getTicketPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeEvent(Event event) {
        String sql = "DELETE FROM events WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, event.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("This event not found in event list.");
        }
    }

*/

