package Entities;

import java.util.Date;
import java.util.List;

public class Booking {
    // Attributes
    private String bookingId;
    private User user;
    private List<Ticket> tickets; // List of tickets for this booking
    private Payment payment;
    private boolean isCancelled; // Whether the booking was cancelled
    private Date bookingDate;

    // Constructor
    public Booking(String bookingId, User user, List<Ticket> tickets, Payment payment, Date bookingDate) {
        this.bookingId = bookingId;
        this.user = user;
        this.tickets = tickets;
        this.payment = payment;
        this.bookingDate = bookingDate;
        this.isCancelled = false; // Initially, the booking is not cancelled
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
                ticket.getShow_seat().cancelReservation(); // Release the reserved seats
            }
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
               "bookingId='" + bookingId + '\'' +
               ", user=" + user +
               ", tickets=" + tickets +
               ", payment=" + payment +
               ", isCancelled=" + isCancelled +
               ", bookingDate=" + bookingDate +
               '}';
    }
}
