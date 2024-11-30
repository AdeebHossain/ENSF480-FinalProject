package Entities;

public class BankCardInfo {
    private int cardNumber;
    private int expiryDate;
    private int cvv;

    // Constructor
    public BankCardInfo(int cardNumber, int expiryDate, int cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getters
    public int getCardNumber() {
        return cardNumber;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    // Setters
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    // ToString override for better representation
    @Override
    public String toString() {
        return String.format("Card Number: %d, Expiry Date: %d, cvv: %d", cardNumber, expiryDate, cvv);
    }
}
