package Controller;

import java.sql.ResultSet;

public class SignUpController {

    private MySQLConnection connection;

    // Constructor
    public SignUpController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to add a new user to the database
    public boolean addRegUser(String name, String email, String pass) {
        // Check if the user already exists
        String searchQuery = "SELECT * FROM users WHERE email = ?";
        ResultSet results = connection.query(searchQuery, email);
        boolean temp = false;

        try {
            if (results.next()) {
                temp = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the user does not exist, add them to the database
        if (!temp) {
            String query = "INSERT INTO users (email, registered, password, name, annualFeeDue) VALUES (?,?,?,?,?)";
            connection.execute(query, email, 1, pass, name, 0);
            return true;
        }

        return false;
    }

    // Method to add new user's card to the database
    public boolean AddRegCard(String cardNumber, String CVV, String expiryDate, String email) {
        // Get user_id based on email
        String searchQuery = "SELECT user_id FROM users WHERE email = ?";
        ResultSet results = connection.query(searchQuery, email);
        try {
            if (results.next()) {
                int userId = results.getInt("user_id");
                String query = "INSERT INTO cards (card_number, expiry_date, cvv, user_id) VALUES (?,?,?,?)";
                connection.execute(query, cardNumber, expiryDate, CVV, userId);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to remove user from the database
    public void removeUser(String email) {
        String query = "DELETE FROM users WHERE email = ?";
        connection.execute(query, email);
    }

    // Method to pay the annual fee (updates in the user database)
    public void payAnnualFee(String email) {
        String query = "UPDATE users SET annualFeeDue = ? WHERE email = ?";
        connection.execute(query, 0, email);
    }
}