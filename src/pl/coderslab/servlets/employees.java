package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.classes.Employee;
import pl.coderslab.classes.Order;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.EmployeeDAO;
import pl.coderslab.dao.OrderDAO;

/**
 * Servlet implementation class employees
 */
@WebServlet("/employees")
public class employees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Employee[] empls = EmployeeDAO.loadAll(DbUtil.getConn());
			request.setAttribute("empls", empls);
			if(request.getParameter("id")!=null){
				int id = Integer.parseInt(request.getParameter("id"));
				Order[] orders = OrderDAO.loadByEmployeeId(DbUtil.getConn(), id);
				request.setAttribute("orders", orders);
				request.setAttribute("showOrders", "yes");
			}
			else {
				request.setAttribute("showOrders", null);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/employees.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
