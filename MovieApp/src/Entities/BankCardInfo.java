package Entities;

public class BankCardInfo {
    private String cardNumber;

    // Constructor
    public BankCardInfo(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter
    public String getCardNumber() {
        return cardNumber;
    }

    // Setter
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // ToString override for better representation
    @Override
    public String toString() {
        return String.format("Card Number: %s", cardNumber);
    }
}
