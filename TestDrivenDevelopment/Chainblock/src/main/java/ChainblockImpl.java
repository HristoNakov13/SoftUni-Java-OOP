import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    public int getCount() {
        return Database.getSize();
    }

    public void add(Transaction transaction) {
        if (!Database.contains(transaction)) {
            Database.add(transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return Database.contains(transaction);
    }

    public boolean contains(int id) {
        return Database.contains(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!Database.contains(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        Database.getTransactionByID(id).setTransactionStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!Database.contains(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        Database.removeByID(id);
    }

    public Transaction getById(int id) {
        if (!Database.contains(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return Database.getTransactionByID(id);
    }

    public List<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = Database.getTransactionsByStatus(status);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No transactions with such status");
        }
        return transactions
                .stream()
                .sorted((trans1, trans2) -> Double.compare(trans2.getAmount(), trans1.getAmount()))
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.getByTransactionStatus(status);
        HashSet<String> senders = new HashSet<>();
        for (Transaction transaction : transactions) {
            senders.add(transaction.getFrom());
        }
        List<Transaction> sendersList = new ArrayList<>();
        for (String sender : senders) {
            sendersList.addAll(Database.getTransactionsBySender(sender));
        }
        return sendersList;
    }

    public List<Transaction> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.getByTransactionStatus(status);
        HashSet<String> receivers = new HashSet<>();
        for (Transaction transaction : transactions) {
            receivers.add(transaction.getTo());
        }
        List<Transaction> sendersList = new ArrayList<>();
        for (String receiver : receivers) {
            sendersList.addAll(Database.getTransactionsByReceiver(receiver));
        }
        return sendersList;
    }

    public List<Transaction> getAllOrderedByAmountDescendingThenById() {
        List<Transaction> transactions = Database.getAllTransactions();
        return transactions
                .stream()
                .sorted(Comparator
                        .comparingDouble(Transaction::getAmount)
                        .thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
    }

    public List<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> transactions = Database.getTransactionsBySender(sender);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No such transactions");
        }
        return transactions
                .stream()
                .sorted(Comparator
                        .comparingDouble(Transaction::getAmount)
                ).collect(Collectors.toList());
    }

    public List<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactions = Database.getTransactionsByReceiver(receiver);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No such transactions");
        }
        return transactions
                .stream()
                .sorted((trans1, trans2) -> {
                            int compare = Double.compare(trans1.getAmount(), trans2.getAmount());
                            if (compare == 0) {
                                compare = Integer.compare(trans2.getId(), trans1.getId());
                            }
                            return compare;
                        }
                ).collect(Collectors.toList());
    }

    public List<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        List<Transaction> transactions = Database.getTransactionsByStatus(status);
        return transactions
                .stream()
                .filter(transaction -> transaction.getAmount() <= amount)
                .collect(Collectors.toList());
    }

    public List<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactions = Database.getTransactionsBySender(sender);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No such senders");
        }
        return transactions
                .stream()
                .filter(transaction -> transaction.getAmount() > amount)
                .sorted((trans1, trans2) -> Double.compare(trans2.getAmount(), trans1.getAmount()))
                .collect(Collectors.toList());
    }

    public List<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactions = Database.getTransactionsByReceiver(receiver);
        transactions =  transactions.stream()
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() < hi)
                .sorted((trans1, trans2) -> {
                            int compare = Double.compare(trans1.getAmount(), trans2.getAmount());
                            if (compare == 0) {
                                compare = Integer.compare(trans2.getId(), trans1.getId());
                            }
                            return compare;
                        }
                ).collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No such senders");
        }
        return transactions;
    }

    public List<Transaction> getAllInAmountRange(double lo, double hi) {
        List<Transaction> transactions = Database.getAllTransactions();
        return transactions
                .stream()
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() <= hi)
                .collect(Collectors.toList());
    }
}
