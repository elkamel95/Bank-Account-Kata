package kata.exception;
/**
 * this method allowed to create a custom exception
 * */
public class TransactionException extends Exception {
        public TransactionException(String errorMessage) {
            super(errorMessage);
        }
    }

