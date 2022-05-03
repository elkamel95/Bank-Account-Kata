package kata.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents the information about the account user.
 */
public class Account {
    private String rib;
    private String firstName;
    private String lastName;
    private double balance = 0.0;
    public static final double MINBALANCE = -200;

    private List<Statement> accountStatement = new ArrayList<>();

    public Account(String rib, String firstName, String lastName) {
        this.rib = rib;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public double getBalance() {
        return balance;
    }

    /**
     * This method be able to create the statement list.
     */
    public List<Statement> createStatement(double amount) {

        Statement statement = new Statement();
        statement.setAmount(amount);
        statement.setBalance(this.getBalance());
        statement.setDate(new Date());
        accountStatement.add(statement);

        return accountStatement;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * This method allowed to add an amount to the account balance.
     */
    public double addBalance(double amount) {
        this.setBalance(this.getBalance() + amount);
        return this.getBalance();
    }

    /**
     * This method allows subtracting an amount from the account balance.
     */
    public double subBalance(double amount) {
        this.setBalance(this.getBalance() - amount);

        return this.getBalance();
    }


    /**
     * This method allows to get the account statement list.
     */
    public List<Statement> getAccountStatement() {
        return accountStatement;
    }
    /**
     * This method allows to get the account rib.
     */
    public String getRib() {
        return rib;
    }

    /**
     * This method allows to get the account user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method allows to get the account user's last name.
     */
    public String getLastName() {
        return lastName;
    }

}
