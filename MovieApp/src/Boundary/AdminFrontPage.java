package Boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Controller.MovieController;

public class AdminFrontPage {

    private final MovieController movieController;

    public AdminFrontPage() {
        movieController = new MovieController();
        initAdminPage();
    }

    private void initAdminPage() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Movie Name", "Summary"}, 0);
        JTable movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        refreshMovieTable(tableModel);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        JButton addMovieButton = new JButton("Add Movie");
        JButton removeMovieButton = new JButton("Remove Movie");
        JButton editMovieButton = new JButton("Edit Movie");

        controlPanel.add(addMovieButton);
        controlPanel.add(removeMovieButton);
        controlPanel.add(editMovieButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        addMovieButton.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField summaryField = new JTextField();
            JTextField lengthField = new JTextField();
            JTextField airDateField = new JTextField();

            JPanel inputPanel = new JPanel(new GridLayout(4, 2));
            inputPanel.add(new JLabel("Name:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Summary:"));
            inputPanel.add(summaryField);
            inputPanel.add(new JLabel("Length (minutes):"));
            inputPanel.add(lengthField);
            inputPanel.add(new JLabel("Air Date (YYYY-MM-DD):"));
            inputPanel.add(airDateField);

            int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Add New Movie", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    String summary = summaryField.getText();
                    String length = lengthField.getText();
                    String airDate = airDateField.getText();

                    movieController.addMovie(name, summary, length, airDate);
                    refreshMovieTable(tableModel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please check the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeMovieButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                String movieName = tableModel.getValueAt(selectedRow, 0).toString();
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the movie: " + movieName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    movieController.removeMovie(movieName);
                    refreshMovieTable(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editMovieButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                JTextField nameField = new JTextField(tableModel.getValueAt(selectedRow, 0).toString());
                JTextField summaryField = new JTextField(tableModel.getValueAt(selectedRow, 1).toString());

                JPanel inputPanel = new JPanel(new GridLayout(2, 2));
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(nameField);
                inputPanel.add(new JLabel("Summary:"));
                inputPanel.add(summaryField);

                int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Edit Movie", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String oldName = tableModel.getValueAt(selectedRow, 0).toString();
                    String newName = nameField.getText();
                    String newSummary = summaryField.getText();

                    movieController.updateMovie(oldName, newName, newSummary);
                    refreshMovieTable(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a movie to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void refreshMovieTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        List<String[]> movies = movieController.getAllMovies();
        for (String[] movie : movies) {
            tableModel.addRow(movie);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminFrontPage::new);
    }
}