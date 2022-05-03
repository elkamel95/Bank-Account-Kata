import services.TransactionService;
import dao.Account;

/**
 * This class represent the main application , allowd to exexcute the users stories
 * @author Elkamel Fahmi
 * @since 1.0
 */
public class KataMain {
    public  static  void  main(String [] args) throws Exception {
        TransactionService transaction = new TransactionService();
        //create account for the user1
        Account user1= new  Account("123456789123TF","Fahmi","Elkamel");
        transaction.deposit(user1,100);
        transaction.withdrawal(user1,90);

        transaction.deposit(user1,101);

        transaction.deposit(user1,200);
        transaction.withdrawal(user1,40);


        transaction.printStatement(user1);
    }
}
