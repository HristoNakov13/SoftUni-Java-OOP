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
    private static final String INVALID_NAME = "Invalid";
    private Chainblock chainblock;
    private Transaction TEST_TRANSACTION;


    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.TEST_TRANSACTION = new TransactionImpl(TRANSACTION_ID, TRANSACTION_STATUS, FROM_IVAN, TO_PESHO, TRANSFER_AMOUNT);
        this.chainblock.add(TEST_TRANSACTION);
        this.chainblock.add(DEFAULT_TRANSACTION);
    }

    @Test
    public void addsTransactionToRepository() {
        Assert.assertTrue(Database.contains(this.TEST_TRANSACTION));
    }

    @Test
    public void containsByIDReturnsTrueForTransactionWhichIsPresent() {
        boolean actual = this.chainblock.contains(TRANSACTION_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void containsByIDReturnsFalseForTransactionNotPresent() {
        boolean actual = this.chainblock.contains(INVALID_ID);
        Assert.assertFalse(actual);
    }

    @Test
    public void containsByTransactionReturnsTrueForTransactionWhichIsPresent() {
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
        int actual = this.chainblock.getCount();
        Assert.assertEquals(2, actual);
    }

    @Test
    public void changesStatusofTransactionByID() {
        TransactionStatus actual = TransactionStatus.SUCCESSFUL;
        this.chainblock.changeTransactionStatus(TRANSACTION_ID, actual);
        Assert.assertEquals(TEST_TRANSACTION.getStatus(), actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenAttemptChangeStatusOfTransactionByInvalidID() {
        TransactionStatus actual = TransactionStatus.ABORTED;
        this.chainblock.changeTransactionStatus(INVALID_ID, actual);
    }

    @Test
    public void getsCorrectTransactionByID() {
        Transaction actual = this.chainblock.getById(TRANSACTION_ID);
        Assert.assertEquals(TEST_TRANSACTION.getId(), actual.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenAttemptGetByInvalidID() {
        Transaction actual = this.chainblock.getById(INVALID_ID);
    }

    @Test
    public void getByTransactionStatusReturnsCollectionOfTransactionsOrderedByAmount() {
        List<Transaction> actual = this.chainblock.getByTransactionStatus(TRANSACTION_STATUS);
        Transaction actualHighestAmount = actual.get(0);
        Transaction actualLowestAmount = actual.get(1);

        Assert.assertEquals(TEST_TRANSACTION.getId(), actualHighestAmount.getId());
        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actualLowestAmount.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTransactionStatusThrowsWhenNoTransactionsWithSuchStatusAdded() {
        List<Transaction> actual = this.chainblock.getByTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllSendersWithTransactionStatusReturnsHistoryOfSendersSearchedByStatus() {
        List<Transaction> senders = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION_STATUS);
        Transaction sender = senders.get(0);
        String senderName = sender.getFrom();
        Assert.assertEquals(FROM_IVAN, senderName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoSenderHasTransactionsWithSuchStatus() {
        List<Transaction> senders = this.chainblock.getAllSendersWithTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllReceiversWithTransactionStatusReturnsHistoryOfSendersSearchedByStatus() {
        List<Transaction> receivers = this.chainblock.getAllReceiversWithTransactionStatus(TRANSACTION_STATUS);
        Transaction receiver = receivers.get(0);
        String senderName = receiver.getTo();
        Assert.assertEquals(TO_PESHO, senderName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoReceiverHasTransactionsWithSuchStatus() {
        List<Transaction> receiver = this.chainblock.getAllReceiversWithTransactionStatus(INVALID_STATUS);
    }

    @Test
    public void getAllTransactionsOrderedByAmountDescendingThenById() {

        List<Transaction> transactions = this.chainblock.getAllOrderedByAmountDescendingThenById();
        Transaction actualFirst = transactions.get(0);
        Transaction actualSecond = transactions.get(1);

        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actualFirst.getId());
        Assert.assertEquals(TEST_TRANSACTION.getId(), actualSecond.getId());

    }

    @Test
    public void getTransactionsBySenderOrderedByAmountDescending() {

        List<Transaction> transactions = this.chainblock.getBySenderOrderedByAmountDescending(FROM_IVAN);
        Transaction actualLowestAmount = transactions.get(0);
        Transaction actualHighest = transactions.get(1);

        Assert.assertEquals(TEST_TRANSACTION.getId(), actualHighest.getId());
        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actualLowestAmount.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGetTransactionsByNonexistentSender() {
        List<Transaction> transactions = this.chainblock.getBySenderOrderedByAmountDescending(INVALID_NAME);
    }

    @Test
    public void getTransactionsByReceiverOrderedByAmountDescendingThenIDAscending() {

        List<Transaction> transactions = this.chainblock.getByReceiverOrderedByAmountThenById(TO_PESHO);
        Transaction actualHighest = transactions.get(0);
        Transaction actualLowestAmount = transactions.get(1);

        Assert.assertEquals(TEST_TRANSACTION.getId(), actualLowestAmount.getId());
        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actualHighest.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGetTransactionsByNonexistentReceiver() {
        List<Transaction> transactions = this.chainblock.getByReceiverOrderedByAmountThenById(INVALID_NAME);
    }

    @Test
    public void returnAllTransactionsByStatusAndAmountLowerThanProvided() {
        List<Transaction> transactions = this.chainblock.getByTransactionStatusAndMaximumAmount(TRANSACTION_STATUS, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();
        Transaction actual = transactions.get(0);

        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actual.getId());
        Assert.assertEquals(1, actualCountTransactions);
    }

    @Test
    public void returnsEmptyListIfNoTransactionsFoundWithSuchStatus() {
        List<Transaction> transactions = this.chainblock.getByTransactionStatusAndMaximumAmount(INVALID_STATUS, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();

        Assert.assertEquals(0, actualCountTransactions);
    }

    @Test
    public void getTransactionsBySenderAndMinimumAmountDescending() {
        List<Transaction> transactions = this.chainblock.getBySenderAndMinimumAmountDescending(FROM_IVAN, TRANSFER_AMOUNT - 1);
        int actualCountTransactions = transactions.size();
        Transaction actual = transactions.get(0);

        Assert.assertEquals(TEST_TRANSACTION.getId(), actual.getId());
        Assert.assertEquals(1, actualCountTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoSuchSenderFound() {
        List<Transaction> transactions = this.chainblock.getBySenderAndMinimumAmountDescending(INVALID_NAME, TRANSFER_AMOUNT - 1);
    }

    @Test
    public void returnsTransactionsByReceiverAndAmountBetweenLowInclusiveAndHighExclusiveOrderedByAmountDescendingThenId() {
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, TRANSFER_AMOUNT, TRANSFER_AMOUNT + 1);
        Transaction actual = transactions.get(0);
        int actualTransactionsCount = transactions.size();
        Assert.assertEquals(1, actualTransactionsCount);
        Assert.assertEquals(TEST_TRANSACTION.getId(), actual.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNoTransactionsFoundByRequirements() {
        List<Transaction> transactions = this.chainblock.getByReceiverAndAmountRange(TO_PESHO, TRANSFER_AMOUNT + 1, TRANSFER_AMOUNT + 20);
    }

    @Test
    public void returnsAllTransactionsWithinAmountRangeOrderedByInsertion() {
        List<Transaction> transactions = this.chainblock.getAllInAmountRange(0, TRANSFER_AMOUNT + 1);
        int transactionsCount = transactions.size();
        Transaction actualFirstInserted = transactions.get(0);
        Transaction actualSecondInserted = transactions.get(1);

        Assert.assertEquals(2, transactionsCount);
        Assert.assertEquals(TEST_TRANSACTION.getId(), actualFirstInserted.getId());
        Assert.assertEquals(DEFAULT_TRANSACTION.getId(), actualSecondInserted.getId());
    }

    @Test
    public void returnsEmptyCollectionWhenNoTransactionsMatchSearchParameters() {
        List<Transaction> transactions = this.chainblock.getAllInAmountRange(TRANSFER_AMOUNT + 1, TRANSFER_AMOUNT + 1);
        int actualTransactionsCount = transactions.size();

        Assert.assertEquals(0, actualTransactionsCount);
    }
}
