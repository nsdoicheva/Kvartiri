package source;

public interface IAdmin {
   boolean banUser(RegisteredUser user);
   boolean deleteAd(Ad ad, User user);
}
