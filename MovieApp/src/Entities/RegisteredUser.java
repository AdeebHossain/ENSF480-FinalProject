package Entities;

public class RegisteredUser extends User {
    // Attributes specific to RegisteredUser
    private String password;
    private String email;
    private String address;
    private BankCardInfo paymentInfo; // This is for RegisteredUser only
    private double annualFee = 20.00; // The annual fee for Registered Users
    private boolean hasDiscount = true; // Registered Users have a discount on cancellation fees

    // Constructor
    public RegisteredUser(Name name, String username, String password, String email, String address, BankCardInfo paymentInfo) {
        super(name, username); // Call to parent constructor for name and username
        this.password = password;
        this.email = email;
        this.address = address;
        this.paymentInfo = paymentInfo; // Card number is for RegisteredUser
    }

    // Getters and Setters for RegisteredUser specific attributes
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankCardInfo getpaymentInfo() {
        return paymentInfo;
    }

    public void setpaymentInfo(BankCardInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
               "name='" + getName() + '\'' +
               ", username='" + getUsername() + '\'' +
               ", email='" + email + '\'' +
               ", address='" + address + '\'' +
               ", paymentInfo='" + paymentInfo + '\'' +
               ", annualFee=" + annualFee +
               ", hasDiscount=" + hasDiscount +
               '}';
    }
}
