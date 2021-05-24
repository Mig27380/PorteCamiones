package database.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.dao.Truck;

public class TruckManager {
	public static Truck findById(Connection con, int id) {
		try(PreparedStatement prepstmt = con.prepareStatement("select * from truck where id = ? order by id")){
			prepstmt.setInt(1, id);
			ResultSet result = prepstmt.executeQuery();
			result.beforeFirst();
			if(result.next()) return new Truck(result);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  List<Truck> findAllTrucks(Connection con){
		List<Truck> trucks = new ArrayList<>();
		try(PreparedStatement prepstmt = con.prepareStatement("select * from truck order by id")){
			ResultSet result = prepstmt.executeQuery();
			result.beforeFirst();
			while(result.next()) {
				trucks.add(new Truck(result));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return trucks;
	}
	
	public static Truck findByDriver(Connection con, int id) {
		try(PreparedStatement prepstmt = con.prepareStatement("select t.* from truck t inner join driver d on d.truck = t.id where d.id = ? order by d.id")){
			prepstmt.setInt(1, id);
			ResultSet result = prepstmt.executeQuery();
			result.beforeFirst();
			if(result.next()) return new Truck(result);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insertTruck(Connection con, String license, String brand, String model) {
		try(PreparedStatement prepstmt = con.prepareStatement("insert into truck(id, license, brand, model) values((select max(t.id) + 1 from truck t), ?, ?, ?)")){
			prepstmt.setString(1, license);
			prepstmt.setString(2, brand);
			prepstmt.setString(3, model);
			return prepstmt.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteTruckById(Connection con, int id) {
		try(PreparedStatement prepstmt = con.prepareStatement("delete from truck where id = ?")){
			prepstmt.setInt(1, id);
			return prepstmt.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
