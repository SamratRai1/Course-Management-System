package course.files;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;


public class DatabaseConnection {
	
		private Connection con;
		private Statement st;
	
	    private Statement connect() {

	        try {

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management_course","root","");
	            
	            st = con.createStatement();
	            return st;
	        }

	        catch(Exception e) {

	            System.out.println("Failed");
	            return st;
	        }

	}

	   
	
public Statement getConn(){
		   
	DatabaseConnection conn = new DatabaseConnection();
	Statement st = conn.connect();
	
	return st;
	
  }
}

