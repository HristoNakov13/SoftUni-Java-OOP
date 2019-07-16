public class TransactionImpl implements Transaction, Comparable<TransactionImpl>{

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.status = transactionStatus;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public int compareTo(TransactionImpl o) {
        return this.getId() - o.getId();
    }
}
