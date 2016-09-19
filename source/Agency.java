package source;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import exceptions.WeakPasswordException;
import exceptions.WrongInputException;

import java.util.Map.Entry;

public class Agency extends User implements IAgency {
	
	private Set<User> brokers;
	
	// brokers username and ad
	
	
	public Agency(String name, String userName, String password, String phoneNumber, String address, String email) throws WeakPasswordException {
		super(name, userName, password, phoneNumber, address, email);
		this.brokers = new HashSet<User>();
	}

	public void addBroker(User user) {
		if (user != null) {
			this.brokers.add(user);
			System.out.println("New broker :  " + user.getName() + " was hired in agency: " + this.getName());
			for (Entry<Object, Ad> entry : user.getCopyOfMyAds().entrySet()) {
				this.putAnAd(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public void fireBroker(User user) {
		if(user != null) {
		System.out.println(user.getName()  + " bqgai vkushti!@!@!@!@");
		brokers.remove(user);
		for (Entry<Object, Ad> entry : user.getCopyOfMyAds().entrySet()) {
			this.removeAnAd(entry.getKey(), entry.getValue());
		}
		}
	}
	
	public void listAllAds() {
		System.out.println("ALL ADS IN AGENCY: " + this.getName());
		for (User broker : this.brokers) {
			broker.listAllMyAds();
		}
	}
	
	

}
