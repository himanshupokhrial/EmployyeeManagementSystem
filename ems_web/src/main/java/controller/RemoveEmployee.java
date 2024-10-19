package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.EmployeeDao;

@WebServlet("/remove_emp")
public class RemoveEmployee extends HttpServlet{
	
	EmployeeDao eDao = new EmployeeDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id")) ;
		
		RequestDispatcher rd = req.getRequestDispatcher("view_all_emp");
		
		if(eDao.removeEmployee(id)) {
			req.setAttribute("message", "Employee Removed successfully");
			rd.forward(req, resp);
		}
		else {
			req.setAttribute("message", "Failed to remove employee");
			rd.forward(req, resp);
		}
		
		
	}
}
