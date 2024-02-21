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


## Credits and Acknowledgments
We would like to thank the following individuals and organizations for their contributions to the Event Ticketing System:

Temirlan - Project Lead

Galymzhan, Arailym, Zhansaya and Nurlybek - Additional development and testing
