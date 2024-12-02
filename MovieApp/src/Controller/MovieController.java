package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieController {
    private final MySQLConnection connection;

    // Constructor
    public MovieController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to get movie summary by ID
    public String getSummary(int id) {
        String query = "SELECT summary FROM movies WHERE id = ?";
        ResultSet results = connection.query(query, id);
        try {
            if (results.next()) {
                return results.getString("summary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to add a movie
    public void addMovie(String movieName, String movieDesc, int movieLength, String dateAvail) {
        String query = "INSERT INTO movies (name, summary, length, air_date) VALUES (?, ?, ?, ?)";
        connection.execute(query, movieName, movieDesc, movieLength, dateAvail);
    }

    // Method to remove a movie
    public void removeMovie(String movieName) {
        String query = "DELETE FROM movies WHERE name = ?";
        connection.execute(query, movieName);
    }

    public List<String[]> getAllMovies() {
        String query = "SELECT name, summary, length FROM movies";
        ResultSet results = connection.query(query);
        List<String[]> movies = new ArrayList<>();

        try {
            while (results.next()) {
                String name = results.getString("name");
                String summary = results.getString("summary");
                int length = results.getInt("length");
                String lengthStr = String.valueOf(length);
                movies.add(new String[]{name, summary, lengthStr});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void updateMovie(String oldName, String newName, String newSummary) {
        String query = "UPDATE movies SET name = ?, summary = ? WHERE name = ?";
        connection.execute(query, newName, newSummary, oldName);
    }

    // Method to get movie duration by id
    public String getDuration(int id) {
        String query = "SELECT length FROM movies WHERE id = ?";
        ResultSet results = connection.query(query, id);
        try {
            if (results.next()) {
                return results.getString("length");
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }

        return null;
    }

    public Integer getMovieIdByName(String movieName) {
        String query = "SELECT movie_id FROM movies WHERE name = ?";
        ResultSet results = connection.query(query, movieName); // Assuming `connection.query` handles queries with parameters
        try {
            if (results.next()) {
                int movieId = results.getInt("movie_id"); // Retrieve the movie_id
                System.out.println("Retrieved movie ID: " + movieId); // Print the movie ID to the terminal
                return movieId; // Return the movie_id
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
        System.out.println("No movie found with the name: " + movieName); // Print a message if no match is found
        return null; // Return null if no match is found
    }

}