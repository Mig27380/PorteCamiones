package database.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.dao.Driver;
import database.dao.Truck;

public class DriverManager {
	public static List<Driver> findAll(Connection con){
		List<Driver> drivers = new ArrayList<>();
		List<Truck> trucks = TruckManager.findAllTrucks(con);
		try(PreparedStatement stmt = con.prepareStatement("select * from driver order by id")){
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			while(result.next()) {
				Driver driver = new Driver(result);
				driver.setTruck(trucks.get(result.getInt("truck") - 1));
				drivers.add(driver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drivers;
	}
	
	public static Driver findById(Connection con, int id) {
		try(PreparedStatement stmt = con.prepareStatement("select * from driver where id = ? order by id")){
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			if(result.next()) {
				Driver driver = new Driver(result);
				driver.setTruck(TruckManager.findById(con, result.getInt("truck")));
				return driver;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insertDriver(Connection con, String nif, String firstName, String surname, String phone, String email, int truckId) {
		try(PreparedStatement prepstmt = con.prepareStatement("insert into driver(id, nif, firstname, surname, phone, email, truck) values((select max(d.id) + 1 from driver d), ?, ?, ?, ?, ?, ?)")){
			prepstmt.setString(1, nif);
			prepstmt.setString(2, firstName);
			prepstmt.setString(3, surname);
			prepstmt.setString(4, phone);
			prepstmt.setString(5, email);
			prepstmt.setInt(6, truckId);
			return prepstmt.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteById(Connection con, int id) {
		try(PreparedStatement prepstmt = con.prepareStatement("delete from driver where id = ?")){
			prepstmt.setInt(1, id);
			return prepstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean changeDriversTruck(Connection con, int driverId, int truckId) {
		try(PreparedStatement prepstmt = con.prepareStatement("update driver set truck = ? where id = ?")){
			prepstmt.setInt(1, truckId);
			prepstmt.setInt(2, driverId);
			return prepstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
