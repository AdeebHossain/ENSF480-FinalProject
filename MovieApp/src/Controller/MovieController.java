package Controller;

import Entities.*;

public class MovieController {
    private MySQLConnection connection;

    public MovieController() {
        this.connection = MySQLConnection.getInstance();
    }

    // Method to add a movie into the DB along with movie information
    public void addMovie(String movieName, String movieDesc, String movieLength, String air_date){
        // Convert movieLength from String to int
        String query = "INSERT INTO movies (name, summary, length, air_date) VALUES (?,?,?,?)";
        connection.execute(query, movieName, movieDesc, Integer.parseInt(movieLength), air_date);
    }

    // Method to remove a movie from the DB
    public void removeMovie(String movieName){
        String query = "DELETE FROM movies WHERE name = ?";
        connection.execute(query, movieName);
    }

    // Method to get the name of a movie
    public String getMovieName(int movieId){
        // Querying the correct table 'movies' and column 'movie_id'
        String query = "SELECT name FROM movies WHERE movie_id = ?";
        return connection.query(query, movieId).toString();
    }

    // Method to get description for a movie
    public String getMovieDesc(int movieId){
        // Querying the correct table 'movies' and column 'movie_id'
        String query = "SELECT summary FROM movies WHERE movie_id = ?";
        return connection.query(query, movieId).toString();
    }

    // Method to get the length of a movie
    public int getMovieLength(int movieId){
        // Querying the correct table 'movies' and column 'movie_id'
        String query = "SELECT length FROM movies WHERE movie_id = ?";
        return Integer.parseInt(connection.query(query, movieId).toString());
    }

}
