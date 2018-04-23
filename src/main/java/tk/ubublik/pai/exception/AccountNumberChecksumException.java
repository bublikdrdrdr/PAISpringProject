package tk.ubublik.pai.exception;

public class AccountNumberChecksumException extends Exception {

    public AccountNumberChecksumException() {
    }

    public AccountNumberChecksumException(String message) {
        super(message);
    }

    public AccountNumberChecksumException(String message, Throwable cause) {
        super(message, cause);
    }
}
