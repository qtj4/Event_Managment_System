import java.util.*;

public class Event {
    private int id;
    private String title;
    private String date;
    private String eventType;
    private String location;
    private int amountOfTickets;
    private double ticketPrice;

    public Event(String title, String date, String location, double ticketPrice, int amountOfTickets, String eventType) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.ticketPrice = ticketPrice;
        this.amountOfTickets = amountOfTickets;
        this.eventType = eventType;
    }

    public Event() {
        this.title = "";
        this.date = "";
        this.location = "";
        this.ticketPrice = 0;
        this.amountOfTickets = 0;
        this.eventType = "";
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getEventType() {
        return eventType;
    }

    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", amountOfTickets=" + amountOfTickets +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
