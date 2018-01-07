package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.classes.Employee;
import pl.coderslab.classes.Order;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.EmployeeDAO;
import pl.coderslab.dao.OrderDAO;

/**
 * Servlet implementation class reports
 */
@WebServlet("/reports")
public class reports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reports() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("type") != null) {
			String type = request.getParameter("type");
			request.setAttribute("reportType", type);
			HttpSession sess = request.getSession();
			sess.setAttribute("reportType", Integer.parseInt(type));
		} else {

		}

		getServletContext().getRequestDispatcher("/WEB-INF/reports.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		int reportType = (int) sess.getAttribute("reportType");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse((String) request.getParameter("start"), dtf);
		
		LocalDate stop = LocalDate.parse((String) request.getParameter("stop"), dtf);
		try (Connection conn = DbUtil.getConn()) {
			Order[] orders = OrderDAO.loadAll(conn);
			List<Order> reportList = new ArrayList<>();
			if (reportType==1) {
				for (Order order : orders) {
					if (order.getRepairStartTime()!=null) {
					if (order.getRepairStartTime().isAfter(start.atStartOfDay())&&order.getRepairStartTime().isBefore(stop.plusDays(1).atStartOfDay())) {
						reportList.add(order);
					}
					}
				}
				Map<Integer, Double> result = new HashMap<>();
				for (Order order : reportList) {
					Double time = 0.0;
					if (result.containsKey(order.getAssignedWorker().getId())) {
						time = result.get(order.getAssignedWorker().getId());
					}
					double timeToAdd = 0.0;
					try {
						timeToAdd = order.getRepairTime();
					} catch (Exception e) {
					}
					time += timeToAdd;
					result.put(order.getAssignedWorker().getId(), time);
				
				
				
				}
				Map<Employee, Double> result1 = new HashMap<>();
				
				for (Map.Entry<Integer, Double> entry : result.entrySet())
				{
					int id = entry.getKey();
					Employee empl = EmployeeDAO.loadById(conn, id);
					result1.put(empl, entry.getValue());
				}
				
				request.setAttribute("result1", result1);
				
			} else {
				for (Order order : orders) {
					if (order.getRepairEndTime()!=null) {
						if (order.getRepairEndTime().isAfter(start.atStartOfDay())&&order.getRepairEndTime().isBefore(stop.plusDays(1).atStartOfDay())) {
							reportList.add(order);
						}
					}
					
				}
				
				Double result2 = 0.0;
				System.out.println(orders.length);
				System.out.println(reportList.size());
				for (Order order : reportList) {
					
					result2 += order.getRepairCost()-order.getPartsCost();
				}
				
				request.setAttribute("result2", result2);
				
			}
			
			
			
		}catch(

	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	getServletContext().getRequestDispatcher("/WEB-INF/reports.jsp").forward(request, response);

	}

}
