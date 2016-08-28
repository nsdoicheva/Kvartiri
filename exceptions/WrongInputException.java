package exceptions;

public class WrongInputException extends Exception {

	private static final long serialVersionUID = -8406214775670114138L;

	public WrongInputException() {
		super();
	}

	public WrongInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WrongInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongInputException(String message) {
		super(message);
	}

	public WrongInputException(Throwable cause) {
		super(cause);
	}
	
	

}
