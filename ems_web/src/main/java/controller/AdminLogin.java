package controller;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.EmployeeDao;
import model.dto.Employee;

@WebServlet("/admin_login")
public class AdminLogin extends HttpServlet {
	
	EmployeeDao eDao = new EmployeeDao();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id")) ;
		String password = req.getParameter("password");
		
		Employee emp = new Employee();
		emp.setId(id);
		emp.setPassword(password);
		
		RequestDispatcher success = req.getRequestDispatcher("admin_operation.jsp");
		RequestDispatcher fail = req.getRequestDispatcher("admin_login.jsp");
		
		if(eDao.adminValidate(emp)) {
			// session management 
			HttpSession session = req.getSession();
			session.setAttribute("admin", emp);
			
			req.setAttribute("message", "Admin Login Success");
			success.forward(req, resp);
		}
		
		else {
			req.setAttribute("message", "Invalid Id or Password");
			fail.forward(req, resp);
		}
		
		
		
		
		
		
	}
}
