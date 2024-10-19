package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.EmployeeDao;
import model.dto.Employee;

@WebServlet("/view_emp")
public class ViewEmployee extends HttpServlet{
	
	EmployeeDao eDao = new EmployeeDao();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Employee emp = eDao.getEmployeeById(id) ;
		
		RequestDispatcher rd = req.getRequestDispatcher("view_emp.jsp");
		
		if(emp != null) {
			
			req.setAttribute("employee", emp);
			rd.forward(req, resp);
			
		}
		else {
			req.setAttribute("employee", null);
			req.setAttribute("message", "Employee does not exist ..!");
			rd.forward(req, resp);
		}
		
		
		
		
	}
	
	
}
