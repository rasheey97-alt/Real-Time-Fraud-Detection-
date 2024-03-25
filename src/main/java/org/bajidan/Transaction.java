package org.bajidan;

import java.util.List;
import java.util.ArrayList;

class Transaction {
    long timestamp;
    double amount;
    String userID;
    String serviceID;

    public Transaction(long timestamp, double amount, String userID, String serviceID) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.userID = userID;
        this.serviceID = serviceID;
    }
}

class FraudDetectionSystem {
    // Method to process a single transaction event
    public static void processTransaction(Transaction transaction) {
        // Implement your fraud detection logic here
        // You'll need to analyze the transaction and apply the detection rules
        // For example, check if the transaction matches any fraudulent patterns
    }

    // Method to process a stream of transaction events
    public static void processTransactionStream(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            processTransaction(transaction);
        }
    }

    public static void main(String[] args) {
        // Sample test dataset provided as input
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1617906000, 150.00, "user1", "serviceA"));
        transactions.add(new Transaction(1617906060, 4500.00, "user2", "serviceB"));
        transactions.add(new Transaction(1617906120, 75.00, "user1", "serviceC"));
        transactions.add(new Transaction(1617906180, 3000.00, "user3", "serviceA"));
        transactions.add(new Transaction(1617906240, 200.00, "user1", "serviceB"));
        transactions.add(new Transaction(1617906300, 4800.00, "user2", "serviceC"));
        transactions.add(new Transaction(1617906360, 100.00, "user4", "serviceA"));
        transactions.add(new Transaction(1617906420, 4900.00, "user3", "serviceB"));
        transactions.add(new Transaction(1617906480, 120.00, "user1", "serviceD"));
        transactions.add(new Transaction(1617906540, 5000.00, "user3", "serviceC"));

        // Process the stream of transactions
        processTransactionStream(transactions);
    }
}

