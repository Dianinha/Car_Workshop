package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.classes.Order;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.OrderDAO;

@WebServlet("/changeOrderStatus")
public class changeOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public changeOrderStatus() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		HttpSession sess = request.getSession();
		sess.setAttribute("currentOrderId", orderId);
		
		int currentStatus = Integer.parseInt(request.getParameter("currentStatus"));

		if (currentStatus == 1) {
			
		try {
			OrderDAO.changeStatus(DbUtil.getConn(), orderId, 2);
			
			sess.setAttribute("message", "Zmieniono status na zaakceptowany");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/orders");
			
		} else if (currentStatus == 2) {
			try {
				OrderDAO.changeStatus(DbUtil.getConn(), orderId, 3);
				Order tmp = OrderDAO.loadById(DbUtil.getConn(), orderId);
				tmp.setRepairStartTime(LocalDateTime.now());
				OrderDAO.saveToDB(DbUtil.getConn(), tmp);
				sess.setAttribute("message", "Zmieniono status na w realizacji");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/orders");
			
		} else if (currentStatus == 3) {
//			try {
//				Order tmp = OrderDAO.loadById(DbUtil.getConn(), orderId);
//				request.setAttribute("order", tmp);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			getServletContext().getRequestDispatcher("/WEB-INF/editOrder.jsp").forward(request, response);
			try {
				OrderDAO.changeStatus(DbUtil.getConn(), orderId, 4);
				Order tmp = OrderDAO.loadById(DbUtil.getConn(), orderId);
				tmp.setRepairEndTime(LocalDateTime.now());
				OrderDAO.saveToDB(DbUtil.getConn(), tmp);
//				request.setAttribute("order", tmp);
				sess.setAttribute("message", "Zmieniono status na gotowe do odbioru");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			response.sendRedirect(request.getContextPath() + "/orders");
//			getServletContext().getRequestDispatcher("/WEB-INF/editOrder.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/editOrder");
			
		} else if (currentStatus == 4) {
			sess.setAttribute("message", "Nie można zmienić statusu gotowego zamówienia");
			response.sendRedirect(request.getContextPath() + "/orders");
			
		} else if (currentStatus == 5) {
			sess.setAttribute("message", " Nie można zmienić statusu anulowanego zamówienia");
			response.sendRedirect(request.getContextPath() + "/orders");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
