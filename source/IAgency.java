package source;

import java.util.List;
import java.util.Map;

public interface IAgency {
	boolean addBroker(User user) ;
	boolean fireBroker(User user);
	List<Map<Object, Ad>> listAllAds() ;
}