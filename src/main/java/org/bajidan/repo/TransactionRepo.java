package org.bajidan.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bajidan.model.Transactions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionRepo {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Transactions> transactionsList = List.of(
//            new Transactions("2024-03-12 15:05", 150.25, "user1", "serviceA"),
//            new Transactions("2024-03-12 15:20", 200.50, "user1", "serviceB"),
//            new Transactions("2024-03-12 15:21", 300.75, "user1", "serviceC"),
//            new Transactions("2024-03-12 15:22", 450.90, "user1", "serviceE"),
//            new Transactions("2024-03-12 15:23", 600.30, "user1", "serviceE"),
//            new Transactions("2024-03-12 00:00", 100.20, "user2", "serviceC"),
//            new Transactions("2024-03-12 15:00", 250.80, "user2", "serviceB"),
//            new Transactions("2024-03-12 20:00", 500.45, "user2", "serviceC"),
//            new Transactions("2024-03-12 15:00", 700.60, "user2", "serviceD"),
//            new Transactions("2024-03-12 00:00", 900.75, "user2", "serviceE"),
//            new Transactions("2024-03-12 00:00", 50.10,  "user3", "serviceA"),
//            new Transactions("2024-03-12 15:00", 100.25, "user3", "serviceB")


//        new Transactions("2024-03-12 00:00", 600,"user1", "serviceA"),
//        new Transactions("2024-03-12 15:00", 500,"user1",  "serviceB"),
//        new Transactions("2024-03-12 15:00" ,200, "user1", "serviceC"),
//        new Transactions("2024-03-12 15:00" ,200, "user1", "serviceC")

            new Transactions("user1", "serviceA", LocalDateTime.parse("2024-03-12T10:00:00")),
                    new Transactions("user1", "serviceB", LocalDateTime.parse("2024-03-12T10:01:00")), // Within 1 minute
   new Transactions("user1", "serviceA", LocalDateTime.parse("2024-03-12T10:02:00")), // Within 1 minute
   new Transactions("user1", "serviceB", LocalDateTime.parse("2024-03-12T10:03:00")) // Within 1 minute

    );



    private Map<String,  List<Transactions>> allUsersTransaction = new HashMap<>();

    public Map<String, List<Transactions>> getUserTransaction() {
        allUsersTransaction = transactionsList.stream()
                .collect(Collectors.groupingBy(Transactions::getUserID));
        return allUsersTransaction;
    }

    public void addTransaction(String key, List<Transactions> transactions) {
        allUsersTransaction.put(key, transactions);
    }

    public List<Transactions> getAllTransaction() {
//        List<Transactions> transactions = null;
//        try {
//          transactions =
//                 objectMapper.readValue(new File("C:\\Users\\ID\\IdeaProjects\\spring_boot\\Real-Time Fraud Detection System\\src\\main\\java\\org\\bajidan\\repo\\text.json"),
//                         new TypeReference<List<Transactions>>(){});
//     } catch (IOException e) {
//         System.out.println(e.getMessage());
//     }

     return transactionsList;
    }

    public static void main(String[] args) {
//        getUserTransaction().forEach(
//                (department, employeesInDepartment) ->
//                {
//                    System.out.println(department);
//                    employeesInDepartment.forEach(
//                            employee -> System.out.printf(" %s%n", employee));
//                }
//        );

    }
}
