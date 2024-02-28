# Project Documentation: Event Ticketing System
## Introduction
Welcome to the documentation for the Event Ticketing System project. This document aims to provide comprehensive information about the system's architecture, functionality, setup, and usage.

## Aim of this project
The aim of the Event Ticketing System project is to provide a comprehensive solution for managing ticket sales and event attendance. The system is designed to streamline the process of organizing events, selling tickets, and ensuring a smooth experience for both event organizers and attendees.
## How to use the system


## Functionality
1. Features
- Register a User
- Top up User's Balance
- Buy a Ticket
- Cancel Ticket Order


2. Usage

User Registration:

Create a new User object with the required details (full name, phone number, email address, password, and initial balance).
Use the saveUserToDatabase(User user) method from the DatabaseConnection class to register the user in the database.

Purchasing Tickets:

Retrieve the user's details from the database using their email address.
Use the buyTicket(int eventId) method to purchase a ticket for a specific event, providing the event ID.

Managing User Balance:

Use the topUpBalance(double amount) method to add funds to the user's balance.
Use the cancelOrder(int eventId) method to cancel a ticket order for a specific event.

## Security Considerations
The Event Ticketing System implements AES encryption to secure sensitive data such as user passwords and payment information. Additionally, user authentication and authorization mechanisms are implemented to prevent unauthorized access to the system.


## Design Patterns 

### Singleton Pattern for DatabaseConnection

#### How It Works
- The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. For `DatabaseConnection`, this means that no matter how many times you request a connection, the application will only ever create and use a single instance of the database connection.
- This is particularly useful for database connections, which are expensive to create, maintain, and destroy. By reusing the same connection, the system uses resources more efficiently and reduces overhead.

#### Benefits
- **Resource Management**: It optimizes the application's usage of database connections, minimizing the overhead associated with opening and closing connections.
- **Consistency**: With only one instance of the connection, you reduce the risk of errors due to inconsistent states between different connections.
- **Simplified Debugging and Maintenance**: Having a single pathway for database operations makes the system easier to debug and maintain.

### Repository Pattern for UserRepository and EventRepository

#### How It Works
- The Repository pattern abstracts the way data is stored, retrieved, and manipulated. `UserRepository` and `EventRepository` act as intermediaries between the business logic of our application and the database access code.
- These repositories encapsulate the logic required to access data sources, providing a clean API for the rest of your application to use without needing to know about the database schema or underlying data access technologies.

#### Benefits
- **Decoupling**: By separating data access logic from business logic, your application's components become more decoupled, leading to a system that's easier to manage and evolve.
- **Abstraction**: The Repository pattern hides the details of data access, making the rest of your application agnostic to the specifics of the database access code. This means you can change your database schema, switch databases, or refactor our data access layer without impacting the business logic.
- **Testability**: With data access logic abstracted away, testing components of our application becomes easier. We can mock repositories in unit tests, ensuring that tests are fast and reliable, focusing on the logic being tested rather than the database.

### Broader Implications

- **Maintainability**: As our application grows, these patterns help keep the codebase organized and manageable. Changes in business requirements or data models can be more easily accommodated with minimal impact on the overall system.
- **Scalability**: Efficient resource management and the ability to abstract and encapsulate data access make scaling your application simpler. Whether scaling vertically or horizontally, these patterns provide a solid foundation for growth.
- **Enhanced Collaboration**: With clearly defined patterns and responsibilities, new developers or teams can quickly understand the system's architecture, making it easier to contribute to the project without unintended side effects.

## Credits and Acknowledgments
We would like to thank the following individuals and organizations for their contributions to the Event Ticketing System:

Temirlan - Project Lead

Galymzhan, Arailym, Zhansaya and Nurlybek - Additional development and testing
