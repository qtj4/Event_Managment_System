import java.util.*;
public class UserAuthentication {

    private static final String SECRET_KEY = "sfhu*90&0-3nICh!";
    private static Scanner scanner = new Scanner(System.in);

    public static User signUp() {
        System.out.println("Enter your full name -> ");
        String fullName = scanner.nextLine().strip();

        System.out.println("Enter your phone number -> ");
        String phoneNumber = scanner.next().strip();
        while (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            System.out.println("Enter valid phone number (11 or 12 digits) -> ");
            phoneNumber = scanner.next().strip();
        }

        System.out.println("Enter your email -> ");
        String email_address = scanner.next().strip();
        while (!InputValidator.isValidEmail(email_address) || DatabaseConnection.getUserByEmail(email_address) != null) {
            System.out.println("Enter valid email (it should contain '@' and '.' and have to be unique) ->");
            email_address = scanner.next().strip();
        }


        System.out.println("Enter password -> ");
        String password = scanner.next().strip();
        while (!InputValidator.isValidPassword(password)) {
            System.out.println("Enter valid password (8-20 characters, at least 2 uppercase, 2 lowercase, 2 digits, and 2 special characters) -> ");
            password = scanner.next().strip();
        }
        String encryptedPassword = AESCipher.encrypt(password, SECRET_KEY);

        System.out.println("Enter your balance -> ");
        double balance = scanner.nextDouble();
        User user = new User(fullName, phoneNumber, email_address, encryptedPassword, balance);

        DatabaseConnection.saveUserToDatabase(user);

        System.out.println("Congratulations, you have successfully registered");
        return user;
    }

    public static User logIn() {
        System.out.println("Enter your email -> ");
        String email = scanner.next().strip();

        System.out.println("Enter password -> ");
        String password = scanner.next().strip();

        User user = DatabaseConnection.getUserByEmail(email);

        if (user != null) {
            String encryptedPassword = user.getPassword();
            String decryptedPassword = AESCipher.decrypt(encryptedPassword, SECRET_KEY);

            if (password.equals(decryptedPassword)) {
                System.out.println("Login successful!");
                return user;
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("No user found with that email.");
        }
        return null;
    }

}
