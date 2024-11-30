package Boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminFrontPage {

    // Sample data for movies
    private final ArrayList<Movie> movies;

    public AdminFrontPage() {
        movies = new ArrayList<>();
        initAdminPage();
    }

    private void initAdminPage() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Title label at the top
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Center panel for the movie table
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Movie Name", "Showtimes"}, 0);
        JTable movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        // Populate initial movies (sample data)
        populateSampleMovies();
        refreshMovieTable(tableModel);

        panel.add(scrollPane, BorderLayout.CENTER);

        // South panel for admin controls
        JPanel controlPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        JButton addMovieButton = new JButton("Add Movie");
        JButton removeMovieButton = new JButton("Remove Movie");
        JButton editMovieButton = new JButton("Edit Movie");
        JButton addShowtimeButton = new JButton("Add Showtime");
        JButton removeShowtimeButton = new JButton("Remove Showtime");
        JButton editShowtimeButton = new JButton("Edit Showtime");

        // Add buttons to the control panel
        controlPanel.add(addMovieButton);
        controlPanel.add(removeMovieButton);
        controlPanel.add(editMovieButton);
        controlPanel.add(addShowtimeButton);
        controlPanel.add(removeShowtimeButton);
        controlPanel.add(editShowtimeButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        // Add functionality to buttons
        addMovieButton.addActionListener(e -> {
            String movieName = JOptionPane.showInputDialog(frame, "Enter Movie Name:");
            if (movieName != null && !movieName.trim().isEmpty()) {
                movies.add(new Movie(movieName));
                refreshMovieTable(tableModel);
            }
        });

        removeMovieButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                movies.remove(selectedRow);
                refreshMovieTable(tableModel);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editMovieButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                String newName = JOptionPane.showInputDialog(frame, "Enter new Movie Name:", movies.get(selectedRow).getName());
                if (newName != null && !newName.trim().isEmpty()) {
                    movies.get(selectedRow).setName(newName);
                    refreshMovieTable(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addShowtimeButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                String showtime = JOptionPane.showInputDialog(frame, "Enter Showtime (e.g., 12:00 PM):");
                if (showtime != null && !showtime.trim().isEmpty()) {
                    movies.get(selectedRow).addShowtime(showtime);
                    refreshMovieTable(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to add a showtime.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeShowtimeButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                String[] showtimes = movies.get(selectedRow).getShowtimes().toArray(new String[0]);
                String selectedShowtime = (String) JOptionPane.showInputDialog(frame, "Select a Showtime to Remove:", 
                        "Remove Showtime", JOptionPane.QUESTION_MESSAGE, null, showtimes, showtimes[0]);
                if (selectedShowtime != null) {
                    movies.get(selectedRow).removeShowtime(selectedShowtime);
                    refreshMovieTable(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to remove a showtime.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editShowtimeButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                String[] showtimes = movies.get(selectedRow).getShowtimes().toArray(new String[0]);
                String selectedShowtime = (String) JOptionPane.showInputDialog(frame, "Select a Showtime to Edit:", 
                        "Edit Showtime", JOptionPane.QUESTION_MESSAGE, null, showtimes, showtimes[0]);
                if (selectedShowtime != null) {
                    String newShowtime = JOptionPane.showInputDialog(frame, "Enter new Showtime:", selectedShowtime);
                    if (newShowtime != null && !newShowtime.trim().isEmpty()) {
                        movies.get(selectedRow).editShowtime(selectedShowtime, newShowtime);
                        refreshMovieTable(tableModel);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to edit a showtime.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void populateSampleMovies() {
        movies.add(new Movie("Movie 1", "10:00 AM", "1:00 PM", "6:00 PM"));
        movies.add(new Movie("Movie 2", "11:00 AM", "3:00 PM", "7:30 PM"));
        movies.add(new Movie("Movie 3", "9:30 AM", "12:30 PM", "8:00 PM"));
    }

    private void refreshMovieTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Movie movie : movies) {
            tableModel.addRow(new Object[]{movie.getName(), String.join(", ", movie.getShowtimes())});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminFrontPage::new);
    }
}

class Movie {
    private String name;
    private final ArrayList<String> showtimes;

    public Movie(String name, String... showtimes) {
        this.name = name;
        this.showtimes = new ArrayList<>();
        if (showtimes != null) {
            for (String showtime : showtimes) {
                this.showtimes.add(showtime);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getShowtimes() {
        return showtimes;
    }

    public void addShowtime(String showtime) {
        showtimes.add(showtime);
    }

    public void removeShowtime(String showtime) {
        showtimes.remove(showtime);
    }

    public void editShowtime(String oldShowtime, String newShowtime) {
        int index = showtimes.indexOf(oldShowtime);
        if (index >= 0) {
            showtimes.set(index, newShowtime);
        }
    }
}
