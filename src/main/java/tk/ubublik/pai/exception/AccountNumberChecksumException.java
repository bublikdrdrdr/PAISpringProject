package tk.ubublik.pai.exception;

public class AccountNumberChecksumException extends RuntimeException {

    public AccountNumberChecksumException() {
    }

    public AccountNumberChecksumException(String message) {
        super(message);
    }

    public AccountNumberChecksumException(String message, Throwable cause) {
        super(message, cause);
    }
}
