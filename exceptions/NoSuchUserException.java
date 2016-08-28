package exceptions;

public class NoSuchUserException extends Exception {

	private static final long serialVersionUID = -2018135070847284161L;

	public NoSuchUserException() {
		super();
	}

	public NoSuchUserException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NoSuchUserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoSuchUserException(String arg0) {
		super(arg0);
	}

	public NoSuchUserException(Throwable arg0) {
		super(arg0);
	}

	
}
