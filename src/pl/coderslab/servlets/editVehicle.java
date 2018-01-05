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

import pl.coderslab.classes.Vehicle;
import pl.coderslab.dao.CustomerDAO;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.VehicleDAO;

/**
 * Servlet implementation class editVehicle
 */
@WebServlet("/editVehicle")
public class editVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int carId = Integer.parseInt(request.getParameter("carId"));
		try {
			Vehicle editedVeh = VehicleDAO.loadById(DbUtil.getConn(), carId);
			request.setAttribute("veh", editedVeh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/editVehicle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vehicleId = Integer.parseInt(request.getParameter("carId"));
		int ownerId = Integer.parseInt(request.getParameter("ownerId"));
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String registrationNumber = request.getParameter("regNum");
		int year = Integer.parseInt(request.getParameter("year"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate nextReview = LocalDate.parse((String) request.getParameter("nextRev"), dtf);
		Vehicle tmp = new Vehicle(model, brand, year, registrationNumber, nextReview);
		tmp.setId(vehicleId);
		
		try {
			tmp.setOwner(CustomerDAO.loadById(DbUtil.getConn(), ownerId));
			VehicleDAO.saveToDB(DbUtil.getConn(), tmp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/clients");
	}

}
