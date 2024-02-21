import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to our Event Management System!");
        UserAuthentication userAuth = new UserAuthentication();
        EventManagement eventMgmt = new EventManagement();
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        boolean isLoggedIn = false;
        while (true) {
            if (!isLoggedIn) {
                System.out.println("Please choose an option:\n1. Sign Up\n2. Login\n3. Exit\n");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        user = userAuth.signUp();
                        isLoggedIn = true;
                        System.out.println("\n");
                        break;
                    case 2:
                        user = userAuth.logIn();
                        if(user != null){
                            isLoggedIn = true;
                            System.out.println("\n");
                        } else {
                            System.out.println("Login failed. Please try again.\n");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!\n");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } else {
                System.out.println("Please choose an option:\n1. Show All Events\n2. Show Events by Type\n3. Show balance\n4. Buy Ticket\n5. Cancel Order\n6. Top Up balance\n7. Log out\n8. Exit \n");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("All events:");
                        eventMgmt.showAllEvents();
                        System.out.println("\n");
                        break;

                    case 2:
                        System.out.println("Showing events by type:");
                        String eventType = scanner.next();
                        eventMgmt.showEventsByType(eventType);
                        System.out.println("\n");
                        break;

                    case 3:
                        System.out.println("Your current balance is: " + user.getBalance() + "\n");
                        break;

                    case 4:
                        System.out.println("Please enter the event ID to buy a ticket:");
                        int eventId = scanner.nextInt();
                        user.buyTicket(eventId);
                        System.out.println("\n");
                        break;

                    case 5:
                        System.out.println("Please enter the event ID to cancel the order:");
                        int eventID = scanner.nextInt();
                        user.cancelOrder(eventID);
                        System.out.println("\n");
                        break;

                    case 6:
                        System.out.println("Please enter the amount to top up:");
                        int amount = scanner.nextInt();
                        user.topUpBalance(amount);
                        System.out.println("Your balance has been topped up by " + amount + ". Your new balance is: " + user.getBalance() + "\n");
                        break;

                    case 7:
                        System.out.println("Logging out. See you next time!\n");
                        isLoggedIn = false;
                        break;

                    case 8:
                        System.out.println("Exiting the system. Goodbye!\n");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            }
        }
    }
}
