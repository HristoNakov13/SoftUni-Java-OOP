public interface Transaction {
    int getId();

    void setTransactionStatus(TransactionStatus transactionStatus);

    String getFrom();

    String getTo();

    double getAmount();

    TransactionStatus getStatus();
}
