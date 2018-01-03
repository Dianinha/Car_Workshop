package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.classes.Employee;
import pl.coderslab.classes.Order;
import pl.coderslab.classes.Vehicle;

public class OrderDAO {
	public static Order[] loadAll(Connection conn) {
		List<Order> Orders = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Orders");
			while (rs.next()) {
				Order tmpOrder = new Order();
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime())
						.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				tmpOrder.setId(rs.getInt("id"));
				Orders.add(tmpOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Order[] OrdersArr = new Order[Orders.size()];
		Orders.toArray(OrdersArr);

		return OrdersArr;
	}
	
	public static Order[] loadAllByStatus(Connection conn, int status) {
		List<Order> Orders = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Orders WHERE status="+status);
			while (rs.next()) {
				Order tmpOrder = new Order();
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime())
						.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				tmpOrder.setId(rs.getInt("id"));
				Orders.add(tmpOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Order[] OrdersArr = new Order[Orders.size()];
		Orders.toArray(OrdersArr);

		return OrdersArr;
	}

	public static boolean saveToDB(Connection conn, Order order) {
		boolean result = false;
		if (order.getId() == 0) {
			String query = "INSERT INTO Orders VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] generatedColumns = { "id" };
			try {
				PreparedStatement pst = conn.prepareStatement(query, generatedColumns);
				pst.setTimestamp(1, java.sql.Timestamp.valueOf(order.getAcceptance()));
				pst.setTimestamp(2, java.sql.Timestamp.valueOf(order.getRepairStartTime()));
				pst.setInt(3, order.getAssignedWorker().getId());
				pst.setString(4, order.getProblemDescription());
				pst.setString(5, order.getRepairDescription());
				pst.setInt(6, order.getStatusIinInt());
				pst.setInt(7, order.getVehicle().getId());
				pst.setDouble(8, order.getRepairCost());
				pst.setDouble(9, order.getPartsCost());
				pst.setDouble(10, order.getRepairTime());

				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					order.setId(rs.getInt(1));
				}
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				PreparedStatement pst = conn.prepareStatement(
						"UPDATE Orders SET accepted=?, startTime=?, worker_id=?, problem_desc=?, solution_desc=?, status=?, vehicle_id=?, repair_cost=?, parts_cost=?, repairTime=? WHERE id=?");

				pst.setTimestamp(1, java.sql.Timestamp.valueOf(order.getAcceptance()));
				pst.setTimestamp(2, java.sql.Timestamp.valueOf(order.getRepairStartTime()));
				pst.setInt(3, order.getAssignedWorker().getId());
				pst.setString(4, order.getProblemDescription());
				pst.setString(5, order.getRepairDescription());
				pst.setInt(6, order.getStatusIinInt());
				pst.setInt(7, order.getVehicle().getId());
				pst.setDouble(8, order.getRepairCost());
				pst.setDouble(9, order.getPartsCost());
				pst.setDouble(10, order.getRepairTime());

				pst.setInt(11, order.getId());

				pst.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public static Order loadById(Connection conn, int id) {

		String query = "SELECT * FROM Orders WHERE id=?";
		Order tmpOrder = new Order();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime())
						.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				tmpOrder.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpOrder;

	}

	public static boolean delete(Connection conn, Order order) {
		boolean result = false;
		String query = "DELETE FROM Orders WHERE id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, order.getId());
			ps.executeUpdate();
			System.out.println("Usunięto");
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
}
