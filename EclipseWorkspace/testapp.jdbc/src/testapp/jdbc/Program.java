package testapp.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Program {


	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		//Jdbc Example	
		
		Properties prop= new Properties();
		
		prop.load(new FileReader("src/config/app.properties")); //load properties from this file
		
		
		
		String url= prop.getProperty("DB_URL");  		//"jdbc:mysql://localhost/bankingdb";  //database access url
		String user=prop.getProperty("DB_USER");		//database access user
		String password=prop.getProperty("DB_PASSWORD"); //database access password
		
		
		//Step 1 --> create a connection
		Connection connection=null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			Statement statement= connection.createStatement();
			
			
			//Step 3 ---> execute a query
			
			ResultSet rs= statement.executeQuery("select * from BankAccounts");
			
			//Step 4 ---> loop through the result set
			
			while(rs.next()) { //moves to the next result and return if there is a next result. first call takes to first result
			
				String accountType= rs.getString("account_type");
				String name=rs.getString("name");
				int accountNumber=rs.getInt("account_number");
				double balance=rs.getDouble("balance");
				
				System.out.printf("%s %d\t%f\t%s\n", accountType, accountNumber,balance, name);
		
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//Step 2 ---> create a statement
				
		
		
	}

}
