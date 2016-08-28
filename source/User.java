package source;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.NoMessagesEception;
import exceptions.WeakPasswordException;
import exceptions.WrongInputException;
import exceptions.WrongPasswordException;

import java.util.Scanner;
import java.util.Set;

public abstract class User implements IUser {

	private static final int MAX_LENGTH_LONG_NUMBER = 15;
	private static final String HOW_TO_START_LONG_NUMBER = "+359 8";
	private static final int MAX_LENGTH_SHORT_NUMBER = 10;
	private static final String HOW_TO_START_NUMBER = "08";
	private static final int MAX_PASSWORD_ATTEMPTS = 3;
	private static final int MIN_PASSWORD_LENGTH = 6;

	private String name;
	private String userName;
	private String password;
	private static Scanner sc = new Scanner(System.in);
	private String phoneNumber;
	private String address;
	private String email;
	private Map<Message, String> messages; // celiq message e unikalen
	private Map<Object, Ad> myAds;
	private AllAds allAds;
	private boolean isBroker;

	protected User(String name, String phoneNumber, String address, String email) {
		try {
			if (isValid(name)) {
				this.name = name;
			} else {
				throw new WrongInputException("Enter a valid name.");
			}

			if (isValidNumber(phoneNumber)) {
				this.phoneNumber = phoneNumber;
			} else {
				throw new WrongInputException("Enter a phone number.");
			}

			if (isValid(address)) {
				this.address = address;
			} else {
				throw new WrongInputException("Enter a address.");
			}

			if (isValidEmailAddress(email)) {
				this.email = email;
			} else {
				throw new WrongInputException("Enter a valid email.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}
		this.isBroker = false;
		this.messages = new HashMap<Message, String>();
		this.myAds = new LinkedHashMap<Object, Ad>();
		this.allAds = AllAds.getInstance();

	}

	public User(String name, String userName, String password, String phoneNumber, String address, String email) {
		this(name, phoneNumber, address, email);
		try {
			if (isValid(userName)) {
				this.userName = userName;
			} else {
				throw new WrongInputException("Enter a valid username.");
			}

			if (isStrongPass(password)) {
				this.password = password;
			} else {
				throw new WrongInputException("Enter a valid password.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}

	}

	private boolean isValid(String str) {
		boolean isValid = false;
		if (str != null && !str.equals("")) {
			isValid = true;
		}
		return isValid;
	}

	private boolean isValidNumber(String telephone) {
		boolean isValidNum = false;
		if (isValid(telephone)) {
			if ((telephone.substring(0, 2).equals(HOW_TO_START_NUMBER) && telephone.length() == MAX_LENGTH_SHORT_NUMBER)
					|| (telephone.substring(0, 6).equals(HOW_TO_START_LONG_NUMBER)
							&& telephone.length() == MAX_LENGTH_LONG_NUMBER)) {
				isValidNum = true;
			}
		}
		return isValidNum;
	}

	private boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/*
	 * void askForPassword() { String userPassword = ""; int
	 * countOfPasswordAttempts = 0; System.out.println("Enter password:");
	 * userPassword = sc.nextLine(); while (!userPassword.equals(this.password))
	 * { countOfPasswordAttempts++; if (countOfPasswordAttempts <
	 * MAX_PASSWORD_ATTEMPTS) { System.out.println(
	 * "Invalid password! Left attempts: " + (MAX_PASSWORD_ATTEMPTS -
	 * countOfPasswordAttempts)); userPassword = sc.nextLine(); } else {
	 * //System.out.println("No more password attempts! Aborting method!");
	 * throw new SecurityException("No more password attempts! Aborting method!"
	 * ); } } }
	 */
	// v tozi metod karame user-a mnogokratno da vuvede silna parola
	// dokato ne postigne uspeh

	public boolean isStrongPass(String password) {
		if (isValid(password)) {
			boolean isLongEnough = password.length() > MIN_PASSWORD_LENGTH;
			boolean hasUppercase = !password.equals(password.toLowerCase());
			boolean hasLowercase = !password.equals(password.toUpperCase());
			boolean hasDigit = password.matches(".*\\d+.*");
			try {
				if (isLongEnough && hasUppercase && hasLowercase && hasDigit) {
					return true;
				} else {
					throw new WeakPasswordException("Your password is too weak.");
				}
			} catch (WeakPasswordException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void uploadAd(Ad ad) {
		this.myAds.put(ad.getId(), ad);
		this.allAds.setAd(ad);
	}

	@Override
	public void listAllMyAds() {
		for (Entry<Object, Ad> entry : this.myAds.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	@Override
	public void deleteAd(Ad ad) {
		System.out.println(this.getUserName() + " wants to delete his ad " + ad.getName());

		for (Iterator<Map.Entry<Object, Ad>> it = this.myAds.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Object, Ad> entry = it.next();
			if (entry.getValue().getId().equals(ad.getId())) {
				it.remove();
			}
		}

	}

	@Override
	public void makeVipUser() {

	}

	@Override
	public void upgrade(Ad ad, Criteria criteria, Object object) {
		switch (criteria) {
		case NAME: {
			System.out
					.println(this.userName + " changes his ad's name \"" + ad.getName() + "\" to " + ((String) object));
			ad.setName((String) object);
			break;
		}
		case PRICE: {
			System.out
					.println(this.userName + "changes his ad \"" + ad.getName() + "\"'s price to " + ((double) object));
			ad.setPricePerMonth((double) object);
			break;
		}
		case NUMBER_OF_ROOMS: {
			System.out.println(
					this.userName + "changes his ad \"" + ad.getName() + "\"'s number of rooms to " + ((int) object));
			ad.setNumberOfRooms((int) object);
			break;
		}
		case SQUARE_METERS: {
			System.out.println(
					this.userName + "changes his ad \"" + ad.getName() + "\"'s square meters to " + ((double) object));
			ad.setSquareMeters((double) object);
			break;
		}
		case NEIGHBORHOOD: {
			System.out.println(
					this.userName + "changes his ad \"" + ad.getName() + "\"'s neighborhood to " + ((String) object));
			ad.setNeighborhood((String) object);
			break;
		}
		}
	}

	@Override
	public String changePassword(String oldPass, String newPassword) {
		System.out.println("The user: " + this.getUserName() + " wants to change his password!");
		if (isValid(oldPass) && isValid(newPassword)) {
			if (oldPass.equals(this.password)) {
				this.password = newPassword;
				System.out.println(this.name + " changed his password successfully!");
			} else {
				try {
					newPassword = this.password;
					throw new WrongPasswordException("Entered password is incorrect.");
				} catch (WrongPasswordException e) {
					e.printStackTrace();
				}
			}
		}
		return newPassword;
	}

	@Override
	public Entry<Message, String> openMessage() {
		Entry<Message, String> lastMessage = null;
		System.out.println(this.userName + " lets check my message box!");
		if (messages.isEmpty()) {
			try {
				System.out.println("I don't have any messages");
				throw new NoMessagesEception("No messages in box.");
			} catch (NoMessagesEception e) {
				e.printStackTrace();
				return lastMessage;
			}
		} else {
			for (Entry<Message, String> mesg : this.messages.entrySet()) {
				lastMessage = mesg;

				System.out.println("Message from: " + mesg.getValue());
				System.out.println("TITLE: " + mesg.getKey().getTitle());
				System.out.println("CONTENT: " + mesg.getKey().getContent());
			}
			return lastMessage;
		}

	}

	@Override
	public void deleteMessage(User user) {
		System.out.println(this.getUserName() + " wants to delete all messages from user: " + user.getUserName());
		for (Iterator<Map.Entry<Message, String>> it = this.messages.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Message, String> entry = it.next();
			if (entry.getValue().equals(user.getUserName())) {
				it.remove();
			}
		}

	}

	@Override
	/**
	 * 
	 * @param user
	 * @param message
	 * @return message, which was send to the user
	 */
	public Message sendMessage(User user, Message message) {
		System.out.println(this.getUserName() + " sent message to user: " + user.getName());
		user.messages.put(message, this.getUserName());
		return message;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		try {
			if (isValid(name)) {
				this.name = name;
			} else {
				throw new WrongInputException("Entered name is invalid.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}

	}

	String getUserName() {
		return userName;
	}

	void setUserName(String userName) {
		try {
			if (isValid(userName)) {
				this.userName = userName;
			} else {
				throw new WrongInputException("Entered userName is invalid.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}
	}

	String getPassword() {
		return password;
	}

	String getPhoneNumber() {
		return phoneNumber;
	}

	void setPhoneNumber(String phoneNumber) {
		try {
			if (isValidNumber(phoneNumber)) {
				this.phoneNumber = phoneNumber;
			} else {
				throw new WrongInputException("Entered phone number is invalid.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}
	}

	String getAddress() {
		return address;
	}

	void setAddress(String address) {
		try {
			if (isValid(address)) {
				this.address = address;
			} else {
				throw new WrongInputException("Entered address is invalid.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}
	}

	String getEmail() {
		return email;
	}

	void setEmail(String email) {
		try {
			if (isValidEmailAddress(email)) {
				this.email = email;
			} else {
				throw new WrongInputException("Entered address is invalid.");
			}
		} catch (WrongInputException e) {
			e.printStackTrace();
		}
	}

	Map<Object, Ad> getCopyOfMyAds() {
		Map<Object, Ad> copyOfMyAds = new LinkedHashMap<Object, Ad>();
		for (Entry<Object, Ad> ad : myAds.entrySet()) {
			copyOfMyAds.put(ad.getKey(), ad.getValue());
		}
		return copyOfMyAds;
	}
	
	void putAnAd(Object obj, Ad ad){
		myAds.put(obj, ad);
	}
	
	void removeAnAd(Object obj, Ad ad){
		myAds.remove(obj, ad);
	}

	boolean isBroker() {
		return isBroker;
	}

	void setBroker(boolean isBroker) {
		this.isBroker = isBroker;
	}
}
