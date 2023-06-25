package cinema;

import java.util.Scanner;
import java.util.*;

public class Cinema {

    private static char[][] seats;
    private static int currentIncome = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        // Initializing the seating arrangement
        seats = new char[numRows][numSeats];
        initializeSeats();

        int choice;
        do {
            // Displaying the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.print("> ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket(scanner);
                    break;
                case 3:
                    getStatistics();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } while (choice != 0);
    }

    // Method to initialize the seats with 'S' (empty)
    private static void initializeSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 'S';
            }
        }
    }

    // Method to display the seating arrangement
    private static void showSeats() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to buy a ticket
    private static void buyTicket(Scanner scanner) {
        System.out.println("\nEnter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        if (row < 1 || row > seats.length || seat < 1 || seat > seats[0].length) {
            System.out.println("Wrong input");
            buyTicket(scanner);
        } else if (seats[row - 1][seat - 1] == 'B') {
            System.out.println("That ticket has already been purchased");
            buyTicket(scanner);
        } else {
            seats[row - 1][seat - 1] = 'B';
            int ticketPrice = calculateTicketPrice(row);
            currentIncome += ticketPrice;
            System.out.println("Ticket price: $" + ticketPrice);
        }
    }

    // Method to calculate the ticket price based on the row
    private static int calculateTicketPrice(int row) {
        int totalSeats = seats.length * seats[0].length;
        int frontHalfRows = seats.length / 2;
        int ticketPrice;
        if (totalSeats <= 60 || row <= frontHalfRows) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    private static void getStatistics() {
        int ticketsSold = 0;
        double totalSeatsAvailable = 0.00;
        double percentageSold = 0.00;

        //Counts tickets sold and that are available
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if(seats[i][j] == 'B'){
                    ticketsSold++;
                }else if(seats[i][j] == 'S'){
                    totalSeatsAvailable++;
                }
            }
        }
        percentageSold = ticketsSold / (totalSeatsAvailable + ticketsSold) * 100;
        System.out.println("Number of purchased tickets: " + ticketsSold + "\nPercentage: " + String.format("%.2f", percentageSold) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateTotalSeatValue());
    }

    // method to calculate total seat value
    private static int calculateTotalSeatValue() {
        int totalValue = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                int ticketPrice = calculateTicketPrice(i + 1);
                totalValue += ticketPrice;
            }
        }
        return totalValue;
    }
}


