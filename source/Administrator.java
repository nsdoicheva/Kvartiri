package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.NoSuchUserException;

public class Administrator extends User {
	private String adminUserName;
	private static Map<String, User> allUsers;

	private Administrator(String name, String phoneNumber, String address, String email) {
		super(name, phoneNumber, address, email);
		this.adminUserName = "admin";
		Administrator.allUsers = new HashMap<String, User>();
	}

	private static Administrator instance = new Administrator("Admin", "0878372222", "Sofia", "admin@gmail.com");

	public static Administrator getInstance() {
		return instance;
	}

	public static boolean banUser(User user) {
		for (String key : allUsers.keySet()) {
			if (key.equals(user.getUserName())) {
				allUsers.remove(key);
				return true;
			} else {
				try {
					throw new NoSuchUserException("This user doesn't exist in our database.");
				} catch (NoSuchUserException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public static boolean deleteAd(Ad ad, User user) {
		if (ad != null && user != null) {
			for (String key : allUsers.keySet()) {
				if (key.equals(user.getUserName())) {
					user.deleteAd(ad);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean addUser(User user) {
		if (user != null) {
			allUsers.put(user.getUserName(), user);
			return true;
		}else{
			return false;
		}

	}

	public static List<String> listAllUsers() {
		List<String> listAllUsers= new ArrayList<String>();
		for (Entry<String, User> user : allUsers.entrySet()) {
			listAllUsers.add(user.getKey());
		}
		return listAllUsers;
	}

}
