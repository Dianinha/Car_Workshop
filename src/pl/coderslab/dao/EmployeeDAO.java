package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.classes.Employee;

public class EmployeeDAO {

	public static Employee[] loadAll(Connection conn) {
		List<Employee> Employees = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Employees WHERE active=1");
			while (rs.next()) {
				Employee tmpEmployee = new Employee();
				tmpEmployee.setName(rs.getString("name")).setSurname(rs.getString("surname"))
						.setAddress(rs.getString("address")).setPhoneNumber(rs.getInt("phone"));
				tmpEmployee.setNote(rs.getString("note"));
				tmpEmployee.setCostPerHour(rs.getDouble("cost"));
				tmpEmployee.setId(rs.getInt("id"));
				Employees.add(tmpEmployee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Employee[] EmployeesArr = new Employee[Employees.size()];
		Employees.toArray(EmployeesArr);

		return EmployeesArr;
	}

	public static boolean saveToDB(Connection conn, Employee Employee) {
		boolean result = false;
		if (Employee.getId() == 0) {
			String query = "INSERT INTO Employees VALUES (null, ?, ?, ?, ?, ?, ?, 1)";
			String[] generatedColumns = { "id" };
			try {
				PreparedStatement pst = conn.prepareStatement(query, generatedColumns);
				pst.setString(1, Employee.getName());
				pst.setString(2, Employee.getSurname());
				pst.setString(3, Employee.getAddress());
				pst.setInt(4, Employee.getPhoneNumber());
				pst.setString(5, Employee.getNote());
				;
				pst.setDouble(6, Employee.getCostPerHour());

				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					Employee.setId(rs.getInt(1));
				}
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				PreparedStatement pst = conn.prepareStatement(
						"UPDATE Employees SET name=?, surname=?, address=?, phone=?, note=?, cost=?, active=1 WHERE id=?");
				pst.setString(1, Employee.getName());
				pst.setString(2, Employee.getSurname());
				pst.setString(3, Employee.getAddress());
				pst.setInt(4, Employee.getPhoneNumber());
				pst.setString(5, Employee.getNote());
				;
				pst.setDouble(6, Employee.getCostPerHour());
				pst.setInt(7, Employee.getId());

				pst.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public static Employee loadById(Connection conn, int id) {

		String query = "SELECT * FROM Employees WHERE id=? AND active=1";
		Employee tmpEmployee = new Employee();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tmpEmployee.setName(rs.getString("name")).setSurname(rs.getString("surname"))
						.setAddress(rs.getString("address")).setPhoneNumber(rs.getInt("phone"));
				tmpEmployee.setNote(rs.getString("note"));
				tmpEmployee.setCostPerHour(rs.getDouble("cost"));
				tmpEmployee.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpEmployee;

	}

	public static boolean delete(Connection conn, Employee Employee) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE Employees SET active=0 WHERE id=?");

			pst.setInt(1, Employee.getId());

			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public static boolean delete(Connection conn, int employeeId) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE Employees SET active=0 WHERE id=?");

			pst.setInt(1, employeeId);

			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
}
