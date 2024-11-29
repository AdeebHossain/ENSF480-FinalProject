package Entities;

public class OrdinaryUser extends User {
    // Attributes specific to OrdinaryUser
    private boolean isRegistered = false; // Flag to indicate whether the user is registered

    // Constructor
    public OrdinaryUser(Name name, String username) {
        super(name, username); // Call to parent constructor for name and username
    }

    // Getter for isRegistered
    public boolean isRegistered() {
        return isRegistered;
    }

    // Setter for isRegistered
    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
    public String toString() {
        return "OrdinaryUser{" +
               "name='" + getName() + '\'' +
               ", username='" + getUsername() + '\'' +
               ", isRegistered=" + isRegistered +
               '}';
    }
}
