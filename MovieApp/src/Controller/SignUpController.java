package Controller;

import java.sql.ResultSet;

public class SignUpController {
    
    private MySQLConnection connection;

    // Constructor
    public SignUpController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to add a new user to the database
    public boolean addRegUser(String name, String email, String pass){
        //retreive all current users 
        String searchQuery = "SELECT * FROM users";
        ResultSet results = connection.query(searchQuery);
        boolean temp = false;

        // Check if the user already exists
        try {
            while (results.next()) {
                if (results.getString("email").equals(email)) {
                    temp = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the user does not exist, add them to the database
        if (!temp) {
            String query = "INSERT INTO users (email, registered, password, name, annualFeeDue) VALUES (?,?,?,?)";
            connection.execute(query, email, 1, pass, name, 0);
            return true;
        }

        return false;
    }

    //Method to add new uer's card to the database
    public boolean AddRegCard(String name, String cardNumber, String CVV, String expiryDate){
        //retreive all current cards
        String searchQuery = "SELECT * FROM cards";
        ResultSet results = connection.query(searchQuery);
        boolean temp = false;

        // Check if the card already exists
        try {
            while (results.next()) {
                if (results.getString("cardNumber").equals(cardNumber)) {
                    temp = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the card does not exist, add it to the database
        if (!temp) {
            String query = "INSERT INTO cards ( card_number, expiry_date, cvv, user_id) VALUES (?,?,?,?,?)";
            connection.execute(query, name, cardNumber, CVV, expiryDate);
            return true;
        }

        return false;
    }

    //Method to remove user from the database
    public void removeUser(String email){
        String query = "DELETE FROM users WHERE email = ?";
        connection.execute(query, email);
    }

    //Method to pay the annual fee (updates in the user database)
    public void payAnnualFee(String email){
        String query = "UPDATE users SET annualFeeDue = ? WHERE email = ?";
        connection.execute(query, 0, email);
    }
}
