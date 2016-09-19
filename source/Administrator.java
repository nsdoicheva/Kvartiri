package source;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.undo.CannotUndoException;

import exceptions.FailedConnectionException;
import exceptions.NoSuchUserException;
import exceptions.WeakPasswordException;
import exceptions.WrongInputException;

public class Administrator extends User {
	private static final String SELECT_ALL_INFO_FOR_USERS="SELECT name,  username, password, phoneNumber, address, email from users;";
	
	private String adminUserName;
    private static Map<String, User> allUsers = new ConcurrentHashMap<String, User >();
	//ArrayList<Ad> allAds = new ArrayList<Ad>(); nqma nujda ot vsichki obqvi	
	
    public Administrator(String name, String userName, String password, String phoneNumber, String address,
			String email) throws WeakPasswordException {
		super(name, userName, password, phoneNumber, address, email);
	}
	

	//eager loading - zarejda se oshte kogato pusnesh prilojenieto
	private static Administrator instance ;

 
	// Get the only object available, synchronized podigurqva da ne se napravqt nqkolko admina
	public static synchronized Administrator getInstance() {
		if (instance == null) {
				try {
					instance = new Administrator("Admin", "admin2", "Admin1234", "0878372313", "Studentski", "admin@gmail.com");
				} catch (WeakPasswordException e) {
					e.printStackTrace();
				}
		}
		return instance;
	}

	public boolean validateLogin(String username, String password) {
		if(!(allUsers.containsKey(username))) {
			return false;
		}
	   return allUsers.get(username).getPassword().equals(password);			
	}
	
	public void registerUser(String name, String username, String password,  String phone, String address, String email) throws WeakPasswordException, UserAlreadyExistsException{
		User user = new RegisteredUser(name, username, password, phone, address, email);
		allUsers.put(username, user);
		UserDAO.getInstance().saveUser(user);
	}
	
	public Set<User> getAllUsersFromDB() {
		Set<User> users = new HashSet<User>();
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_INFO_FOR_USERS);
			
			while(resultSet.next()) {
				try {
					users.add(new RegisteredUser(resultSet.getString("name"),resultSet.getString("username"),
							resultSet.getString("password"), resultSet.getString("phoneNumber"),
							resultSet.getString("address"),	resultSet.getString("email")
							) );
				} catch (WeakPasswordException e) {
					e.printStackTrace();
				}
					              
			}
		} catch (SQLException | FailedConnectionException e) {
			System.out.println("Cannot make statement!");
			return users;

		}

		return users;

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
