package database.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.dao.Delivery;
import database.dao.Driver;
import database.dao.Truck;

public class DeliveryManager {
	public static List<Delivery> listAllOrders(Connection con) {
		List<Delivery> orders = new ArrayList<>();
		List<Driver> drivers = DriverManager.findAll(con);
		try (PreparedStatement stmt = con.prepareStatement("select * from delivery order by id")) {
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			while (result.next()) {
				Delivery order = new Delivery(result);
				try {
				order.setDriver(drivers.get(result.getInt("driver_id") - 1));
				} catch(IndexOutOfBoundsException e) {
					
				}
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public static Delivery findById(Connection con, int id) {
		try (PreparedStatement stmt = con.prepareStatement("select * from delivery where id = ? order by id")) {
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			if (result.next()) {
				Delivery order = new Delivery(result);
				order.setDriver(DriverManager.findById(con, result.getInt("driver_id")));
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Driver> findAvailableDrivers(Connection con) {
		List<Driver> drivers = new ArrayList<>();
		List<Truck> trucks = TruckManager.findAllTrucks(con);
		try (PreparedStatement stmt = con.prepareStatement(
				"select * from driver where not truck in "
				+ "(select dr.truck from driver dr inner join delivery dl on dr.id = dl.driver_id where delivery_status like 'ongoing')")) {
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			while (result.next()) {
				Driver driver = new Driver(result);
				driver.setTruck(trucks.get(result.getInt("truck") - 1));
				drivers.add(driver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drivers;
	}

	public static boolean makeOrder(Connection con, String description) {
		List<Driver> availableDrivers = findAvailableDrivers(con);
		Collections.shuffle(availableDrivers);
		try (PreparedStatement stmt = con
				.prepareStatement("insert into delivery(id, driver_id, departure_date, delivery_status, delivery_desc) "
						+ "values((select max(d.id) + 1 from delivery d), ?, sysdate(), ?, ?)")) {
			if(!availableDrivers.isEmpty()) stmt.setInt(1, availableDrivers.get(0).getId());
			else stmt.setNull(1, 6);
			stmt.setString(2, !availableDrivers.isEmpty() ? "ongoing" : "postponed");
			stmt.setString(3, description);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean confirmArrival(Connection con, int id) {
		if (findById(con, id).getStatus().equals("ongoing")) {
			try (PreparedStatement stmt = con
					.prepareStatement("update delivery set delivery_status = 'ended' where id = ?")) {
				stmt.setInt(1, id);
				return stmt.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean takePostponedOrder(Connection con, int id) {
		List<Integer> availableIds = new ArrayList<>();
		
		return false;
	}
}
