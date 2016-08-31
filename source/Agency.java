package source;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Agency extends User implements IAgency {

	private Set<User> brokers;

	// brokers username and ad

	public Agency(String name, String userName, String password, String phoneNumber, String address, String email) {
		super(name, userName, password, phoneNumber, address, email);
		this.brokers = new HashSet<User>();
	}

	public boolean addBroker(User user) {
		if (user != null) {
			this.brokers.add(user);
			user.setBroker(true);
			System.out.println("New broker :  " + user.getName() + " was hired in agency: " + this.getName());
			for (Entry<Object, Ad> entry : user.getCopyOfMyAds().entrySet()) {
				this.putAnAd(entry.getKey(), entry.getValue());
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean fireBroker(User user) {
		if (user != null && user.isBroker()==true) {
			System.out.println(user.getName() + " is fired from the agency");
			user.setBroker(false);
			brokers.remove(user);
			for (Entry<Object, Ad> entry : user.getCopyOfMyAds().entrySet()) {
				this.removeAnAd(entry.getKey(), entry.getValue());
			}
			return true;
		} else {
			return false;
		}
	}

	public List<Map<Object, Ad>> listAllAds() {
		System.out.println("ALL ADS IN AGENCY: " + this.getName());
		List<Map<Object, Ad>> allBrokerAds = new ArrayList<Map<Object, Ad>>();
		for (User broker : this.brokers) {
			allBrokerAds.add(broker.listAllMyAds());
		}
		return allBrokerAds;
	}

}
