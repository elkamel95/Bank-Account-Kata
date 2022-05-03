import Services.TransactionService;
import dao.Account;
import dao.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    private static Instant startedAt;

    static TransactionService t;
    static Account account;

    @BeforeEach
    public void initAccount() {
        //Arrange
        System.out.println("Appel avant chaque test");

        String rib = "5454545454";
        String firstName = "user1";
        String lastName = "Test";
        account = new Account(rib, firstName, lastName);
        account.setBalance(1.0);
        assertTrue(account.getBalance() == 1.0);
        assertAll("Account",
                () -> assertEquals("user1", account.getFirstName()),
                () -> assertEquals("Test", account.getLastName()),
                () -> assertEquals("5454545454", account.getRib())
        );


    }

    @Test
    public final void addBalanceAccountTest() {
        //act
        account.addBalance(100);
        //assert
        assertTrue(account.getBalance() > 0, "The deposit transaction failed");

    }

    @Test
    public final void subBalanceAccountTest() {
        //act
        account.subBalance(100);
        //assert
        assertEquals(account.getBalance(),-99.0, "The deposit transaction failed");

    }

    @Test
    public final void initStatement() {
        //act
        Statement s = new Statement();
//act
        s.setAmount(100);
        s.setBalance(100 + s.getAmount());
        s.setDate(new Date());
//assert
        assertTrue(s.getBalance() == 200, "The addition is not correct");
        assertTrue(s.getDate().before(new Date()) || s.getDate().equals(new Date()), "The statement date is not correct");

    }

    @Test
    public final void addStatementTest() {
        //act
        List<Statement> statementAccount = account.createStatement(100);
        //assert
        assertTrue(!statementAccount.isEmpty(), "The statement account is empty");

    }

    @BeforeAll
    public static void initTransaction() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();

        //Arrange
        t = new TransactionService();
        //act

        //assert

        assertNotNull(t);


    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 30.10, 5.25, 3040, 150})
    public void accountDepositTest(double amount) {

        //act
        double balance = account.getBalance();
        t.deposit(account, amount);
        //assert
        assertTrue(balance < account.getBalance(), "The deposit transaction failed");


    }

    @ParameterizedTest
    @ValueSource(doubles = {300, 300.10, 500.25, 220, 320})
    public final void withdrawal(double amount) throws Exception {
        try {
            t.withdrawal(account, amount);
            fail("Discount Amount is Greater than due balance");

        } catch (Exception e) {
            t.deposit(account, amount);
            double balance = account.getBalance();

            t.withdrawal(account, 40);
            assertTrue(balance >= account.getBalance(), "The withdrawal transaction failed");

        }


    }


    @Test
    void printStatementTest() throws Exception {
        t.deposit(account, 100);
        t.deposit(account, 100);
        t.deposit(account, 100);

        t.withdrawal(account, 40);
        t.withdrawal(account, 40);
        t.withdrawal(account, 40);
        try {
            t.printStatement(account);

        } catch (Exception e) {
            fail(e.getMessage());

        }
    }
    @AfterAll
    static public void showTestDuration() {
        System.out.println("Appel après tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }
}
