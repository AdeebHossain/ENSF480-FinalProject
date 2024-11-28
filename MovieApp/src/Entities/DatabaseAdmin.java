package Entities;

public class DatabaseAdmin extends RegisteredUser {
    // Attributes specific to DatabaseAdmin
    private String adminLevel;

    // Constructor
    public DatabaseAdmin(String name, String username, String password, String email, String address, String cardNumber, String adminLevel) {
        super(name, username, password, email, address, cardNumber);
        this.adminLevel = adminLevel;
    }

    // Getter for adminLevel
    public String getAdminLevel() {
        return adminLevel;
    }

    // Setter for adminLevel
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @Override
    public String toString() {
        return "DatabaseAdmin{" +
               "name='" + getName() + '\'' +
               ", username='" + getUsername() + '\'' +
               ", email='" + getEmail() + '\'' +
               ", adminLevel='" + adminLevel + '\'' +
               '}';
    }
}
