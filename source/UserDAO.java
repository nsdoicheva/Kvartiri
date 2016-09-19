package source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.*;

import exceptions.FailedConnectionException;

public class UserDAO {
	private static final String INSERT_INTO_AGENCIES_QUERY = "INSERT INTO agencies VALUES(?);";
	private static final String INSERT_INTO_USERS_QUERY = "INSERT INTO users VALUES(null,?,?,md5(?),?,?,?);";
	private static final String SELECT_USER_SQL = "SELECT username, password FROM users WHERE username = ? AND password = md5(?)";

	private static UserDAO instance;

	private UserDAO() {

	}

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public boolean saveUser(User user) {
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection()
					.prepareStatement(INSERT_INTO_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getPhoneNumber());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getEmail());

			int count = statement.executeUpdate();
			if (count > 0) {
				System.out.println("The user was saved to the DB");
				return true;
			}
		} catch (SQLException | FailedConnectionException e) {
			return false;
		}
		return false;

	}

	public boolean saveAgency(User user) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();

			connection.setAutoCommit(false);

			PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USERS_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getPhoneNumber());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getEmail());

			int count = statement.executeUpdate();
			if(count==0){
				return false;
			}

			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			statement.close();
			statement = connection.prepareStatement(INSERT_INTO_AGENCIES_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			statement.executeUpdate();

			connection.commit();

		} catch (SQLException | FailedConnectionException e) {
			return false;
		}
		return true;
	}

	public boolean loginUser(String username, String password) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SELECT_USER_SQL);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("Wrong username or password.");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("User cannot be logged right now.");
		} catch (FailedConnectionException e) {
			System.out.println("No connection");
		}
		return true;
	}
	
}