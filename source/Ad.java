package source;


import java.io.File;
import java.util.Date;
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
	private User user;
	private Date uploadDate;
	private TypeOfAd type;
	private int numberOfRooms;
	private double squareMeters;

	public Ad(String name, String address, String description, double pricePerMonth, String neighborhood, User user,
			TypeOfAd type, int numberOfRooms, double squareMeters) {
		setName(name);
		setAddress(address);
		setDescription(description);
		setPricePerMonth(pricePerMonth);
		setNeighborhood(neighborhood);
		setUser(user);
		setUploadDate(uploadDate);
		setType(type);
		setNumberOfRooms(numberOfRooms);
		if (squareMeters > 0) {
			this.squareMeters = squareMeters;
		}
		Date date = new Date();
		this.uploadDate = date;
		this.id = lastId++;
		this.images = new LinkedList<File>();
	}

	public double getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(double squareMeters) {
		this.squareMeters = squareMeters;
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

	public void setUploadDate(Date uploadDate) {
		if (uploadDate != null) {
			this.uploadDate = uploadDate;
		}
	}

	public TypeOfAd getType() {
		return type;
	}

	public void setType(TypeOfAd type) {
		if (type != null) {
			this.type = type;
		}
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
				+ ", uploadDate=" + uploadDate + ", type=" + type + ", numberOfRooms=" + numberOfRooms + 
				", AREA =" + squareMeters + " ] ";
	}

	Integer getId() {
		return id;
	}

}
