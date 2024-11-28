package Entities;

import java.util.Date;
import java.util.List;

public class Receipt {
    // Attributes
    private String ticketId; // Could be a unique identifier for the booking
    private List<Ticket> tickets; // List of tickets in this receipt
    private Date bookingDate;

    // Constructor
    public Receipt(String ticketId, List<Ticket> tickets, Date bookingDate) {
        this.ticketId = ticketId;
        this.tickets = tickets;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Receipt{" +
               "ticketId='" + ticketId + '\'' +
               ", tickets=" + tickets +
               ", bookingDate=" + bookingDate +
               '}';
    }
}
