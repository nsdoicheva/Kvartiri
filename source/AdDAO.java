package source;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import java.sql.Connection;

import exceptions.FailedConnectionException;
import exceptions.WeakPasswordException;

public class AdDAO {
	private static final String INSERT_INTO_ADS_QUERY = "INSERT INTO ads values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_ID = "SELECT id FROM users WHERE username= ? ;";
	private static final String SELECT_ALL_AD_INFO_QUERY = "SELECT name, address, description, price, type, neighbourhood, square_meters, rooms, User_id from ads;";
	private static final String SELECT_OWNER_OF_AD = "SELECT username FROM users WHERE id= ? ;";
	private static AdDAO instance;

	private AdDAO() {

	}

	public synchronized static AdDAO getInstance() {
		if (instance == null) {
			instance = new AdDAO();
		}
		return instance;
	}

	public boolean saveAd(Ad ad, String username) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(SELECT_USER_ID, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.next();
			int userId = rs.getInt(1);
			statement.close();

			PreparedStatement statement2 = connection.prepareStatement(INSERT_INTO_ADS_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement2.setString(1, ad.getName());
			statement2.setString(2, ad.getAddress());
			statement2.setString(3, ad.getDescription());
			statement2.setDouble(4, ad.getPricePerMonth());
			statement2.setString(5, ad.getNeighborhood());
			statement2.setString(6, ad.getType());
			statement2.setDouble(7, ad.getSquareMeters());
			statement2.setDate(8, ad.getUploadDate());
			statement2.setInt(9, ad.getNumberOfRooms());
			statement2.setInt(10, userId);

			statement2.executeUpdate();
			connection.commit();
			System.out.println("The ad was saved to the DB");
			return true;
		} catch (SQLException | FailedConnectionException e) {
			return false;
		}
	}

	public List<Ad> getAllAdsFromDB() {
		List<Ad> ads = new ArrayList<Ad>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_AD_INFO_QUERY);
			PreparedStatement prepStatement = connection.prepareStatement(SELECT_OWNER_OF_AD, Statement.RETURN_GENERATED_KEYS);
			while (resultSet.next()) {
				int userId = resultSet.getInt(9);
				prepStatement.setInt(1, userId);
				ResultSet result = prepStatement.executeQuery();
				result.next();
				String username = result.getString(1);
				User owner = null;
				for (User user : Administrator.getInstance().getAllUsersFromDB()) {
					if (user.getUserName().equals(username)) {
						owner = user;
					}
				}
				ads.add(new Ad(resultSet.getString("name"), resultSet.getString("address"),
						resultSet.getString("description"), resultSet.getDouble("price"), resultSet.getString("type"),
						resultSet.getString("neighbourhood"), owner, resultSet.getInt("rooms"),
						resultSet.getDouble("square_meters")));
			}
			connection.commit();
		} catch (SQLException | FailedConnectionException e) {
			System.out.println("Cannot make statement!");
			return ads;
		}
		return ads;
	}
}
