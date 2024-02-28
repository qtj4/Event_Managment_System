import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class TransactionManager {
    private static Map<String, List<Integer>> myTickets = new HashMap<>();
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qazwsx123";
    private static final String ADMIN_PASSWORD = "admin123";


    public void topUpBalance(double amount, User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            user.setBalance(user.getBalance() + amount);
            String sql = "UPDATE users SET balance = ? WHERE email_address = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, user.getBalance());
            statement.setString(2, user.getEmailAddress());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void buyTicket(int eventId, User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM events WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double ticketPrice = resultSet.getDouble("ticket_price");
                int ticketsAmount = resultSet.getInt("tickets_amount");

                if (user.getBalance() < ticketPrice) {
                    System.out.println("Not enough balance");
                } else if (ticketsAmount <= 0) {
                    System.out.println("No tickets available");
                } else {
                    user.setBalance(user.getBalance() - ticketPrice);

                    sql = "UPDATE users SET balance = ? WHERE email_address = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setDouble(1, user.getBalance());
                    statement.setString(2, user.getEmailAddress());
                    statement.executeUpdate();


                    sql = "UPDATE events SET tickets_amount = ? WHERE id = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, ticketsAmount - 1);
                    statement.setInt(2, eventId);
                    statement.executeUpdate();

                    System.out.println("Ticket bought successfully!");
                    System.out.println(EventRepository.getEventById(eventId));
                    List<Integer> userTickets = myTickets.getOrDefault(user.getEmailAddress(), new ArrayList<>());
                    userTickets.add(eventId);
                    myTickets.put(user.getEmailAddress(), userTickets);
                }
            } else {
                System.out.println("Event not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOrder(int eventId, User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM events WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();

            if (myTickets.get(user.getEmailAddress()) != null && !myTickets.get(user.getEmailAddress()).isEmpty() && myTickets.get(user.getEmailAddress()).contains(eventId)) {
                if (resultSet.next()) {
                    double ticketPrice = resultSet.getDouble("ticket_price");
                    int ticketsAmount = resultSet.getInt("tickets_amount");
                    user.setBalance(user.getBalance() + ticketPrice);

                    sql = "UPDATE users SET balance = ? WHERE email_address = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setDouble(1, user.getBalance());
                    statement.setString(2, user.getEmailAddress());
                    statement.executeUpdate();

                    sql = "UPDATE events SET tickets_amount = ? WHERE id = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, ticketsAmount + 1);
                    statement.setInt(2, eventId);
                    statement.executeUpdate();

                    // transactions in sql

                    List<Integer> userTickets = myTickets.get(user.getEmailAddress());
                    userTickets.remove(Integer.valueOf(eventId));
                    myTickets.put(user.getEmailAddress(), userTickets);

                    System.out.println("Your order has been cancelled successfully. Your balance has been updated.");
                } else {
                    System.out.println("Event not found. Please check the event ID and try again.");
                }

            } else {
                System.out.println("You did not buy a ticket for this event. Please check your orders and try again.");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while cancelling your order. Please try again later.");
            throw new RuntimeException(e);
        }
    }
}
