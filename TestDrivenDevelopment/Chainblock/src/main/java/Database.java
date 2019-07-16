import java.util.*;

public class Database {
    private static Map<Integer, Transaction> DATABASE = new LinkedHashMap<>();

    public static boolean contains(Transaction transaction) {
        return DATABASE.containsKey(transaction.getId());
    }

    public static boolean contains(int id) {
        return DATABASE.containsKey(id);
    }

    public static void add(Transaction transaction) {
        DATABASE.put(transaction.getId(), transaction);
    }

    public static Transaction getTransactionByID(int id) {
        return DATABASE.get(id);
    }

    public static void removeByID(int id) {
        DATABASE.remove(id);
    }

    public static List<Transaction> getTransactionsByStatus(TransactionStatus transactionStatus) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : DATABASE.values()) {
            if (transactionStatus.equals(transaction.getStatus())) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public static int getSize() {
        return DATABASE.size();
    }

    public static List<Transaction> getTransactionsBySender(String sender) {
        List<Transaction> sendersList = new ArrayList<>();
        for (Transaction transaction : DATABASE.values()) {
            if (transaction.getFrom().equals(sender)) {
                sendersList.add(transaction);
            }
        }
        return sendersList;
    }

    public static List<Transaction> getTransactionsByReceiver(String sender) {
        List<Transaction> sendersList = new ArrayList<>();
        for (Transaction transaction : DATABASE.values()) {
            if (transaction.getTo().equals(sender)) {
                sendersList.add(transaction);
            }
        }
        return sendersList;
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(DATABASE.values());
        return transactions;
    }


}
