package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieController {
    private MySQLConnection DB;

    // Constructor
    public MovieController() {
        DB = MySQLConnection.getInstance();
    }

    // Method to get movie summary by ID
    public String getSummary(int id) {
        String query = "SELECT summary FROM movies WHERE id = ?";
        ResultSet results = DB.query(query, id);
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
    public void addMovie(String movieName, String movieDesc, String movieLength, String dateAvail) {
        String query = "INSERT INTO movies (name, summary, length, date_available) VALUES (?, ?, ?, ?)";
        DB.execute(query, movieName, movieDesc, movieLength, dateAvail);
    }

    // Method to remove a movie
    public void removeMovie(String movieName) {
        String query = "DELETE FROM movies WHERE name = ?";
        DB.execute(query, movieName);
    }

    public List<String[]> getAllMovies() {
        String query = "SELECT name, summary FROM movies";
        ResultSet results = DB.query(query);
        List<String[]> movies = new ArrayList<>();

        try {
            while (results.next()) {
                String name = results.getString("name");
                String summary = results.getString("summary");
                movies.add(new String[]{name, summary});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void updateMovie(String oldName, String newName, String newSummary) {
        String query = "UPDATE movies SET name = ?, summary = ? WHERE name = ?";
        DB.execute(query, newName, newSummary, oldName);
    }

}
