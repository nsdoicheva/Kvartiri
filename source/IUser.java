package source;

import java.util.Map.Entry;

public interface IUser {

	void uploadAd(Ad ad);

	void makeVipUser();

	void deleteAd(Ad ad);

	void listAllMyAds();

	Entry<Message, String> openMessage();

	void deleteMessage(User user);

	Message sendMessage(User user, Message message);

	void upgrade(Ad ad, Criteria criteria, Object object);

	String changePassword(String oldPass, String password);


}
