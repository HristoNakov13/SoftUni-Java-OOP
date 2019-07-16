import java.util.List;

public interface Chainblock {

    int getCount();

    void add(Transaction transaction);

    boolean contains(Transaction transaction);

    boolean contains(int id);

    void changeTransactionStatus(int id, TransactionStatus newStatus);

    void removeTransactionById(int id);

    Transaction getById(int id);

    List<Transaction> getByTransactionStatus(TransactionStatus status);

    List<Transaction> getAllSendersWithTransactionStatus(TransactionStatus status);

    List<Transaction> getAllReceiversWithTransactionStatus(TransactionStatus status);

    List<Transaction> getAllOrderedByAmountDescendingThenById();

    List<Transaction> getBySenderOrderedByAmountDescending(String sender);

    List<Transaction> getByReceiverOrderedByAmountThenById(String receiver);

    List<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount);

    List<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount);

    List<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi);

    List<Transaction> getAllInAmountRange(double lo, double hi);

}
