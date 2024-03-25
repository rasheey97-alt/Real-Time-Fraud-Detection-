package org.bajidan.service;


import org.bajidan.model.Transactions;
import org.bajidan.repo.TransactionRepo;

import java.time.LocalDateTime;
import java.util.*;

public class FraudDetentionSystem {

    private static final TransactionRepo transactionRepo = new TransactionRepo();
    public static final int MIN_DISTINCT_SERVICES = 3; // Minimum distinct services to trigger alert



    // Method to process a single transaction event
    public static void processTransaction(Transactions transaction) {
       flagThreeDistinctService(transaction);
    }

    // Method to process a stream of transaction events
    public static void processTransactionStream(List<Transactions> transactions) {
//        for (Transactions transaction : transactions) {
//            processTransaction(transaction);
//        }

        detectPingPong(transactions);
    }

    // Flag transactions in more than 3 distinct services within a 5-minute window
    private static void flagThreeDistinctService(Transactions transaction) {
        List<Transactions> userTxList = transactionRepo.getUserTransaction()
                .getOrDefault(transaction.getUserID(), new ArrayList<>());
        userTxList.add(transaction);

        // Remove transactions outside the window
        LocalDateTime currentTimestamp = transaction.getTimestamp();
        LocalDateTime minTimestamp = currentTimestamp.minusMinutes(5);
        userTxList.removeIf(t -> t.getTimestamp().isBefore(minTimestamp));
        transactionRepo.addTransaction(transaction.getUserID(), userTxList);

        // Check for frequent service usage within the window
        Set<String> distinctServices = new HashSet<>();
        Map<String, Integer> numbersOfService = new HashMap<>();

        for (Transactions t : userTxList) {
            distinctServices.add(t.getServiceID());
        }

        if (distinctServices.size() > MIN_DISTINCT_SERVICES) {
            System.out.println("Alert: user " + transaction.getUserID() +
                    " conducted transactions in " +  distinctServices.size() +
                    " distinct services within a 5 minute window.");
        }

    }


        private static void detectPingPong(List<Transactions> transactions) {
            // Map to store user's transactions for each service
            Map<String, Map<String, LocalDateTime>> userTransactions = new HashMap<>();



            // Iterate through transactions
            for (Transactions transaction : transactions) {
                String userID = transaction.getUserID();
                String serviceID = transaction.getServiceID();
                LocalDateTime timestamp = transaction.getTimestamp();

                // Get user's transactions map for the current service
                Map<String, LocalDateTime> serviceTransactions = userTransactions.getOrDefault(userID, new HashMap<>());

                // Check if there is a previous transaction for the same service
                if (serviceTransactions.containsKey(serviceID)) {
                    LocalDateTime lastTimestamp = serviceTransactions.get(serviceID);
                    // Calculate time difference in minutes
                    long minutesDifference = java.time.Duration.between(lastTimestamp, timestamp).toMinutes();
                    // Check if the time difference is less than or equal to 10 minutes
                    if (minutesDifference <= 10) {
                        // Ping-pong activity detected
                        System.out.println("Alert: Ping-pong activity detected for user " + userID + " between services " + serviceID + " and " + serviceTransactions.entrySet().iterator().next().getKey());
                    }
                }

                // Update user's transactions map with the current transaction
                serviceTransactions.put(serviceID, timestamp);
                userTransactions.put(userID, serviceTransactions);
            }
        }
        public static void main(String[] args) {
//        List<Transactions> transactions = List.of(
//                new Transactions("2024-03-12 15:24", 150.25, "user1", "serviceD")
////                new Transactions("2024-03-12 15:00", 200.50, "user2", "serviceB"),
////                new Transactions("2024-03-12 16:00", 300.50, "user3", "serviceC"),
////                new Transactions("2024-03-12 17:00", 400.50, "user4", "serviceD"),
////                new Transactions("2024-03-12 18:00", 500.50, "user5", "serviceE")
//        );

            List<Transactions> transactions = List.of(new Transactions("user1", "serviceA", LocalDateTime.parse("2024-03-12T10:00:00")),
                    new Transactions("user1", "serviceB", LocalDateTime.parse("2024-03-12T10:01:00")), // Within 1 minute
                    new Transactions("user1", "serviceA", LocalDateTime.parse("2024-03-12T10:02:00")), // Within 1 minute
                    new Transactions("user1", "serviceB", LocalDateTime.parse("2024-03-12T10:03:00")) );

       processTransactionStream(transactions);
    }
}
