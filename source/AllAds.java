package source;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import comparators.AreaComparator;
import comparators.NameOfAdComparator;
import comparators.NeighborhoodComparator;
import comparators.NumberOfRoomsComparator;
import comparators.PriceComparator;
import exceptions.FailedConnectionException;
import exceptions.WeakPasswordException;

public class AllAds {

	//private static final String SELECT_ALL_ADS ="" ;

	private static List<Ad> allAds ;

	private static AllAds instance = new AllAds();

	private AllAds() {
		AllAds.allAds = Collections.synchronizedList(new ArrayList<>());
	}

	public static AllAds getInstance() {
		return instance;
	}

	public void setAd(Ad ad) {
		System.out.println("New ad was added!");
		if (ad != null) {
			allAds.add(ad);
		}
	}

	public static  void listAdsInSite() {
		for (Ad ad : allAds) {
			System.out.println(ad);
		}
	}
	/*
	public List<Ad> getAllAdsFromDB() {
		List<Ad> ads = new ArrayList<Ad>();
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_ADS);
			
			while(resultSet.next()) {
				try {
					allAds.add(null);
				} catch (WeakPasswordException e) {
					e.printStackTrace();
				}
					              
			}
		} catch (SQLException | FailedConnectionException e) {
			System.out.println("Cannot make statement!");
			return ads;
		}

		return ads;

	}
	*/
	
	private static Comparator<Ad> getComparator(Criteria sortCriteria) {
		switch (sortCriteria) {
		case PRICE:
			return new PriceComparator(); 
		case NEIGHBORHOOD:
			return new NeighborhoodComparator();
		case SQUARE_METERS:
			return new AreaComparator();
		case NUMBER_OF_ROOMS:  
			return new NumberOfRoomsComparator();
		case NAME : 	
			return new NameOfAdComparator();
		default : return new NameOfAdComparator();	
		}
	}
	
	public static void sortAdsBy(Criteria sortCriteria) {
		Collections.sort(allAds, getComparator(sortCriteria)) ;
	}

}
