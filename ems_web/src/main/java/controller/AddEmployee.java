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

@WebServlet("/add_emp")
public class AddEmployee extends HttpServlet{
	
	EmployeeDao eDao = new EmployeeDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Double salary = Double.parseDouble(req.getParameter("salary"));
		Long phone = Long.parseLong(req.getParameter("phone"));
		String  password = req.getParameter("password");
		String  role = req.getParameter("role");
		
		Employee emp = new Employee(name, salary, phone, password, role);
		
		RequestDispatcher success = req.getRequestDispatcher("admin_operation.jsp");
		RequestDispatcher fail = req.getRequestDispatcher("add_emp.jsp");
		
		if(eDao.addEmployee(emp)) {
			
			req.setAttribute("message", "Employee Added Successfully");
			success.forward(req, resp);
		}
		else {
			req.setAttribute("message", "Failed to add Employee");
			fail.forward(req, resp);
		}
		
		
		
		
		
		
	}
}
