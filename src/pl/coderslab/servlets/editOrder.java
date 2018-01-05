package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.classes.Employee;
import pl.coderslab.classes.Order;
import pl.coderslab.classes.Vehicle;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.EmployeeDAO;
import pl.coderslab.dao.OrderDAO;
import pl.coderslab.dao.VehicleDAO;

@WebServlet("/editOrder")
public class editOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public editOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession sess = request.getSession();
			int orderId = (int) sess.getAttribute("currentOrderId");
			Order tmp = OrderDAO.loadById(DbUtil.getConn(), orderId);
			Employee[] employees = EmployeeDAO.loadAll(DbUtil.getConn());
			Vehicle[] vehicles = VehicleDAO.loadAll(DbUtil.getConn());
			request.setAttribute("order", tmp);
			request.setAttribute("employees", employees);
			request.setAttribute("vehicles", vehicles);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/editOrder.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		int orderId = (int) sess.getAttribute("currentOrderId");
		try {
			Order tmpOrder = OrderDAO.loadById(DbUtil.getConn(), orderId);
			int emplId = Integer.parseInt(request.getParameter("employeeId"));
			Employee assignedWorker = EmployeeDAO.loadById(DbUtil.getConn(), emplId);
			tmpOrder.setAssignedWorker(assignedWorker);
			tmpOrder.setProblemDescription(request.getParameter("problem"));
			tmpOrder.setRepairDescription(request.getParameter("solution"));
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			Vehicle vehicle = VehicleDAO.loadById(DbUtil.getConn(), vehId);
			tmpOrder.setVehicle(vehicle);
			tmpOrder.setCostPerHour();
			double partsCost = Double.parseDouble(request.getParameter("partsCost"));
			tmpOrder.setPartsCost(partsCost);
			double repairTime = Double.parseDouble(request.getParameter("repairTime"));
			tmpOrder.setRepairTime(repairTime);
			tmpOrder.setRepairCost();
			OrderDAO.saveToDB(DbUtil.getConn(), tmpOrder);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/orders");
				
	}

}
