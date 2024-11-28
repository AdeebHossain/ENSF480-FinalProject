package Controller;

import Entities.*;
import java.util.List;

public class BookingController {

    public Booking createBooking(User user, List<Ticket> tickets, Payment payment) {
        // Validate inputs
        // Create booking object, associate with user
    }

    public void cancelBooking(Booking booking) {
        // Cancel the booking and release reserved seats
    }

    public void addTicketsToBooking(Booking booking, List<Ticket> newTickets) {
        // Add tickets to an existing booking
    }

    public Receipt generateReceipt(Booking booking) {
        // Generate a receipt for a completed booking
    }
}
