package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.classes.Vehicle;

public class VehicleDAO {
	public static Vehicle[] loadAll(Connection conn) {
		List<Vehicle> Vehicles = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Vehicles WHERE active=1");
			while (rs.next()) {
				Vehicle tmpVehicle = new Vehicle();
				tmpVehicle.setModel(rs.getString("model")).setBrand(rs.getString("brand"))
						.setProductionYear(rs.getInt("year")).setRegistrationNumber(rs.getString("regNum"));
				LocalDate nextRev = rs.getDate("nextRev").toLocalDate();
				tmpVehicle.setNextReview(nextRev);
				try {
					tmpVehicle.setOwner(CustomerDAO.loadById(conn, rs.getInt("customer_id")));
				} catch (Exception e) {
				}
				tmpVehicle.setId(rs.getInt("id"));
				Vehicles.add(tmpVehicle);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vehicle[] VehiclesArr = new Vehicle[Vehicles.size()];
		Vehicles.toArray(VehiclesArr);

		return VehiclesArr;
	}

	public static Vehicle[] loadAllByClientId(Connection conn, int id) {
		List<Vehicle> Vehicles = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Vehicles WHERE active=1 AND customer_id="+id);
			while (rs.next()) {
				Vehicle tmpVehicle = new Vehicle();
				tmpVehicle.setModel(rs.getString("model")).setBrand(rs.getString("brand"))
						.setProductionYear(rs.getInt("year")).setRegistrationNumber(rs.getString("regNum"));
				LocalDate nextRev = rs.getDate("nextRev").toLocalDate();
				tmpVehicle.setNextReview(nextRev);
				try {
					tmpVehicle.setOwner(CustomerDAO.loadById(conn, rs.getInt("customer_id")));
				} catch (Exception e) {
				}
				tmpVehicle.setId(rs.getInt("id"));
				Vehicles.add(tmpVehicle);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vehicle[] VehiclesArr = new Vehicle[Vehicles.size()];
		Vehicles.toArray(VehiclesArr);

		return VehiclesArr;
	}

	public static boolean saveToDB(Connection conn, Vehicle Vehicle) {
		boolean result = false;
		if (Vehicle.getId() == 0) {
			String query = "INSERT INTO Vehicles VALUES (null, ?, ?, ?, ?, ?, 1, ?)";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, Vehicle.getBrand());
				pst.setString(2, Vehicle.getModel());
				pst.setInt(3, Vehicle.getProductionYear());
				pst.setString(4, Vehicle.getRegistrationNumber());
				pst.setDate(5, java.sql.Date.valueOf(Vehicle.getNextReview()));
				pst.setInt(6, Vehicle.getOwner().getId());
				pst.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				PreparedStatement pst = conn.prepareStatement(
						"UPDATE Vehicles SET model=?, brand=?, year=?, regNum=?, nextRev=?, active=1, customer_id=? WHERE id=?");
				pst.setString(1, Vehicle.getBrand());
				pst.setString(2, Vehicle.getModel());
				pst.setInt(3, Vehicle.getProductionYear());
				pst.setString(4, Vehicle.getRegistrationNumber());
				pst.setDate(5, java.sql.Date.valueOf(Vehicle.getNextReview()));
				pst.setInt(6, Vehicle.getOwner().getId());
				pst.setInt(7, Vehicle.getId());

				pst.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public static Vehicle loadById(Connection conn, int id) {

		String query = "SELECT * FROM Vehicles WHERE id=?";
		Vehicle tmpVehicle = new Vehicle();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tmpVehicle.setModel(rs.getString("model")).setBrand(rs.getString("brand"))
						.setProductionYear(rs.getInt("year")).setRegistrationNumber(rs.getString("regNum"));
				LocalDate nextRev = rs.getDate("nextRev").toLocalDate();
				tmpVehicle.setNextReview(nextRev);
				try {
					tmpVehicle.setOwner(CustomerDAO.loadById(conn, rs.getInt("customer_id")));
				} catch (Exception e) {
				}
				tmpVehicle.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpVehicle;

	}
	public static boolean changeOwner(Connection conn, int ownerId, int vehId) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE Vehicles SET customer_id=? WHERE id=?");
			pst.setInt(1, ownerId);
			pst.setInt(2, vehId);
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static boolean delete(Connection conn, int id) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE Vehicles SET active=0 WHERE id=?");
			pst.setInt(1, id);
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static boolean delete(Connection conn, Vehicle Vehicle) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE Vehicles SET active=0 WHERE id=?");
			pst.setInt(1, Vehicle.getId());
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String query = "DELETE FROM Vehicles WHERE id=?";
		// PreparedStatement ps;
		// try {
		// ps = conn.prepareStatement(query);
		// ps.setInt(1, Vehicle.getId());
		// ps.executeUpdate();
		// System.out.println("Usunięto");
		// result = true;
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		return result;

	}
}
