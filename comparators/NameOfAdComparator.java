package comparators;

import java.util.Comparator;

import source.Ad;

public class NameOfAdComparator implements Comparator<Ad> {

	@Override
	public int compare(Ad ad1, Ad ad2) {
		return ad1.getName().compareToIgnoreCase(ad2.getName());
	}

}
