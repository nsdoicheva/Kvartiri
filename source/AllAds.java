package source;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comparators.AreaComparator;
import comparators.NameOfAdComparator;
import comparators.NeighborhoodComparator;
import comparators.NumberOfRoomsComparator;
import comparators.PriceComparator;

public class AllAds {

	private static List<Ad> allAds;

	private static AllAds instance = new AllAds();

	private AllAds() {
		AllAds.allAds = new ArrayList<>();
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

	public static void listAdsInSite() {
		for (Ad ad : allAds) {
			System.out.println(ad);
		}
	}
	
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
