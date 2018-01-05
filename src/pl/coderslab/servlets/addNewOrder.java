package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.classes.Employee;
import pl.coderslab.classes.Order;
import pl.coderslab.classes.Status;
import pl.coderslab.classes.Vehicle;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.EmployeeDAO;
import pl.coderslab.dao.OrderDAO;
import pl.coderslab.dao.VehicleDAO;

@WebServlet("/addNewOrder")
public class addNewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addNewOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Employee[] employees = EmployeeDAO.loadAll(DbUtil.getConn());
			Vehicle[] vehicles = VehicleDAO.loadAll(DbUtil.getConn());
			request.setAttribute("employees", employees);
			request.setAttribute("vehicles", vehicles);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/WEB-INF/addNewOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order newOrder = new Order();
		newOrder.setAcceptance(LocalDateTime.now());
		int workerId = Integer.parseInt(request.getParameter("employeeId"));
		int vehicleId = Integer.parseInt(request.getParameter("vehId"));
		try {
			newOrder.setAssignedWorker(EmployeeDAO.loadById(DbUtil.getConn(),workerId));
			newOrder.setVehicle(VehicleDAO.loadById(DbUtil.getConn(), vehicleId));
			newOrder.setCostPerHour();
			newOrder.setStatus(Status.ACCEPTED);
			newOrder.setProblemDescription(request.getParameter("problem"));
			OrderDAO.saveToDB(DbUtil.getConn(), newOrder);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/orders");
	}

}
