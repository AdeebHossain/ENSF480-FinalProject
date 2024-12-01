package Controller;

import java.util.*;
import javax.mail.*;

public class EmailController {

    private final String fromEmail = "your-email@example.com";  // Sender's email
    private final String emailPassword = "your-email-password"; // Sender's email password
    private final String smtpHost = "smtp.example.com"; // SMTP server (e.g., Gmail, Outlook)
    
    // Method to send an email
    public boolean sendEmail(String toEmail, String subject, String body) {
        try {
            // Set up email properties
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", smtpHost);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            
            // Get the default session
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, emailPassword);
                }
            });
            
            // Create the email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);
            
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
