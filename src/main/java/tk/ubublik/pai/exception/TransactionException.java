package tk.ubublik.pai.exception;

import tk.ubublik.pai.validation.Errors;

public class TransactionException extends RuntimeException {

	private Errors errors;

	public TransactionException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
