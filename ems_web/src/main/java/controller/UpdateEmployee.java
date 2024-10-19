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

@WebServlet("/update_emp")
public class UpdateEmployee extends HttpServlet {
	
	EmployeeDao eDao = new EmployeeDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String role =  req.getParameter("role");
		Double salary  = Double.parseDouble(req.getParameter("salary"));
		Long phone = Long.parseLong(req.getParameter("phone"));
		
		Employee e = new Employee(id, name, salary, phone, password, role);
		
		RequestDispatcher rd = req.getRequestDispatcher("view_all_emp");
		if(eDao.updateEmployee(e)) {
			req.setAttribute("message", "employee updated successfully");
			rd.forward(req, resp);
		}
		else {
			req.setAttribute("message", "failed to update employee");
			rd.forward(req, resp);
		}
		
		
		
		
	}
}
