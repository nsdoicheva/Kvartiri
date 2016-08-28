package source;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.NoSuchUserException;

public class Administrator extends User {
	private String adminUserName;
    private static Map<String, User> allUsers;
	//ArrayList<Ad> allAds = new ArrayList<Ad>(); nqma nujda ot vsichki obqvi	
	

	private Administrator(String name, String phoneNumber, String address, String email) {
		super(name, phoneNumber, address, email);
		this.adminUserName = "admin";
		Administrator.allUsers = new HashMap<String, User >();
	}

	private static Administrator instance = new Administrator("Admin", "0878372222", "Sofia", "admin@gmail.com");

 
	// Get the only object available
	public static Administrator getInstance() {
		return instance;
	}

	
	public static void banUser(User user) {
		for (String key : allUsers.keySet()) {
			if (key.equals(user.getUserName())) {
				allUsers.remove(key);
			} else {
				try {
					throw new NoSuchUserException("This user doesn't exist in our database.");
				} catch (NoSuchUserException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	public static void deleteAd(Ad ad, User user) {
		for (String key : allUsers.keySet()) {
			if (key.equals(user.getUserName())) {
				user.deleteAd(ad);
			}
		}
	}
	
	public static void addUser(User user) {
		if(user != null ) {
			allUsers.put(user.getUserName(), user);
		}
		
	}
	
	public static void listAllUsers(){
		for (Entry<String, User> user : allUsers.entrySet()) {
			System.out.println("User: "+ user.getKey());
		}
	}


}
