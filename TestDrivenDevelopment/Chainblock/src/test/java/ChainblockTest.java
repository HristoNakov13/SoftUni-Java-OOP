import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChainblockTest {
    private static final Transaction DEFAULT_TRANSACTION = new TransactionImpl(
            413,
            TransactionStatus.SUCCESSFUL,
            "Ivan",
            "PESHO",
            100.4);

    private static final String FROM_IVAN = "Ivan";
    private static final String TO_PESHO = "PESHO";
    private static final int TRANSACTION_ID = 523412345;
    private static final double TRANSFER_AMOUNT = 540.5;
    private static final TransactionStatus TRANSACTION_STATUS = TransactionStatus.SUCCESSFUL;
    private static final int INVALID_ID = 45000012;
    private static final TransactionStatus INVALID_STATUS = TransactionStatus.ABORTED;
    private static final String INVALID_SENDER = "Invalid";
    private Chainblock chainblock;
    private Transaction TEST_TRANSACTION;


    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.TEST_TRANSACTION = new TransactionImpl(TRANSACTION_ID, TRANSACTION_STATUS, FROM_IVAN, TO_PESHO, TRANSFER_AMOUNT);
    }

    @Test
    public void addsTransactionToRepository() {
        this.chainblock.add(this.TEST_TRANSACTION);
        Assert.assertTrue(Database.contains(this.TEST_TRANSACTION));
    }

    @Test
    public void containsByIDReturnsTrueForTransactionWhichIsPresent() {
        this.chainblock.add(TEST_TRANSACTION);
        boolean actual = this.chainblock.contains(TRANSACTION_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void containsByIDReturnsFalseForTransactionNotPresent() {
        boolean actual = this.chainblock.contains(INVALID_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void containsByTransactionReturnsTrueForTransactionWhichIsPresent() {
        this.chainblock.add(TEST_TRANSACTION);
        boolean actual = this.chainblock.contains(TEST_TRANSACTION);
        Assert.assertTrue(actual);
    }

    @Test
    public void containsByTransactionReturnsFalseForTransactionNotPresent() {
        boolean actual = this.chainblock.contains(TEST_TRANSACTION);
        Assert.assertTrue(actual);
    }

    @Test
    public void returnsCorrectCountOfTransactions() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        int actual = this.chainblock.getCount();
        Assert.assertEquals(2, actual);
    }

    @Test
    public void changesStatusofTransactionByID() {
        this.chainblock.add(TEST_TRANSACTION);
        TransactionStatus actual = TransactionStatus.ABORTED;
        this.chainblock.changeTransactionStatus(TRANSACTION_ID, actual);
        Assert.assertEquals(TRANSACTION_STATUS, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenAttemptChangeStatusOfTransactionByInvalidID() {
        this.chainblock.add(TEST_TRANSACTION);
        TransactionStatus actual = TransactionStatus.ABORTED;
        this.chainblock.changeTransactionStatus(INVALID_ID, actual);
    }

    @Test
    public void getsCorrectTransactionByID() {
        this.chainblock.add(TEST_TRANSACTION);
        Transaction actual = this.chainblock.getById(TRANSACTION_ID);
        Assert.assertEquals(TEST_TRANSACTION, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenAttemptGetByInvalidID() {
        this.chainblock.add(TEST_TRANSACTION);
        Transaction actual = this.chainblock.getById(INVALID_ID);
    }

    @Test
    public void getByTransactionStatusReturnsCollectionOfTransactionsOrderedByAmount() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Transaction> actual = this.chainblock.getByTransactionStatus(TRANSACTION_STATUS);
        Transaction actualHighestAmount = actual.get(0);
        Transaction actualLowestAmount = actual.get(1);

        Assert.assertEquals(TEST_TRANSACTION, actualHighestAmount);
        Assert.assertEquals(DEFAULT_TRANSACTION, actualLowestAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTransactionStatusThrowsWhenNoTransactionsWithSuchStatusAdded() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Transaction> actual = this.chainblock.getByTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllSendersWithTransactionStatusReturnsHistoryOfSendersSearchedByStatus() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Username> senders = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION_STATUS);
        Username sender = senders.get(0);
        String senderName = sender.getName();
        Assert.assertEquals(FROM_IVAN, senderName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoSenderHasTransactionsWithSuchStatus() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Username> senders = this.chainblock.getAllSendersWithTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllReceiversWithTransactionStatusReturnsHistoryOfSendersSearchedByStatus() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Username> receivers = this.chainblock.getAllReceiversWithTransactionStatus(TRANSACTION_STATUS);
        Username receiver = receivers.get(0);
        String senderName = receiver.getName();
        Assert.assertEquals(FROM_IVAN, senderName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoReceiverHasTransactionsWithSuchStatus() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        List<Username> receiver = this.chainblock.getAllReceiversWithTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllTransactionsOrderedByAmountDescendingThenById() {
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
        Transaction sameAmountAsTestDiffID = new TransactionImpl(TRANSACTION_ID - 2, TRANSACTION_STATUS, FROM_IVAN, TO_PESHO, TRANSFER_AMOUNT);
        List<Transaction> transactions = this.chainblock.getAllOrderedByAmountDescendingThenById();
        Transaction actualFirst = transactions.get(0);
        Transaction actualSecond = transactions.get(1);
        Transaction actualThird = transactions.get(2);

        Assert.assertEquals(sameAmountAsTestDiffID, actualFirst);
        Assert.assertEquals(TEST_TRANSACTION, actualSecond);
        Assert.assertEquals(DEFAULT_TRANSACTION, actualThird);

    }

    @Test
    public void getTransactionsBySenderOrderedByAmountDescending() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);

        List<Transaction> transactions = this.chainblock.getBySenderOrderedByAmountDescending(FROM_IVAN);
        Transaction actualLowestAmount = transactions.get(0);
        Transaction actualHighest = transactions.get(1);

        Assert.assertEquals(TEST_TRANSACTION, actualHighest);
        Assert.assertEquals(DEFAULT_TRANSACTION, actualLowestAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGetTransactionsByNonexistentSender() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getBySenderOrderedByAmountDescending(INVALID_SENDER);
    }

    @Test
    public void returnAllTransactionsByStatusAndAmountLowerThanProvided() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByTransactionStatusAndMaximumAmount(TRANSACTION_STATUS, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();
        Transaction actual = transactions.get(0);

        Assert.assertEquals(DEFAULT_TRANSACTION, actual);
        Assert.assertEquals(1, actualCountTransactions);
    }

    @Test
    public void returnsEmptyListIfNoTransactionsFoundWithSuchStatus() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByTransactionStatusAndMaximumAmount(INVALID_STATUS, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();

        Assert.assertEquals(0, actualCountTransactions);
    }

    @Test
    public void getTransactionsBySenderAndMinimumAmountDescending() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getBySenderAndMinimumAmountDescending(FROM_IVAN, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();
        Transaction actual = transactions.get(0);

        Assert.assertEquals(TEST_TRANSACTION, actual);
        Assert.assertEquals(1, actualCountTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoSuchSenderFound() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getBySenderAndMinimumAmountDescending(INVALID_SENDER, TRANSFER_AMOUNT - 1);
    }

    @Test
    public void returnsTransactionsByReceiverAndAmountBetweenLowInclusiveAndHighExclusiveOrderedByAmountDescendingThenId() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, TRANSFER_AMOUNT, TRANSFER_AMOUNT + 1);
        Transaction actual = transactions.get(0);
        int actualTransactionsCount = transactions.size();
        Assert.assertEquals(1, actualTransactionsCount);
        Assert.assertEquals(TEST_TRANSACTION, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoTransactionsFoundByRequirements() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, TRANSFER_AMOUNT + 1, TRANSFER_AMOUNT + 20);
    }

    @Test
    public void returnsAllTransactionsWithinAmountRangeOrderedByInsertion() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, 0, TRANSFER_AMOUNT + 1);
        int transactionsCount = transactions.size();
        Transaction actualFirstInserted = transactions.get(0);
        Transaction actualSecondInserted = transactions.get(1);

        Assert.assertEquals(2, transactionsCount);
        Assert.assertEquals(DEFAULT_TRANSACTION, actualFirstInserted);
        Assert.assertEquals(TEST_TRANSACTION, actualSecondInserted);
    }

    @Test
    public void returnsEmptyCollectionWhenNoTransactionsMatchSearchParameters() {
        this.chainblock.add(DEFAULT_TRANSACTION);
        this.chainblock.add(TEST_TRANSACTION);
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, TRANSFER_AMOUNT + 1, TRANSFER_AMOUNT + 1);
        int actualTransactionsCount = transactions.size();

        Assert.assertEquals(0, actualTransactionsCount);
    }
}
