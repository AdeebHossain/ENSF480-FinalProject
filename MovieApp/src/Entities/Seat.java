package Entities;

public class Seat {
    // Attributes
    private char seatRow;
    private int seatNumber;
    private boolean isAvailable;

    // Constructor
    public Seat(char seatRow, int seatNumber, boolean isAvailable) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public char getseatRow() {
        return seatRow;
    }

    public void setseatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeat(){
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
               "seatRow='" + seatRow + '\'' +
               ", seatNumber='" + seatNumber + '\'' +
               ", isAvailable=" + isAvailable +
               '}';
    }
}
