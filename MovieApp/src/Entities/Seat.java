package Entities;

public class Seat {
    // Attributes
    private String seatRow;
    private String seatNumber;
    private boolean isAvailable;

    // Constructor
    public Seat(String seatRow, String seatNumber, boolean isAvailable) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public String getseatRow() {
        return seatRow;
    }

    public void setseatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Method to reserve the seat
    public boolean reserveSeat() {
        if (isAvailable) {
            isAvailable = false;
            return true; // Successfully reserved
        }
        return false; // Seat was already reserved
    }

    // Method to cancel reservation
    public void cancelReservation() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Seat{" +
               "seatRow='" + seatRow + '\'' +
               ", seatNumber='" + seatNumber + '\'' +
               ", isAvailable=" + isAvailable +
               '}';
    }
}
