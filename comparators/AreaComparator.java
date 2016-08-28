package comparators;
import java.util.Comparator;

import source.Ad;

public class AreaComparator implements Comparator<Ad> {

	@Override
	public int compare(Ad ad1, Ad ad2) {
		return (int) (ad1.getSquareMeters() - ad2.getSquareMeters());
	}

}
