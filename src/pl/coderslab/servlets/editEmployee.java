package pl.coderslab.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.classes.Employee;
import pl.coderslab.dao.DbUtil;
import pl.coderslab.dao.EmployeeDAO;

@WebServlet("/editEmployee")
public class editEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    public editEmployee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		try {
			Employee editedEmployee = EmployeeDAO.loadById(DbUtil.getConn(), id);
			request.setAttribute("empl", editedEmployee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/editEmployeeForm.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int phone = Integer.parseInt(request.getParameter("phone"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String note = request.getParameter("note");
		double cost = Double.parseDouble(request.getParameter("cost"));
		Employee tmp = new Employee(name, surname, address, phone, note, cost);
		int id = Integer.parseInt(request.getParameter("id"));
		tmp.setId(id);
		
		try {
			EmployeeDAO.saveToDB(DbUtil.getConn(), tmp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/employees");
	}

}
