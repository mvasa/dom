import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	 
	 static Connection conn = null;
	 static ResultSet rs = null;
	 static PreparedStatement ps = null;

	public static void openConnection() 
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/testdb",
							" ", pwd);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Opened database successfully");
	}
	
	public static void main(String[] args){
		DBConnection db = new DBConnection();
		DBConnection.openConnection();
	}

	
	
	
	
	
	
	
	
	
	
	
	public static String pwd = "Echotilt58*";
}
