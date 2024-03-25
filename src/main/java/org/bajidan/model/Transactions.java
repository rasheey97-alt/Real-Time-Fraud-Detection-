package org.bajidan.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions {
    private LocalDateTime timestamp;
    double amount;
    String userID;
    String serviceID;

    public Transactions(String timestamp, double amount, String userID, String serviceID) {
        this.timestamp = getTime(timestamp);
        this.amount = amount;
        this.userID = userID;
        this.serviceID = serviceID;
    }

    public Transactions(String userID, String serviceID, LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.userID = userID;
        this.serviceID = serviceID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = getTime(timestamp);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %82f %s",
                getTimestamp(), getUserID(), getAmount(), getServiceID());
    }

    private LocalDateTime getTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
