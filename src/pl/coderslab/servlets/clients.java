package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.classes.Customer;
import pl.coderslab.classes.Vehicle;
import pl.coderslab.dao.CustomerDAO;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.VehicleDAO;

/**
 * Servlet implementation class clients
 */
@WebServlet("/clients")
public class clients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public clients() {
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
			Connection conn = DbUtil.getConn();
			Customer[] clients = CustomerDAO.loadAll(conn);
			request.setAttribute("clients", clients);
			if (request.getParameter("id")!=null){
				request.setAttribute("showCars", "yes");
				int id = Integer.parseInt((String)request.getParameter("id"));
				Vehicle[] vehArr = VehicleDAO.loadAllByClientId(conn, id);
				
				if (vehArr.length==0) {
					request.setAttribute("noCars", "noneFound");
				}
				request.setAttribute("vehicles", vehArr);
				Customer owner = CustomerDAO.loadById(conn, id);
				request.setAttribute("owner", owner);
			}
			else {
				request.setAttribute("showCars", null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
