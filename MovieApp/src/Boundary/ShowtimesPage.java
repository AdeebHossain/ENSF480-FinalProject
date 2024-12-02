package Boundary;

import Controller.SeatingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List; // This imports the List interface with generics
import java.util.ArrayList; // Import ArrayList for actual implementation


public class ShowtimesPage {

    // Use a global map to store seat states across popup openings
    private static final Map<String, Boolean> seatStates = new HashMap<>();
    private static int numTickets = 0;
    private static final SeatingController seatingController = new SeatingController();

    public static void show(String movieTitle, String[] showtimes, String movieDescription, ImageIcon movieImage, int movieLength) {
        JFrame frame = new JFrame("Showtimes - " + movieTitle + " (" + movieLength + " m)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        frame.add(mainPanel);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel imageLabel = new JLabel(movieImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Combine description and length
        JTextArea descriptionArea = new JTextArea(movieDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionArea.setPreferredSize(new Dimension(500, 300)); // Increase the description area size

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        topPanel.add(imageLabel);
        topPanel.add(descriptionScrollPane);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> showtimeDropdown = new JComboBox<>(showtimes);
        showtimeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        showtimeDropdown.setPreferredSize(new Dimension(150, 30));
        JLabel dropdownLabel = new JLabel("Select a Showtime:", JLabel.CENTER);
        dropdownLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        dropdownPanel.add(dropdownLabel);
        dropdownPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dropdownPanel.add(showtimeDropdown);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backButton = new JButton("Back to Movie Selection");
        JButton seatSelectionButton = new JButton("Proceed to Seat Selection");
        seatSelectionButton.setEnabled(false);

        JButton checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.addActionListener(e -> {
            if (numTickets == 0) {
                JOptionPane.showMessageDialog(frame, "No tickets selected. Please select your seats first.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                SwingUtilities.invokeLater(() -> new PaymentGUI(movieTitle, numTickets, 12.50));
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(seatSelectionButton);
        buttonPanel.add(checkoutButton);

        bottomPanel.add(dropdownPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        backButton.addActionListener(e -> {
            frame.dispose();
            FrontPage.main(null);
        });

        seatSelectionButton.addActionListener(e -> openSeatSelectionFrame(frame));

        showtimeDropdown.addActionListener(e -> {
            if (showtimeDropdown.getSelectedIndex() != -1) {
                seatSelectionButton.setEnabled(true);
            }
        });
    }

    private static void openSeatSelectionFrame(JFrame parentFrame) {
        JFrame seatFrame = new JFrame("Select Your Seats");
        seatFrame.setSize(800, 600);
        seatFrame.setLayout(new BorderLayout());
    
        // Load seat and screen icons
        ImageIcon availableIcon = new ImageIcon("../data/available.png");
        ImageIcon occupiedIcon = new ImageIcon("../data/occupied.png");
        ImageIcon selectedIcon = new ImageIcon("../data/selected.png");
        ImageIcon screenIcon = new ImageIcon("../data/screen.png");
    
        // Add the screen icon at the top
        JLabel screenLabel = new JLabel(screenIcon, JLabel.CENTER);
        seatFrame.add(screenLabel, BorderLayout.NORTH);
    
        // Create the seat grid
        JPanel seatGrid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); // Reduce gaps between buttons
    
        JPanel confirmationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel selectedSeatsLabel = new JLabel("Selected Seats: ");
        JButton confirmSeatsButton = new JButton("Confirm Seats");
    
        confirmationPanel.add(selectedSeatsLabel);
        confirmationPanel.add(confirmSeatsButton);
    
        StringBuilder selectedSeats = new StringBuilder();
    
        // Fetch seat availability
        List<String[]> seatAvailability = seatingController.getSeatsAvailability();
    
        // Dynamically create seat buttons based on availability
        for (String[] seat : seatAvailability) {
            String row = seat[0];
            String col = seat[1];
            String status = seat[2];
            String seatLabel = row + col;
    
            // Determine icon based on status
            ImageIcon currentIcon = "Reserved".equals(status) ? occupiedIcon : availableIcon;
    
            JButton seatButton = new JButton(currentIcon);
            seatButton.setPreferredSize(new Dimension(50, 35)); // Adjust seat size if needed
            seatButton.setBorder(BorderFactory.createEmptyBorder());
            seatButton.setContentAreaFilled(false);
    
            // Restore previously selected seats
            if (seatStates.getOrDefault(seatLabel, false)) {
                seatButton.setIcon(selectedIcon);
                selectedSeats.append(seatLabel).append(" ");
                numTickets++;
            }
    
            seatButton.addActionListener(seatEvent -> {
                if (seatButton.getIcon() == availableIcon) {
                    seatButton.setIcon(selectedIcon);
                    seatStates.put(seatLabel, true);
                    selectedSeats.append(seatLabel).append(" ");
                    selectedSeatsLabel.setText("Selected Seats: " + selectedSeats);
                    numTickets++;
                } else if (seatButton.getIcon() == selectedIcon) {
                    seatButton.setIcon(availableIcon);
                    seatStates.put(seatLabel, false);
                    int index = selectedSeats.indexOf(seatLabel);
                    if (index >= 0) {
                        selectedSeats.delete(index, index + seatLabel.length() + 1);
                    }
                    selectedSeatsLabel.setText("Selected Seats: " + selectedSeats);
                    numTickets--;
                }
            });
    
            gbc.gridx = Integer.parseInt(col) - 1; // Columns are 1-indexed
            gbc.gridy = row.charAt(0) - 'A'; // Convert row char to zero-based index
    
            seatGrid.add(seatButton, gbc);
        }
    
        confirmSeatsButton.addActionListener(confirmEvent -> {
            if (selectedSeats.length() == 0) {
                JOptionPane.showMessageDialog(seatFrame, "No seats selected. Please select at least one seat.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(seatFrame, "Seats confirmed: " + selectedSeats, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                seatFrame.dispose();
            }
        });
    
        seatFrame.add(seatGrid, BorderLayout.CENTER);
        seatFrame.add(confirmationPanel, BorderLayout.SOUTH);
    
        seatFrame.setVisible(true);
    }
    
}
