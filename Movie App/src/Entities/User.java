package Entities;

public class User {
    // Common Attributes for all types of users
    private String name;
    private String username;

    // Constructor
    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
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
