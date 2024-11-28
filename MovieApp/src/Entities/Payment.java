package Entities;

import java.util.Date;

public class Payment {
    // Attributes
    private String paymentId;
    private double amount;
    private String paymentMethod;
    private Date paymentDate;
    private boolean paymentStatus; // true for successful, false for failed

    // Constructor
    public Payment(String paymentId, double amount, String paymentMethod, Date paymentDate, boolean paymentStatus) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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
               ", paymentDate=" + paymentDate +
               ", paymentStatus=" + paymentStatus +
               '}';
    }
}
