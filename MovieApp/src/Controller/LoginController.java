package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private MySQLConnection connection;

    // Constructor
    public LoginController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to validate user credentials and get role
    public int validateUser(String email, String password) {
        String query = "SELECT registered FROM users WHERE email = ? AND password = ?";
        ResultSet result = connection.query(query, email, password);

        try {
            if (result.next()) {
                return result.getInt("registered"); // Return the user's role
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if login fails
    }

    public boolean checkAnnualFee(String email) {
        String query = "SELECT annualFeeDue FROM users WHERE email = ?";
        ResultSet results = connection.query(query, email);
        try {
            if (results.next()) {
                return results.getInt("annualFeeDue") == 1; // Returns true if fee is due
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Fee is not due or no such user
    }

}
