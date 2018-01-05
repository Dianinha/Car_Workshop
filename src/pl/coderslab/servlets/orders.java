package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.classes.Order;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.OrderDAO;

/**
 * Servlet implementation class orders
 */
@WebServlet("/orders")
public class orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int statusFilter = 0;
		try {
			statusFilter= Integer.parseInt(request.getParameter("status"));
		} catch (Exception e) {
		}
		try {
			Connection conn = DbUtil.getConn();
			if (statusFilter==0) {
				Order[] orders = OrderDAO.loadAll(conn);
				request.setAttribute("orders", orders);
			} else {
				Order[] orders = OrderDAO.loadAllByStatus(conn, statusFilter);
				request.setAttribute("orders", orders);
			}
			
			
			if (request.getParameter("id")!=null){
				HttpSession sess = request.getSession();
				request.setAttribute("showDetails", "yes");
				int id = Integer.parseInt((String)request.getParameter("id"));
				sess.setAttribute("currentOrderId", id);
				
				Order orderDetailed = OrderDAO.loadById(DbUtil.getConn(), id);
				
				request.setAttribute("dOrder", orderDetailed);
			}
			else {
				HttpSession sess = request.getSession();
//				sess.setAttribute("message", null);
				request.setAttribute("showDetails", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
