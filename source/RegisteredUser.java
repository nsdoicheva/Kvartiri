package source;

import exceptions.WeakPasswordException;

public class RegisteredUser extends User {

	private boolean isVip;

	public RegisteredUser(String name, String userName, String password, String phoneNumber, String address,
			String email)throws WeakPasswordException {
		super(name, userName, password, phoneNumber, address, email);
	}
}
