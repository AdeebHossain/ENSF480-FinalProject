package Boundary;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MovieGUI {

    private final Map<String, Boolean> seatStates = new HashMap<>();
    private int numTickets = 0; // Track number of tickets

    public MovieGUI() {
        // Main JFrame setup
        JFrame frame = new JFrame("Movie Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel for movie selection
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel movieLabel = new JLabel("Select a Movie:", SwingConstants.CENTER);
        String[] movies = {"Movie 1", "Movie 2", "Movie 3"};
        JComboBox<String> movieDropdown = new JComboBox<>(movies);

        // Panel for showtime selection
        JPanel showtimePanel = new JPanel();
        showtimePanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel showtimeLabel = new JLabel("Select a Showtime:", SwingConstants.CENTER);
        String[] showtimes = {"12:00 PM", "3:00 PM", "6:00 PM", "9:00 PM"};
        JComboBox<String> showtimeDropdown = new JComboBox<>(showtimes);
        showtimePanel.setVisible(false);

        // Panel for seat selection and confirmation
        JPanel seatPanel = new JPanel();
        JButton selectSeatButton = new JButton("Select Seats");
        JButton confirmSeatButton = new JButton("Proceed to Payment");
        seatPanel.add(selectSeatButton);
        seatPanel.add(confirmSeatButton);
        seatPanel.setVisible(false);

        // Add components to moviePanel
        moviePanel.add(movieLabel);
        moviePanel.add(movieDropdown);

        // Add components to showtimePanel
        showtimePanel.add(showtimeLabel);
        showtimePanel.add(showtimeDropdown);

        // Add all panels to the frame
        frame.add(moviePanel, BorderLayout.NORTH);
        frame.add(showtimePanel, BorderLayout.CENTER);
        frame.add(seatPanel, BorderLayout.SOUTH);

        // Add action listeners for interactions
        movieDropdown.addActionListener(e -> showtimePanel.setVisible(true));
        showtimeDropdown.addActionListener(e -> seatPanel.setVisible(true));

        selectSeatButton.addActionListener(e -> {
            JFrame seatFrame = new JFrame("Select Your Seats");
            seatFrame.setSize(800, 600);
            seatFrame.setLayout(new BorderLayout());
        
            JPanel seatGrid = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
        
            JPanel confirmationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel selectedSeatsLabel = new JLabel("Selected Seats: ");
            JButton confirmSeatsButton = new JButton("Confirm Seats");
            confirmationPanel.add(selectedSeatsLabel);
            confirmationPanel.add(confirmSeatsButton);
        
            // Load seat icons
            ImageIcon availableIcon = new ImageIcon("available.png");
            ImageIcon selectedIcon = new ImageIcon("selected.png");
            ImageIcon occupiedIcon = new ImageIcon("occupied.png");
        
            String[][] seatMap = {
                    {"A1", "A2", " ", "A3", "A4", "A5", " ", "A6", "A7"},
                    {"B1", "B2", " ", "B3", "B4", "B5", " ", "B6", "B7"},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {"C1", "C2", " ", "C3", "C4", "C5", " ", "C6", "C7"},
                    {"D1", "D2", " ", "D3", "D4", "D5", " ", "D6", "D7"},
                    {"E1", "E2", " ", "E3", "E4", "E5", " ", "E6", "E7"},
            };
        
            // Track selected seats
            StringBuilder selectedSeats = new StringBuilder();
        
            for (int row = 0; row < seatMap.length; row++) {
                for (int col = 0; col < seatMap[row].length; col++) {
                    String seatLabel = seatMap[row][col];
        
                    if (seatLabel.equals(" ")) {
                        gbc.gridx = col;
                        gbc.gridy = row;
                        seatGrid.add(new JLabel(" "), gbc);
                    } else {
                        JButton seatButton = new JButton(availableIcon);
                        seatButton.setPreferredSize(new Dimension(60, 60));
                        seatButton.setContentAreaFilled(false);
                        seatButton.setBorderPainted(false);
        
                        if (seatStates.containsKey(seatLabel) && seatStates.get(seatLabel)) {
                            seatButton.setIcon(occupiedIcon);
                            seatButton.setEnabled(false);
                        }
        
                        seatButton.addActionListener(seatEvent -> {
                            if (seatButton.getIcon() == availableIcon) {
                                seatButton.setIcon(selectedIcon);
                                seatStates.put(seatLabel, true);
                                selectedSeats.append(seatLabel).append(" ");
                                selectedSeatsLabel.setText("Selected Seats: " + selectedSeats.toString());
                                numTickets++;
                            } else if (seatButton.getIcon() == selectedIcon) {
                                seatButton.setIcon(availableIcon);
                                seatStates.put(seatLabel, false);
                                int index = selectedSeats.indexOf(seatLabel);
                                if (index >= 0) {
                                    selectedSeats.delete(index, index + seatLabel.length() + 1);
                                }
                                selectedSeatsLabel.setText("Selected Seats: " + selectedSeats.toString());
                                numTickets--;
                            }
                        });
        
                        gbc.gridx = col;
                        gbc.gridy = row;
                        seatGrid.add(seatButton, gbc);
                    }
                }
            }
        
            confirmSeatsButton.addActionListener(confirmEvent -> {
                if (selectedSeats.length() == 0) {
                    JOptionPane.showMessageDialog(seatFrame, "No seats selected. Please select at least one seat.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(seatFrame, "Seats confirmed: " + selectedSeats.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    seatFrame.dispose();
                }
            });
        
            seatFrame.add(seatGrid, BorderLayout.CENTER);
            seatFrame.add(confirmationPanel, BorderLayout.SOUTH);
        
            seatFrame.setVisible(true);
        });
        
        

        confirmSeatButton.addActionListener(e -> {
            String selectedMovie = (String) movieDropdown.getSelectedItem();
            double ticketPrice = 15.00;

            frame.dispose();
            new PaymentGUI(selectedMovie, numTickets, ticketPrice);
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieGUI::new);
    }
}
