package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter
public class Delivery {
	private int id;
	private int driverId;
	@Setter private Driver driver;
	private Date departureDate;
	private String status;
	private Date arrivalDate;
	private String deliveryDesc;
	
	public Delivery(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.driverId = result.getInt("driver_id");
			this.departureDate = result.getDate("departure_date");
			this.status = result.getString("delivery_status");
			this.arrivalDate = result.getTimestamp("arrival_date");
			this.deliveryDesc = result.getString("delivery_desc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
