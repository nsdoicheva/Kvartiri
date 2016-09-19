package source;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import java.sql.Connection;

import exceptions.FailedConnectionException;

public class AdDAO {
	private static final String INSERT_INTO_ADS_QUERY = "INSERT INTO ads values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER_ID = "SELECT id FROM users WHERE username= ? ;";
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

}
