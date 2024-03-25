package org.bajidan;

import java.util.*;

public class FraudDetection {

    public static final int WINDOW_SIZE = 5 * 60; // 5 minutes in seconds
    public static final int MIN_DISTINCT_SERVICES = 3; // Minimum distinct services to trigger alert

    public static class Transaction {
        private long timestamp;
        private double amount;
        private String userID;
        private String serviceID;

        public Transaction(long timestamp, double amount, String userID, String serviceID) {
            this.timestamp = timestamp;
            this.amount = amount;
            this.userID = userID;
            this.serviceID = serviceID;
        }

        // Getters omitted for brevity
    }

    public static void main(String[] args) {
        Map<String, List<Transaction>> userTransactions = new HashMap<>();

        // Sample transactions (replace with real data processing)
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1617906000, 150.00, "user1", "serviceA"),
                // ... other transactions from the test data
                new Transaction(1617906540, 5000.00, "user3", "serviceC")
        );

        for (Transaction transaction : transactions) {
            processTransaction(transaction, userTransactions);
        }
    }

    private static void processTransaction(Transaction transaction, Map<String, List<Transaction>> userTransactions) {
        List<Transaction> userTxList = userTransactions.getOrDefault(transaction.userID, new ArrayList<>());
        userTxList.add(transaction);

        // Remove transactions outside the window
        long currentTimestamp = transaction.timestamp;
        long minTimestamp = currentTimestamp - WINDOW_SIZE;
        userTxList.removeIf(t -> t.timestamp < minTimestamp);
        userTransactions.put(transaction.userID, userTxList);

        // Check for frequent service usage within the window
        Set<String> distinctServices = new HashSet<>();
        for (Transaction t : userTxList) {
            distinctServices.add(t.serviceID);
        }
        if (distinctServices.size() > MIN_DISTINCT_SERVICES) {
            System.out.println("Alert: user {transaction.userID} conducted transactions in {distinctServices.size()} distinct services within a {WINDOW_SIZE / 60} minute window.");
        }
    }
}

