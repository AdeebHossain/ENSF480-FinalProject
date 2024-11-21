/*
 * ENSF480 Term Project- Fall 2024- Movie Theatre App
 * Instructor: Syed Tauhid Ullah Shah
 * Authors: G16 
 * Lecture Section: L01 
 */

 public class Ticket{
    private int ticket_number;
    private Movie movie;
    private Theatre theatre;
    private Seat show_seat;
    private Showtime showtime;
    private Receipt receipt;

    private static int ticketID = 1000;
    
    // Constrctor where partial ticket object is created, however no details regarding payment and reciept are available yet
    public Ticket(Movie movie, Theatre theatre, Seat show_seat, Showtime showtime){
        this.ticket_number = ticketID;
        this.movie = movie;
        this.theatre = theatre;
        this.show_seat = show_seat;
        this.showtime = showtime;
        this.receipt =  null;
    }

    // Constructor where ticket object is created, includidng all payment and reciept details
    public Ticket(int ticket_number, Movie movie, Theatre theatre, Seat show_seat, Showtime showtime, Receipt receipt) {
        this.ticket_number = ticket_number;

        // Ensure ticketID stays ahead of the given ticket_number to avoid duplicates
        if (ticket_number >= ticketID) {
            ticketID = ticket_number + 1;
        }

        this.movie = movie;
        this.theatre = theatre;
        this.show_seat = show_seat;
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

    public Theatre getTheatre() {
        return theatre;
    }

    public Seat getShow_seat() {
        return show_seat;
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

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public void setShow_seat(Seat show_seat) {
        this.show_seat = show_seat;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
 }