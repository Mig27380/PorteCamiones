package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class Driver {
	
	private SimpleStringProperty name;
	
	private int id;
	private String nif;
	private String firstName;
	private String surname;
	private String phone;
	private String email;
	private int truckId;
	private Truck truck;
	
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
	
	public void setName() {
		name.set(firstName);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getName() {
		return name.get();
	}
}
