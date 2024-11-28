package Controller;

import Entities.*;

public class MovieController {

    public Movie addMovie(String name, String description, String genre) {
        // Add a new movie to the database
    }

    public void removeMovie(Movie movie) {
        // Remove the movie from the database
    }

    public void alterMovie(Movie movie, String newDescription, String newGenre) {
        // Modify movie details
    }

    public void addShowtime(Movie movie, Showtime showtime) {
        // Add a new showtime for the movie
    }

    public void removeShowtime(Movie movie, Showtime showtime) {
        // Remove a showtime for the movie
    }
}
