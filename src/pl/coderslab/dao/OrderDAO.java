package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime());
						
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				if (rs.getTimestamp("startTime")!=null) {
					tmpOrder.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				} 
				if (rs.getTimestamp("endTime")!=null) {
					tmpOrder.setRepairEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				} 
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
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				if (rs.getTimestamp("startTime")!=null) {
					tmpOrder.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				} 
				if (rs.getTimestamp("endTime")!=null) {
					tmpOrder.setRepairEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				} 
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
	
//	public static Order[] loadAllFromDateInterval(Connection conn, LocalDate start, LocalDate stop, int reportType) {
//		List<Order> Orders = new ArrayList<>();
//		PreparedStatement pst;
//		String query1 = "SELECT * FROM Orders WHERE startTime BETWEEN ? and ?";
//		String query2 = "SELECT * FROM Orders WHERE endTime BETWEEN ? and ?";
//		Timestamp startReport = Timestamp.valueOf(start.atStartOfDay());
//		Timestamp stopReport = Timestamp.valueOf(start.plusDays(1).atStartOfDay());
//		try {
//			if (reportType==1) {
//				pst = conn.prepareStatement(query1);
//			} else {
//				pst = conn.prepareStatement(query2);
//			}
//			pst.setTimestamp(1, startReport);
//			pst.setTimestamp(2, stopReport);
//			ResultSet rs = pst.executeQuery();
//			while (rs.next()) {
//				Order tmpOrder = new Order();
//				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
//				tmpOrder.setAssignedWorker(assigned).setRepairTime(rs.getDouble("repairTime"))
//						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
//						.setCostPerHour();
//				Orders.add(tmpOrder);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		Order[] OrdersArr = new Order[Orders.size()];
//		Orders.toArray(OrdersArr);
//
//		return OrdersArr;
//	}
//	

	public static boolean saveToDB(Connection conn, Order order) {
		boolean result = false;
		if (order.getId() == 0) {
			String query = "INSERT INTO Orders VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] generatedColumns = { "id" };
			try {
				PreparedStatement pst = conn.prepareStatement(query, generatedColumns);
				pst.setTimestamp(1, java.sql.Timestamp.valueOf(order.getAcceptance()));
				if (order.getRepairStartTime()==null){
					pst.setTimestamp(2, null);
				}
				else {
					pst.setTimestamp(2, java.sql.Timestamp.valueOf(order.getRepairStartTime()));
				}
				
				pst.setInt(3, order.getAssignedWorker().getId());
				pst.setString(4, order.getProblemDescription());
				pst.setString(5, order.getRepairDescription());
				pst.setInt(6, order.getStatusIinInt());
				pst.setInt(7, order.getVehicle().getId());
				pst.setDouble(8, order.getRepairCost());
				pst.setDouble(9, order.getPartsCost());
				pst.setDouble(10, order.getRepairTime());
				if (order.getRepairEndTime()==null){
					pst.setTimestamp(11, null);
				}
				else {
					pst.setTimestamp(11, java.sql.Timestamp.valueOf(order.getRepairEndTime()));
				}
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
						"UPDATE Orders SET accepted=?, startTime=?, worker_id=?, problem_desc=?, solution_desc=?, status=?, vehicle_id=?, repair_cost=?, parts_cost=?, repairTime=?, endTime=? WHERE id=?");

				pst.setTimestamp(1, java.sql.Timestamp.valueOf(order.getAcceptance()));
				if (order.getRepairStartTime()==null){
					pst.setTimestamp(2, null);
				}
				else {
					pst.setTimestamp(2, java.sql.Timestamp.valueOf(order.getRepairStartTime()));
				}
				pst.setInt(3, order.getAssignedWorker().getId());
				pst.setString(4, order.getProblemDescription());
				pst.setString(5, order.getRepairDescription());
				pst.setInt(6, order.getStatusIinInt());
				pst.setInt(7, order.getVehicle().getId());
				pst.setDouble(8, order.getRepairCost());
				pst.setDouble(9, order.getPartsCost());
				pst.setDouble(10, order.getRepairTime());
				if (order.getRepairEndTime()==null){
					pst.setTimestamp(11, null);
				}
				else {
					pst.setTimestamp(11, java.sql.Timestamp.valueOf(order.getRepairEndTime()));
				}
				pst.setInt(12, order.getId());

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
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				if (rs.getTimestamp("startTime")!=null) {
					tmpOrder.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				} 
				if (rs.getTimestamp("endTime")!=null) {
					tmpOrder.setRepairEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				} 
				tmpOrder.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpOrder;

	}
	
	public static Order[] loadByEmployeeId(Connection conn, int id) {

		List<Order> Orders = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Orders WHERE worker_id="+id);
			while (rs.next()) {
				Order tmpOrder = new Order();
				tmpOrder.setAcceptance(rs.getTimestamp("accepted").toLocalDateTime());
				Employee assigned = EmployeeDAO.loadById(conn, rs.getInt("worker_id"));
				tmpOrder.setAssignedWorker(assigned).setProblemDescription(rs.getString("problem_desc"))
						.setRepairDescription(rs.getString("solution_desc")).setStatus(rs.getInt("status"));
				Vehicle vehicle = VehicleDAO.loadById(conn, rs.getInt("vehicle_id"));
				tmpOrder.setVehicle(vehicle).setRepairTime(rs.getDouble("repairTime"))
						.setRepairCost(rs.getDouble("repair_cost")).setPartsCost(rs.getDouble("parts_cost"))
						.setCostPerHour();
				if (rs.getTimestamp("startTime")!=null) {
					tmpOrder.setRepairStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				} 
				if (rs.getTimestamp("endTime")!=null) {
					tmpOrder.setRepairEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				} 
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
	
	public static boolean changeStatus(Connection conn, int id, int status) {
		boolean result = false;
		String query = "UPDATE Orders SET status=? WHERE id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static boolean delete(Connection conn, int id) {
		boolean result = false;
		String query = "UPDATE Orders SET status=5 WHERE id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Usunięto");
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
}
