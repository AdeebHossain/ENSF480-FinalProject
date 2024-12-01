package Entities;

import java.util.Date;

public class Receipt {
    // Attributes
    private int receiptID;
    private int numTickets;
    private double total;
    private Date date;
    private String user;

    // Constructor
    public Receipt(int receiptID, int numTickets, double total, Date date, String user) {
        this.receiptID = receiptID;
        this.numTickets = numTickets;
        this.total = total;
        this.date = date;
        this.user = user;
    }

    // Getters and Setters
    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // Methods
    public void generateReceipt() {
        System.out.println("Generating receipt...");
        System.out.println("Receipt ID: " + receiptID);
        System.out.println("User: " + user);
        System.out.println("Date: " + date);
        System.out.println("Number of Tickets: " + numTickets);
        System.out.println("Total: $" + total);
    }

    public void sendReceipt() {
        System.out.println("Sending receipt to user: " + user);
        // Add logic for sending receipt (e.g., email or other methods)
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptID=" + receiptID +
                ", numTickets=" + numTickets +
                ", total=" + total +
                ", date=" + date +
                ", user='" + user + '\'' +
                '}';
    }
}
