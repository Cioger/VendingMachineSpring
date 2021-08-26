package vendingMachine.TheVendingMachine.exceptions;

public class SoldOutException extends RuntimeException {

	public SoldOutException() {
	}

	public SoldOutException(String message) {
		super(message);
	}

	public SoldOutException(Throwable cause) {
		super(cause);
	}

	public SoldOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoldOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
