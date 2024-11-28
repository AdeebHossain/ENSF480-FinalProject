package Controller;

import Entities.*;

public class SeatController {

    public boolean reserveSeat(Seat seat) {
        if (seat.isAvailable()) {
            seat.reserveSeat();
            return true;
        }
        return false; // Seat is not available
    }

    public void cancelReservation(Seat seat) {
        seat.cancelReservation();
    }

    public boolean checkSeatAvailability(Seat seat) {
        return seat.isAvailable();
    }
}
