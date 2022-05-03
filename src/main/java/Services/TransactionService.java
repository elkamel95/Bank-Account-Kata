package Services;

import dao.Account;
import dao.Statement;

/***
 * This method allowed to manage the transaction (deposit,withdrawal,history).
 * */
public class TransactionService {
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
    public synchronized void withdrawal(Account account, double amount) throws Exception {
        if (Account.minBalance >= account.getBalance() - amount) {
            throw new Exception("min balance ");

        }
        if (account.getBalance() < amount && Account.minBalance >= account.getBalance() - amount) {
            throw new Exception("Discount Amount is Greater than due balance ");

        }
        account.subBalance(amount);
        account.createStatement(amount);

    }

    /***
     * This method allowed to  print the account statement .
     * */
    public void printStatement(Account account) {
        System.out.println("Date-------------------------| Amount--|---- balance");

        for (Statement h : account.getAccountStatement()) {
            System.out.println(h.getDate() + " | ---" + h.getAmount() + "--- | " + h.getBalance());

        }
    }
}
