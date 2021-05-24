package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter
public class Driver {
	private int id;
	private String nif;
	private String firstName;
	private String surname;
	private String phone;
	private String email;
	private int truckId;
	@Setter private Truck truck;
	
	public Driver(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.nif = result.getString("nif");
			this.firstName = result.getString("firstname");
			this.surname = result.getString("surname");
			this.phone = result.getString("phone");
			this.email = result.getString("email");
			this.truckId = result.getInt("truck");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
