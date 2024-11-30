package Entities;

import java.util.Date;

public class Payment {
    // Attributes
    private int paymentId;
    private Ticket ticket;
    private double amount;
    private String paymentMethod;
    private boolean paymentStatus; // true for successful, false for failed

    // Constructor
    public Payment(int paymentId, double amount, String paymentMethod, Date paymentDate, boolean paymentStatus) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getTicketNum() {
        return ticket.getTicket_number();
    }  

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
               "paymentId='" + paymentId + '\'' +
               ", amount=" + amount +
               ", paymentMethod='" + paymentMethod + '\'' +
               ", paymentStatus=" + paymentStatus +
               '}';
    }
}
