package source;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Ad {
	private String name;
	private String address;
	private Integer id;
	private static int lastId;
	private String description;
	private double pricePerMonth;
	private String neighborhood;
	List<File> images = new LinkedList<File>();
	private String type;
	private User user;
	private Date uploadDate;
	private int numberOfRooms;
	private double squareMeters;

	public Ad(String name, String address, String description, double pricePerMonth, String type, String neighborhood,
			User user, int numberOfRooms, double squareMeters) {
		setName(name);
		setAddress(address);
		setDescription(description);
		setPricePerMonth(pricePerMonth);
		setNeighborhood(neighborhood);
		setUser(user);
		setNumberOfRooms(numberOfRooms);
		setType(type);
		if (squareMeters > 0) {
			this.squareMeters = squareMeters;
		}

		this.uploadDate = new Date(
				LocalDate.now().getDayOfMonth() / LocalDate.now().getMonthValue() / LocalDate.now().getYear());

		this.id = lastId++;
		this.images = new LinkedList<File>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type != null && !type.trim().equals("")) {
			this.type = type;
		}
	}

	public double getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(double squareMeters) {
		if (squareMeters > 0) {
			this.squareMeters = squareMeters;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.trim().equals("")) {
			this.name = name;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null && !address.trim().equals("")) {
			this.address = address;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null && !description.trim().equals("")) {
			this.description = description;
		}
	}

	public double getPricePerMonth() {
		return pricePerMonth;
	}

	public void setPricePerMonth(double pricePerMonth) {
		if (pricePerMonth > 0) {
			this.pricePerMonth = pricePerMonth;
		}
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		if (neighborhood != null && !neighborhood.trim().equals("")) {
			this.neighborhood = neighborhood;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		if (numberOfRooms > 0) {
			this.numberOfRooms = numberOfRooms;
		}
	}

	@Override
	public String toString() {
		return "UPLODADED BY : + " + this.user.getUserName() + " Ad [name=" + name + ", address=" + address
				+ ", description=" + description + ", pricePerMonth=" + pricePerMonth + ", neighborhood=" + neighborhood
				+ ", uploadDate=" + uploadDate + ", numberOfRooms=" + numberOfRooms + ", AREA =" + squareMeters + " ] ";
	}

	Integer getId() {
		return id;
	}

}
