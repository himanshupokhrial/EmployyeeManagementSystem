package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.dto.Employee;

public class EmployeeDao {
	
	FileInputStream fis;
	Properties property = new Properties();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	Statement stm;
	
	
	{
		
		try {
			fis = new FileInputStream("C:\\Users\\kadam\\OneDrive\\Desktop\\My Adv Java\\Batch\\M4\\servlet\\ems_web\\src\\main\\resources\\data.properties");
			property.load(fis);
			
			String driver = property.getProperty("driver");
			
			String url = property.getProperty("url");
			
			// Step 1 : Load the Driver
			Class.forName(driver);
			
			
			// Step 2 : Establish the Connection
			
			con = DriverManager.getConnection(url, property);
			
			System.out.println("Connection Done .. !");
			
			
			// Step 3 Creating object of Statement 
			
			stm = con.createStatement();
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // eof non static initilizer
	
	
	
	public boolean adminValidate(Employee emp) {
		
		String query = "select password, role from employee where id = ? ";
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setInt(1, emp.getId());
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				if(rs.getString(1).equals(emp.getPassword()) && rs.getString(2).equalsIgnoreCase("admin")) {
					return true;
				}
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return false;
		
		
	}

	
	public boolean addEmployee(Employee emp) {
		
		String query = "insert into employee(name, salary, phone, password,role) values(?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setLong(3, emp.getPhone());
			ps.setString(4, emp.getPassword());
			ps.setString(5, emp.getRole());
			
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return false;
	}
	
	
	public Employee getEmployeeById(int id ) {
		
		String query = "select * from employee where id = ?";
		
		try {
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
			
				return new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getLong(4), rs.getString(5), rs.getString(6));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public List<Employee> getAllEmployee(){
		
		List<Employee> emps = new ArrayList<>();
		
		String query = "select * from employee ";
		
		try {
			rs = stm.executeQuery(query);
			
			if(rs.next()) {
				do {
					
					emps.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"), rs.getLong("phone"), rs.getString("password"), rs.getString("role")));
				}
				
				while(rs.next());
				
				return emps;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
	}
	
	public boolean removeEmployee(int id) {
		String query = "delete from employee where id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean updateEmployee(Employee emp) {
		
		String query = "update employee set name = ? , salary = ? , phone = ?, password = ? , role = ? where id = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setLong(3, emp.getPhone());
			ps.setString(4, emp.getPassword());
			ps.setString(5, emp.getRole());
			ps.setInt(6, emp.getId());
			
			if(ps.executeUpdate() > 0 ) {
				return true;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
		
	}

	public static void main(String[] args) {
		EmployeeDao eDao = new EmployeeDao();
		Employee e = new Employee(1, "Raju Rajan", 25000, 8899788, "1234", "Finance");
		System.out.println(eDao.updateEmployee(e));
		
		
		
		
		
		
		
		
		// System.out.println(eDao.removeEmployee(2));
		
		
		/*
		List<Employee> emps = eDao.getAllEmployee();
		
		for(Employee e : emps ) {
			System.out.println(e);
		}
		
		*/
		
		// ------------------------------------------------------
		
		
		
		// System.out.println(eDao.getEmployeeById(101));
		
		
		
		
		/*
			addEmployee() test
		Employee e = new Employee("Raju", 20000, 78987876, "1234", "Developer");
		
		System.out.println(eDao.addEmployee(e));
		
		*/
		
		
		/* adminValidate() testing
		Employee e = new Employee();
		e.setId(100);
		e.setPassword("12348");
		
		System.out.println(eDao.adminValidate(e));
		
		*/

	}
	
	
	
	
	
}
