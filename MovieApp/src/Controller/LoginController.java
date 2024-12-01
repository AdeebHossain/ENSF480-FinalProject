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
}
