package Entities;

import java.util.List;

public class Showtime {
    // Attributes
    private int showtimeId; // Unique identifier for the showtime
    private Movie movie; // Movie that is being shown
    private String startTime; // Start time of the showtime
    private Date date; // Date of the showtime
    private List<Seat> availableSeats; // List of available seats for the showtime
    private boolean isActive; // Whether the showtime is still active or canceled

    // Constructors

    public Showtime(int showtimeId, Movie movie, String startTime) {
        this.showtimeId = showtimeId;
        this.movie = movie;
        this.startTime = startTime;
            this.isActive = true; 
        }


    public Showtime(int showtimeId, Movie movie, String startTime, List<Seat> availableSeats) {
        this.showtimeId = showtimeId;
        this.movie = movie;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.isActive = true; // By default, the showtime is active when created
    }

    // Getters and Setters
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    // Method to book a seat
    public boolean bookSeat(Seat seat) {
        if (seat.isAvailable()) {
            seat.reserveSeat(); // Reserve the seat
            return true; // Successfully booked
        }
        return false; // Seat was already reserved
    }

    // Method to cancel a booking
    public void cancelBooking(Seat seat) {
        seat.cancelReservation();
    }

    @Override
    public String toString() {
        return "Showtime{" +
               ", movie=" + movie.getMovie_title() +
               ", startTime='" + startTime + '\'' +
               ", availableSeats=" + availableSeats.size() +
               ", isActive=" + isActive +
               '}';
    }
}
