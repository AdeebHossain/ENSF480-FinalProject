package Boundary;

import Controller.TicketController;
import Controller.SeatingController;
import Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookingCancellation {

    private TicketController ticketController;
    private SeatingController seatingController;

    public BookingCancellation() {
        ticketController = new TicketController();
        seatingController = new SeatingController();

        JFrame frame = new JFrame("Booking Cancellation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Adjusted size
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Title label
        JLabel titleLabel = new JLabel("Booking Cancellation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(34, 34, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        // Ticket ID input and add button
        JTextField ticketIdField = new JTextField(20);
        ticketIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(34, 139, 34)); // Green button
        addButton.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(240, 248, 255)); // Match background
        inputPanel.add(ticketIdField);
        inputPanel.add(addButton);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(inputPanel, gbc);

        // Ticket ID list
        DefaultListModel<String> ticketListModel = new DefaultListModel<>();
        JList<String> ticketList = new JList<>(ticketListModel);
        ticketList.setFont(new Font("Arial", Font.PLAIN, 14));
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ticketList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(ticketList);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(scrollPane, gbc);

        // Buttons for ticket actions
        JButton viewButton = new JButton("View Ticket Info");
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(new Color(70, 130, 180)); // Steel blue
        viewButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Remove");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(220, 20, 60)); // Crimson
        deleteButton.setForeground(Color.WHITE);

        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(new Color(240, 248, 255)); // Match background
        actionPanel.add(viewButton);
        actionPanel.add(deleteButton);

        gbc.gridy = 3;
        frame.add(actionPanel, gbc);

        // Email entry and confirm button
        JTextField emailField = new JTextField("Enter Email", 20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton confirmButton = new JButton("Confirm Cancellation");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(34, 139, 34)); // Green button
        confirmButton.setForeground(Color.WHITE);

        JPanel confirmPanel = new JPanel(new FlowLayout());
        confirmPanel.setBackground(new Color(240, 248, 255)); // Match background
        confirmPanel.add(emailField);
        confirmPanel.add(confirmButton);

        gbc.gridy = 4;
        frame.add(confirmPanel, gbc);

        // Add button actions
        ArrayList<String> ticketIds = new ArrayList<>();
        addButton.addActionListener(e -> {
            String ticketId = ticketIdField.getText().trim();
            if (!ticketId.isEmpty() && !ticketIds.contains(ticketId)) {
                ticketIds.add(ticketId);
                ticketListModel.addElement(ticketId);
                ticketIdField.setText("");
            }
        });

        // Delete selected ticket ID from the list
        deleteButton.addActionListener(e -> {
            String selected = ticketList.getSelectedValue();
            if (selected != null) {
                ticketIds.remove(selected);
                ticketListModel.removeElement(selected);
            }
        });

        // View ticket information (fetch actual ticket data from database)
        viewButton.addActionListener(e -> {
            String selected = ticketList.getSelectedValue();
            if (selected != null) {
                try {
                    int ticketId = Integer.parseInt(selected);
                    String[] ticketInfo = ticketController.getTicketInfo(ticketId);
                    if (ticketInfo != null) {
                        String ticketDetails = String.format("Ticket Info:\nUser: %s\nSeats: %s\nPrice: $%s\nShowtime: %s",
                                ticketInfo[0], ticketInfo[1], ticketInfo[4], ticketInfo[5]);
                        JOptionPane.showMessageDialog(frame, ticketDetails, "Ticket Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ticket not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ticket ID format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Confirm cancellation logic
        confirmButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (!email.isEmpty() && !ticketIds.isEmpty()) {
                // Instantiate LoginController to calculate credit
                LoginController loginController = new LoginController();
                double creditPercentage = loginController.calculateCredit(email) * 100; // Get credit percentage

                for (String ticketIdStr : ticketIds) {
                    try {
                        int ticketId = Integer.parseInt(ticketIdStr);
                        // Remove the ticket from the database
                        if (ticketController.removeTicket(ticketId)) {
                            // Get ticket info to release the seat
                            String[] ticketInfo = ticketController.getTicketInfo(ticketId);
                            if (ticketInfo != null) {
                                int seatId = Integer.parseInt(ticketInfo[1]); // Seat ID
                                // Release the seat
                                seatingController.releaseSeat(seatId);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ticket ID: " + ticketIdStr);
                    }
                }

                // Show refund confirmation with calculated credit
                JOptionPane.showMessageDialog(frame,
                        String.format("Cancellation confirmed.\nRefund: %.0f%% credit sent to %s.", creditPercentage, email),
                        "Cancellation Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close window
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a valid email and select at least one ticket.");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookingCancellation::new);
    }
}
