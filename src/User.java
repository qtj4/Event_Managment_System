import java.util.*;

public class User {
    private String password;//encrypted
    private String phoneNumber;
    private String emailAddress;
    private String fullName;

    private double balance;


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
}
