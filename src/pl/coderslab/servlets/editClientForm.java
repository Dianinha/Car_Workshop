package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.classes.Customer;
import pl.coderslab.dao.CustomerDAO;
import pl.coderslab.dao.DbUtil;

/**
 * Servlet implementation class editClientForm
 */
@WebServlet("/editClientForm")
public class editClientForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editClientForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		try {
			Customer editedCustomer = CustomerDAO.loadById(DbUtil.getConn(), id);
			request.setAttribute("client", editedCustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/editClientForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int phone = Integer.parseInt((String) request.getParameter("phone"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate bday = LocalDate.parse((String) request.getParameter("bday"), dtf);
		String name = (String) request.getParameter("name");
		String surname = (String) request.getParameter("surname");
		String address = (String) request.getParameter("address");
		Customer newClient = new Customer(name, surname, address, phone, bday);
		int id = Integer.parseInt((String) request.getParameter("id"));
		newClient.setId(id);
		try {
			CustomerDAO.saveToDB(DbUtil.getConn(), newClient);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/clients");

	}

}
