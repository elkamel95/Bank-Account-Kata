package kata.services;

import kata.dao.Account;
import kata.dao.Statement;
import kata.exception.TransactionException;

import java.util.logging.Logger;

/***
 * This method allowed to manage the transaction (deposit,withdrawal,history).
 * */
public class TransactionService {
    Logger logger = Logger.getLogger(TransactionService.class.getName());

    /***
     * This method allowed you to deposit an amount and add it to the statement.
     *
     * */
    public synchronized void deposit(Account account, double amount) {
        account.addBalance(amount);
        account.createStatement(amount);


    }

    /***
     * This method allowed create transaction withdrawal.
     * Withdrawal an amount dd it to the statement
     *Checking the min balance
     * */
    public synchronized void withdrawal(Account account, double amount) throws TransactionException {
        if (Account.MINBALANCE >= account.getBalance() - amount) {
            throw new TransactionException("Discount Amount is less than the balance");

        }
        if (account.getBalance() < amount && Account.MINBALANCE >= account.getBalance() - amount) {
            throw new TransactionException("Discount Amount is Greater than due balance ");

        }
        account.subBalance(amount);
        account.createStatement(amount);

    }

    /***
     * This method allowed to  print the account statement .
     * */
    public void printStatement(Account account) {
        logger.info("Date-------------------------| Amount--|---- balance");

        for (Statement h : account.getAccountStatement()) {
            logger.info(h.getDate() + " | ---" + h.getAmount() + "--- | " + h.getBalance());

        }
    }
}
