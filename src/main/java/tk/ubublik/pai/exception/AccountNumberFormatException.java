package tk.ubublik.pai.exception;

public class AccountNumberFormatException extends RuntimeException {

    public AccountNumberFormatException() {
    }

    public AccountNumberFormatException(String message) {
        super(message);
    }

    public AccountNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
