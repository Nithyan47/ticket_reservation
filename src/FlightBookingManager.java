import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightBookingManager {
    private static final int totalTickets = 150;
    private static String airlineName = "cgh Airlines Reservations";
    private static int remainingTickets = 150;

    private List<UserData> ticketBookings = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void runBookingApp() {
        greetUsers();

        while (remainingTickets > 0) {
            // take user input for multiple travelers
            List<UserData> userDataList = getUserInput();

            // process each traveler's details
            for (UserData userData : userDataList) {
                // validation
                boolean isValidName = userData.getFirstName().length() > 2 && userData.getLastName().length() > 2;
                boolean isValidEmail = userData.getEmail().contains("@");
                boolean isValidTicketNumber = userData.getNumberOfTickets() > 0 && userData.getNumberOfTickets() <= remainingTickets;

                if (isValidName && isValidEmail && isValidTicketNumber) {
                    // book flight ticket
                    bookTicket(userData);
                    // calling sendTicket function
                    sendTicket(userData);
                } else {
                    if (!isValidName) {
                        System.out.println("First name or last name you entered is too short.");
                    }
                    if (!isValidEmail) {
                        System.out.println("Email address you entered doesn't contain @ sign.");
                    }
                    if (!isValidTicketNumber) {
                        System.out.println("Number of tickets you entered is invalid.");
                    }
                }
            }

            // call function print first names
            List<String> firstNames = getFirstNames();
            System.out.printf("The first names of bookings are %s%n", firstNames);

            if (remainingTickets == 0) {
                // end the program
                System.out.println("Our flight tickets are fully booked at the moment. Please check back next time for availability.");
                break;
            }
        }
    }

    private void greetUsers() {
        System.out.printf("Welcome to %s%n", airlineName);
        System.out.printf("We have a total of %d flight tickets available and %d are still open for booking%n", totalTickets, remainingTickets);
        System.out.println("Book your flight tickets here to embark on your journey");
    }

    private List<String> getFirstNames() {
        List<String> firstNames = new ArrayList<>();

        for (UserData booking : ticketBookings) {
            firstNames.add(booking.getFirstName());
        }
        return firstNames;
    }

    private List<UserData> getUserInput() {
        System.out.println("Enter the total number of travelers ");
        int totalTravelers = scanner.nextInt();
    
        List<UserData> userDataList = new ArrayList<>();
    
        for (int i = 1; i <= totalTravelers; i++) {
            System.out.println("Enter details for traveler " + i + ":");
            
            System.out.println("First name");
            String firstName = scanner.next();
            
            System.out.println("Last name");
            String lastName = scanner.next();
            
            System.out.println("Email");
            String email = scanner.next();
    
            userDataList.add(new UserData(firstName, lastName, email, 1)); // Assuming 1 ticket per traveler
        }
    
        return userDataList;
    }
    
    
    
    

    private void bookTicket(UserData userData) {
        remainingTickets -= userData.getNumberOfTickets();
        ticketBookings.add(userData);

        System.out.printf("Thank you, %s %s, for booking %d flight ticket(s). You will receive a confirmation email at %s%n",
                userData.getFirstName(), userData.getLastName(), userData.getNumberOfTickets(), userData.getEmail());
        System.out.printf("%d flight tickets remaining for %s%n", remainingTickets, airlineName);
    }

    private void sendTicket(UserData userData) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ticketArt = generateTicketArt(userData);

        System.out.println("\n" + ticketArt);
    }

    private String generateTicketArt(UserData userData) {
        StringBuilder ticketArt = new StringBuilder();

        ticketArt.append("Flight Ticket Details:\n");
        ticketArt.append(String.format("%d flight ticket(s) for %s %s%n", userData.getNumberOfTickets(), userData.getFirstName(), userData.getLastName()));
        ticketArt.append(String.format("To email address %s%n", userData.getEmail()));

        return ticketArt.toString();
    }

    public static void main(String[] args) {
        FlightBookingManager bookingManager = new FlightBookingManager();
        bookingManager.runBookingApp();
    }
}
