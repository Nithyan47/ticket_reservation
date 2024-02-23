public class UserData {
    
    private String firstName;
    private String lastName;
    private String email;
    private int numberOfTickets;

    public UserData(String firstName, String lastName, String email, int numberOfTickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numberOfTickets = numberOfTickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}
