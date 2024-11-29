package Entities;

public class User {
    // Common Attributes for all types of users
    private Name name;
    private String username;

    // Constructor
    public User(Name name, String username) {
        this.name = name;
        this.username = username;
    }

    // Getters and Setters
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}
