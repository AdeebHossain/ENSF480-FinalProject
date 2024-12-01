/*
 * ENSF480 Term Project- Fall 2024- Movie Theatre App
 * Instructor: Syed Tauhid Ullah Shah
 * Authors: G16 
 * Lecture Section: L01 
 */

 package Entities;
 
 public class Ticket{
    private int ticket_number;
    private Movie movie;
    private String seatNumber;
    private Showtime showtime;
    private Receipt receipt;

    private static int ticketID = 1000;
    
    // Constrctor where partial ticket object is created, however no details regarding payment and reciept are available yet
    public Ticket(Movie movie, String seatNumber, Showtime showtime){
        this.ticket_number = ticketID;
        this.movie = movie;
        // this.theatre = theatre;
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.receipt =  null;
    }

    // Constructor where ticket object is created, includidng all payment and reciept details
    public Ticket(int ticket_number, Movie movie, String seatNumber, Showtime showtime, Receipt receipt) {
        this.ticket_number = ticket_number;

        // Ensure ticketID stays ahead of the given ticket_number to avoid duplicates
        if (ticket_number >= ticketID) {
            ticketID = ticket_number + 1;
        }

        this.movie = movie;
        // this.theatre = theatre;
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.receipt = receipt; 
    }

    // Getters
    public int getTicket_number() {
        return ticket_number;
    }

    public Movie getMovie() {
        return movie;
    }

    // public Theatre getTheatre() {
    //     return theatre;
    // }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    //Setters
    public void setTicket_number(int ticket_number) {
        this.ticket_number = ticket_number;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    // public void setTheatre(Theatre theatre) {
    //     this.theatre = theatre;
    // }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void displayTicket() {
        System.out.println("---------- Ticket Details ----------");
        System.out.println("Ticket Number: " + ticket_number);
        System.out.println("Movie: " + (movie != null ? movie.getMovie_title() : "N/A"));
        System.out.println("Seat Number: " + (seatNumber != null ? seatNumber : "N/A"));
        System.out.println("Showtime: " + (showtime != null ? showtime.getStartTime() : "N/A"));
        System.out.println("Date: " + (showtime != null ? showtime.getDate() : "N/A"));
        System.out.println("Receipt: " + (receipt != null ? receipt.getReceiptID() : "Not Paid"));
        System.out.println("------------------------------------");
    }

    // // Method to cancel the ticket
    // public void cancelTicket() {
    //     if (seatNumber != null) {
    //         seatNumber.cancelReservation(); // Cancel the reservation for the seat
    //     }
    //     System.out.println("Ticket " + ticket_number + " has been canceled.");
    // }
 }