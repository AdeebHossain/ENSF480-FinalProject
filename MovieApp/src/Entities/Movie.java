/*
 * ENSF480 Term Project- Fall 2024- Movie Theatre App
 * Instructor: Syed Tauhid Ullah Shah
 * Authors: G16 
 * Lecture Section: L01 
 */

package Entities;

public class Movie {
    private int movie_ID;
    private String movie_title;
    private String movie_genre;
    private String movie_summary;
    private Announcement announcement;

    private static int movieID = 1000;

    // Constructor for creating a new movie 
    public Movie(String movie_title, String movie_genre, String movie_summary, double movie_price) {
        this.movie_ID = movieID++; 
        this.movie_title = movie_title;
        this.movie_genre = movie_genre;
        this.movie_summary = movie_summary;
        this.announcement = null; 
    }

    // Constructor for creating a movie with a specified ID (for example to load from a database)
    public Movie(int movie_ID, String movie_title, String movie_genre, String movie_summary, double movie_price) {
        this.movie_ID = movie_ID;

        // Ensure movieID is ahead of the given movie_ID to avoid duplicates
        if (movie_ID >= movieID) {
            movieID = movie_ID + 1;
        }

        this.movie_title = movie_title;
        this.movie_genre = movie_genre;
        this.movie_summary = movie_summary;
        this.announcement = null;
    }

    // Getters
    public int getMovie_ID() {
        return movie_ID;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_summary() {
        return movie_summary;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    // Setters
    public void setMovie_ID(int movie_ID) {
        this.movie_ID = movie_ID;
    }    

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }    

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }    

    public void setMovie_summary(String movie_summary) {
        this.movie_summary = movie_summary;
    }    

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    // Override toString to provide a description of the movie
    @Override
    public String toString() {
        return String.format("Movie ID: %d\nTitle: %s\nGenre: %s\nSummary: %s\n", movie_ID, movie_title, movie_genre, movie_summary);
    }
}
