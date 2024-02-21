import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class User {
    private String password;//encrypted
    private String phoneNumber;
    private String emailAddress;
    private String fullName;

    private double balance;
    private static Map<String, List<Integer>> myTickets = new HashMap<>();
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qazwsx123";
    private static final String ADMIN_PASSWORD = "admin123";


    public User(String fullName, String phoneNumber, String emailAddress, String password, double balance) {
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
    }
    public User() {
        this.emailAddress = "";
        this.fullName = "";
        this.phoneNumber = "";
        this.password = "";
        this.balance = 0;
    }

    //setters

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //getters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }



    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void topUpBalance(double amount) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            setBalance(getBalance() + amount);
            String sql = "UPDATE users SET balance = ? WHERE email_address = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, getBalance());
            statement.setString(2, getEmailAddress());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void buyTicket(int eventId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM events WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double ticketPrice = resultSet.getDouble("ticket_price");
                int ticketsAmount = resultSet.getInt("tickets_amount");

                if (this.balance < ticketPrice) {
                    System.out.println("Not enough balance");
                } else if (ticketsAmount <= 0) {
                    System.out.println("No tickets available");
                } else {
                    setBalance(getBalance() - ticketPrice);

                    sql = "UPDATE users SET balance = ? WHERE email_address = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setDouble(1, getBalance());
                    statement.setString(2, getEmailAddress());
                    statement.executeUpdate();


                    sql = "UPDATE events SET tickets_amount = ? WHERE id = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, ticketsAmount - 1);
                    statement.setInt(2, eventId);
                    statement.executeUpdate();

                    System.out.println("Ticket bought successfully!");
                    List<Integer> userTickets = myTickets.getOrDefault(getEmailAddress(), new ArrayList<>());
                    userTickets.add(eventId);
                    myTickets.put(getEmailAddress(), userTickets);
                }
            } else {
                System.out.println("Event not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOrder(int eventId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM events WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();

            if (!myTickets.get(getEmailAddress()).isEmpty() && myTickets.get(getEmailAddress()).contains(eventId)) {
                if (resultSet.next()) {
                    double ticketPrice = resultSet.getDouble("ticket_price");
                    int ticketsAmount = resultSet.getInt("tickets_amount");
                    setBalance(getBalance() + ticketPrice);

                    sql = "UPDATE users SET balance = ? WHERE email_address = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setDouble(1, getBalance());
                    statement.setString(2, getEmailAddress());
                    statement.executeUpdate();

                    sql = "UPDATE events SET tickets_amount = ? WHERE id = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, ticketsAmount + 1);
                    statement.setInt(2, eventId);
                    statement.executeUpdate();

                    List<Integer> userTickets = myTickets.get(getEmailAddress());
                    userTickets.remove(Integer.valueOf(eventId));
                    myTickets.put(getEmailAddress(), userTickets);

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
