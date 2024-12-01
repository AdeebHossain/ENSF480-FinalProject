package Entities;

import java.util.Date;
import java.util.List;

public class Booking {
    // Attributes
    private static int bookingId = 0;
    private User user;
    private List<Ticket> tickets; // List of tickets for this booking
    private boolean isCancelled; // Whether the booking was cancelled
    private Date bookingDate;

    // Constructor
    public Booking(int bookingId, User user, List<Ticket> tickets, Date bookingDate) {
        Booking.bookingId = bookingId ;
        this.user = user;
        this.tickets = tickets;
        this.bookingDate = bookingDate;
        this.isCancelled = false; // Initially, the booking is not cancelled
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId() {
        Booking.bookingId = bookingId++;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Method to cancel the booking
    public void cancelBooking() {
        if (!isCancelled) {
            this.isCancelled = true;
            for (Ticket ticket : tickets) {
                ticket.getSeatNumber(); 
                
            }
        }
    }

    // Method to view booking details
    public String viewBooking() {
        StringBuilder details = new StringBuilder();
        details.append("Booking ID: ").append(bookingId).append("\n")
                .append("User: ").append(user.getName()).append("\n")
                .append("Booking Date: ").append(bookingDate).append("\n")
                .append("Cancelled: ").append(isCancelled ? "Yes" : "No").append("\n")
                .append("Tickets:\n");

        for (Ticket ticket : tickets) {
            details.append("  - ").append(ticket.toString()).append("\n");
        }

        return details.toString();
    }

    @Override
    public String toString() {
        return "Booking{" +
               "bookingId='" + bookingId + '\'' +
               ", user=" + user +
               ", tickets=" + tickets +
               ", isCancelled=" + isCancelled +
               ", bookingDate=" + bookingDate +
               '}';
    }
}

