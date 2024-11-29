package Entities;

public class DatabaseAdmin extends RegisteredUser {
    // Attributes specific to DatabaseAdmin
    private int adminID;

    // Constructor
    public DatabaseAdmin(Name name, String username, String password, String email, String address, BankCardInfo paymentInfo, int adminID) {
        super(name, username, password, email, address, paymentInfo);
        this.adminID = adminID;
    }

    // Getter for adminID
    public int getadminID() {
        return adminID;
    }

    // Setter for adminID
    public void setadminID(int adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "DatabaseAdmin{" +
               "name='" + getName() + '\'' +
               ", username='" + getUsername() + '\'' +
               ", email='" + getEmail() + '\'' +
               ", adminID='" + adminID + '\'' +
               '}';
    }
}
