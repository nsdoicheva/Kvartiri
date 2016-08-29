package source;

import java.util.Map;
import java.util.Map.Entry;

import source.Ad;
import source.Criteria;
import source.Message;

public interface IUser {

	boolean uploadAd(Ad ad);

	void makeVipUser();

	boolean deleteAd(Ad ad);

	Map<Object, Ad> listAllMyAds();

	Entry<Message, String> openMessage();

	boolean deleteMessage(User user);

	Message sendMessage(User user, Message message);

	boolean upgrade(Ad ad, Criteria criteria, Object object);

	String changePassword(String oldPass, String password);

}
