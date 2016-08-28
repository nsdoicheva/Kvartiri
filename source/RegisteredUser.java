package source;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredUser extends User {

	private boolean isVip;

	public RegisteredUser(String name, String userName, String password, String phoneNumber, String address,
			String email) {
		super(name, userName, password, phoneNumber, address, email);

	}
}
