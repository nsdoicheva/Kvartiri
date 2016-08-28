package exceptions;

public class NoMessagesEception extends Exception {

	private static final long serialVersionUID = -6690707603534797347L;

	public NoMessagesEception() {
		super();
	}

	public NoMessagesEception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoMessagesEception(String message, Throwable cause) {
		super(message, cause);
	}

	public NoMessagesEception(String message) {
		super(message);
	}

	public NoMessagesEception(Throwable cause) {
		super(cause);
	}
	
	

}
