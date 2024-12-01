package Controller;

import java.sql.*;

public class UserController {

    private MySQLConnection connection;
    private EmailController emailController;

    // Constructor
    public UserController() {
        connection = MySQLConnection.getInstance();
        emailController = new EmailController();
    }

    // Method to send announcements to registered users who have an annual fee due
    public void sendMovieNewsToRegisteredUsers() {
        String query = "SELECT email FROM users WHERE registered = 1 AND annualFeeDue = 1";
        ResultSet result = connection.query(query);
        
        try {
            while (result.next()) {
                String email = result.getString("email");
                
                // The announcement message
                String subject = "Exclusive Movie News: Early Access";
                String body = "Dear Registered User,\n\n"
                        + "As a Registered User, you are receiving exclusive early access to the latest movie news:\n"
                        + "- No 15% admin fee for cancelling your tickets.\n"
                        + "- You will receive movie news before the public announcement.\n\n"
                        + "Thank you for being a valued member!\n\n"
                        + "Best Regards,\n"
                        + "Your Movie Theatre Team";
                
                // Send email to the user
                emailController.sendEmail(email, subject, body);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
