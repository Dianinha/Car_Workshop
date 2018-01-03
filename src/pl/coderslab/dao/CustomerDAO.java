package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.classes.Customer;

public class CustomerDAO {

	public static Customer[] loadAll(Connection conn) {
		List<Customer> Customers = new ArrayList<>();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Customers WHERE active=1");
			while (rs.next()) {
				Customer tmpCustomer = new Customer();
				tmpCustomer.setName(rs.getString("name")).setSurname(rs.getString("surname"))
						.setAddress(rs.getString("address")).setPhoneNumber(rs.getInt("phone"));
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				tmpCustomer.setBirthday(birthday);
				tmpCustomer.setId(rs.getInt("id"));
				Customers.add(tmpCustomer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Customer[] CustomersArr = new Customer[Customers.size()];
		Customers.toArray(CustomersArr);

		return CustomersArr;
	}

	public static boolean saveToDB(Connection conn, Customer customer) {
		boolean result = false;
		if (customer.getId() == 0) {
			String query = "INSERT INTO Customers VALUES (null, ?, ?, ?, ?, ?, 1)";
//			String[] generatedColumns = { "id" };
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, customer.getName());
				pst.setString(2, customer.getSurname());
				pst.setString(3, customer.getAddress());
				pst.setInt(4, customer.getPhoneNumber());
				pst.setDate(5, java.sql.Date.valueOf(customer.getBirthday()));

				pst.executeUpdate();
//				ResultSet rs = pst.getGeneratedKeys();
//				if (rs.next()) {
//					customer.setId(rs.getInt(1));
//				}
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				PreparedStatement pst = conn.prepareStatement(
						"UPDATE Customers SET name=?, surname=?, address=?, phone=?, birthday=?, active=1 WHERE id=?");
				pst.setString(1, customer.getName());
				pst.setString(2, customer.getSurname());
				pst.setString(3, customer.getAddress());
				pst.setInt(4, customer.getPhoneNumber());
				pst.setDate(5, java.sql.Date.valueOf(customer.getBirthday()));
				pst.setInt(6, customer.getId());

				pst.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public static Customer loadById(Connection conn, int id) {

		String query = "SELECT * FROM Customers WHERE id=? AND active=1";
		Customer tmpCustomer = new Customer();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tmpCustomer.setName(rs.getString("name")).setSurname(rs.getString("surname"))
						.setAddress(rs.getString("address")).setPhoneNumber(rs.getInt("phone"));
				LocalDate bDay = rs.getDate("birthday").toLocalDate();
				tmpCustomer.setBirthday(bDay);
				tmpCustomer.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpCustomer;

	}

	public static boolean delete(Connection conn, Customer customer) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement(
					"UPDATE Customers SET active=0 WHERE id=?");
			
			pst.setInt(1, customer.getId());
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String query = "DELETE FROM Customers WHERE id=?";
//		PreparedStatement ps;
//		try {
//			ps = conn.prepareStatement(query);
//			ps.setInt(1, customer.getId());
//			ps.executeUpdate();
//			System.out.println("Usunięto");
//			result = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return result;

	}
	public static boolean delete(Connection conn, int id) {
		boolean result = false;
		try {
			PreparedStatement pst = conn.prepareStatement(
					"UPDATE Customers SET active=0 WHERE id=?");
			
			pst.setInt(1, id);
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String query = "DELETE FROM Customers WHERE id=?";
//		PreparedStatement ps;
//		try {
//			ps = conn.prepareStatement(query);
//			ps.setInt(1, customer.getId());
//			ps.executeUpdate();
//			System.out.println("Usunięto");
//			result = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return result;

	}

}
