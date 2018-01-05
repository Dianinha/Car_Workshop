package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.VehicleDAO;

/**
 * Servlet implementation class changeOwner
 */
@WebServlet("/changeOwner")
public class changeOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeOwner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("customers"));
		String tmp = request.getParameter("customers");
		int  carId= Integer.parseInt(tmp.substring(0, tmp.indexOf('_')));
		int newOwnerId = Integer.parseInt(tmp.substring(tmp.indexOf('_')+1));
		try {
			VehicleDAO.changeOwner(DbUtil.getConn(), newOwnerId, carId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()	+	"/clients");		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
