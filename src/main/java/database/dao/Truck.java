package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.ToString;

@ToString @Getter
public class Truck {
	private int id;
	private String license;
	private String brand;
	private String model;
	
	public Truck(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.license = result.getString("license");
			this.brand = result.getString("brand");
			this.model = result.getString("model");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
