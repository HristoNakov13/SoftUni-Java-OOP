import java.util.List;

public interface Chainblock extends Iterable<Transaction> {

    int getCount();

    void add(Transaction transaction);

    boolean contains(Transaction transaction);

    boolean contains(int id);

    void changeTransactionStatus(int id, TransactionStatus newStatus);

    void removeTransactionById(int id);

    Transaction getById(int id);

    List<Transaction> getByTransactionStatus(TransactionStatus status);

    Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status);

    Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status);

    List<Transaction> getAllOrderedByAmountDescendingThenById();

    List<Transaction> getBySenderOrderedByAmountDescending(String sender);

    Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver);

    List<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount);

    List<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount);

    List<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi);

    Iterable<Transaction> getAllInAmountRange(double lo, double hi);

}
