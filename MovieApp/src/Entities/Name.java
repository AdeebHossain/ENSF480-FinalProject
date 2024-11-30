package Entities;

public class Name {
    private String firstName;
    private String middleName; 
    private String lastName;

    // Constructor
    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Overloaded Constructor (for cases without a middle name)
    public Name(String firstName, String lastName) {
        this(firstName, null, lastName);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Full name method
    public String returnFullName() {
        if (middleName == null || middleName.isEmpty()) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

    // ToString override for better display
    @Override
    public String toString() {
        return returnFullName();
    }
}
