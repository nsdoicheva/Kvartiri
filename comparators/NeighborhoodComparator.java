package comparators;
import java.util.Comparator;

import source.Ad;

public class NeighborhoodComparator implements Comparator<Ad> {

	@Override
	public int compare(Ad ad1, Ad ad2) {
		return ad1.getNeighborhood().compareToIgnoreCase(ad2.getNeighborhood());
	}
	
	
	
	

}
