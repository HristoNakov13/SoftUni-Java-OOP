package bankAccount;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankAccountTest {
    private static final String INVALID_NAME_LENGTH_GREATER_THAN_TWENTY_FIVE_CHARS = "ASDFQWERTQWERQWERQWERQWERQWERQWE";
    private static final String INVALID_NAME_LENGTH_LESS_THAN_THREE_CHARS = "as";
    private static final BigDecimal INVALID_AMOUNT = BigDecimal.valueOf(-1);
    private static final BigDecimal TEST_AMOUNT = BigDecimal.valueOf(50);
    private static final String TEST_NAME = "Pesho";
    private BankAccount bankAccount;

    @Before
    public void setUp() {
        this.bankAccount = new BankAccount(TEST_NAME, TEST_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenOpeningAccountWithNameLessThanThreeCharsLong() {
        this.bankAccount = new BankAccount(INVALID_NAME_LENGTH_LESS_THAN_THREE_CHARS, TEST_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenOpeningAccountWithNameGreaterThanTwentyFiveCharsLong() {
        this.bankAccount = new BankAccount(INVALID_NAME_LENGTH_GREATER_THAN_TWENTY_FIVE_CHARS, TEST_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenOpeningAccountWithNegativeAmountOfMoney() {
        this.bankAccount = new BankAccount(TEST_NAME, INVALID_AMOUNT);
    }

    @Test
    public void getNameShouldReturnCorrectValue() {
        String actual = this.bankAccount.getName();

        Assert.assertEquals(TEST_NAME, actual);
    }

    @Test
    public void getBalanceShouldReturnAccurateAmount() {
        BigDecimal actual = this.bankAccount.getBalance();

        Assert.assertEquals(TEST_AMOUNT, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowWhenDepositingNegativeAmount() {
        this.bankAccount.deposit(INVALID_AMOUNT);
    }

    @Test
    public void shouldDepositRightAmount() {
        this.bankAccount.deposit(TEST_AMOUNT);

        BigDecimal actualBalance = this.bankAccount.getBalance();
        BigDecimal expected = TEST_AMOUNT.add(TEST_AMOUNT);

        Assert.assertEquals(expected, actualBalance);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowWhenWithdrawingNegativeAmount() {
        this.bankAccount.withdraw(INVALID_AMOUNT);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowWhenWithdrawingGreaterAmountThanCurrentBalance() {
        this.bankAccount.withdraw(TEST_AMOUNT.add(TEST_AMOUNT));
    }

    @Test
    public void shouldWithdrawCorrectAmountOfMoney() {
        BigDecimal actual = this.bankAccount.withdraw(TEST_AMOUNT);

        Assert.assertEquals(TEST_AMOUNT, actual);
    }

    @Test
    public void shouldSubtractFromBalanceWhenWithdrawingMoney() {
        BigDecimal actualBalance = this.bankAccount.getBalance().subtract(this.bankAccount.withdraw(TEST_AMOUNT));
        BigDecimal expected = this.bankAccount.getBalance();

        Assert.assertEquals(expected, actualBalance);
    }


}