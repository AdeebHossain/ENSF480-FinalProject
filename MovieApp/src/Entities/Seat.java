package Entities;

public class Seat {
    // Attributes
    private int seatId; // Unique ID for the seat (maps to the database seat_ID column)
    private char seatRow;
    private int seatNumber;
    private boolean isAvailable;

    // Constructor
    public Seat(int seatId, char seatRow, int seatNumber, boolean isAvailable) {
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    // Overloaded constructor for use cases where seatId is not initially available
    public Seat(char seatRow, int seatNumber, boolean isAvailable) {
        this(-1, seatRow, seatNumber, isAvailable); // -1 indicates uninitialized seatId
    }

    // Getters and Setters
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeat() {
        return seatRow + Integer.toString(seatNumber);
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
               "seatId=" + seatId +
               ", seatRow='" + seatRow + '\'' +
               ", seatNumber=" + seatNumber +
               ", isAvailable=" + isAvailable +
               '}';
    }
}
