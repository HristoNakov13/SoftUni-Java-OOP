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

    private int getId() {
        return id;
    }

    private TransactionStatus getStatus() {
        return status;
    }

    private String getFrom() {
        return from;
    }

    private String getTo() {
        return to;
    }

    private double getAmount() {
        return amount;
    }

    public int compareTo(TransactionImpl o) {
        return this.getId() - o.getId();
    }
}
