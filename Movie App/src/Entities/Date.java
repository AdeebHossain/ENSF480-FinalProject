package Entities;

import java.time.LocalDate;

public class Date {
    private int day;
    private int month;
    private int year;

    // Constructor
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    // Method to check if this date is before the current date
    public boolean isBeforeCurrentDate() {
        LocalDate todaysDate = LocalDate.now();
        LocalDate thisDate = LocalDate.of(year, month, day);
        return thisDate.isBefore(todaysDate);
    }
}
