package Entities;

import database.DATABASE;
import java.io.Serializable;
import java.time.LocalDate;

public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    private DATABASE dataController;
    private RegisteredUser currentRegisteredUser;
    private OrdinaryUser currentOrdinaryUser;

    public Login() {
        dataController = DATABASE.getInstance(); // Assuming singleton pattern for ControlDB
    }

    public void ordinaryUser() {
        currentOrdinaryUser = new OrdinaryUser("Guest", "guestUser");
        currentRegisteredUser = null;
    }

    public RegisteredUser loginVerification(String username, String password) {
        for (RegisteredUser user : dataController.getListOfUsers()) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                currentRegisteredUser = user;
                currentOrdinaryUser = null;
                return user;
            }
        }
        return null;
    }

    public void logoutUser() {
        currentOrdinaryUser = null;
        currentRegisteredUser = null;
    }

    // User Registration now matches the constructor in RegisteredUser class
    public void userRegistration(String name, String username, String password, String email, String address, String cardNumber) {
        if (!checkIfUserExists(username)) {
            // No need to create a BankCardInfo instance as the constructor expects cardNumber directly
            RegisteredUser newUser = new RegisteredUser(name, username, password, email, address, cardNumber);
            dataController.getListOfUsers().add(newUser);
        }
    }

    public boolean checkIfUserExists(String username) {
        return dataController.getListOfUsers().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public RegisteredUser getCurrentRegisteredUser() {
        return currentRegisteredUser;
    }

    public void setCurrentRegisteredUser(RegisteredUser currentRegisteredUser) {
        this.currentRegisteredUser = currentRegisteredUser;
    }

    public OrdinaryUser getCurrentOrdinaryUser() {
        return currentOrdinaryUser;
    }

    public void setCurrentOrdinaryUser(OrdinaryUser currentOrdinaryUser) {
        this.currentOrdinaryUser = currentOrdinaryUser;
    }

    public DATABASE getDataController() {
        return dataController;
    }

    public User getCurrentUser() {
        return currentRegisteredUser != null ? currentRegisteredUser : currentOrdinaryUser;
    }
}
