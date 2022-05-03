package dao;

import java.util.Date;

/**
 * This class represents the information about the account statement .
 */
public class Statement {
    private Date date;
    private double amount;
    private double balance = 0.0;

    /**
     * Get the transaction's date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the transaction's date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the transaction's amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the transaction's amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the transaction's balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Set the transaction's balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
