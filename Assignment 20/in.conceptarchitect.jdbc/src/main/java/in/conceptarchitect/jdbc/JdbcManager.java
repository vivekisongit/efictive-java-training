package in.conceptarchitect.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcManager {

	String url;
	String userName;
	String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public <T> T execute(StatementExecutor<T> executor) {

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement statement = con.createStatement();

			return executor.executeStatement(statement);

		} catch (SQLException ex) {
			throw new JdbcException(ex.getMessage(), ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
					throw new JdbcException(ex.getMessage(), ex);
				}
			}
		}

	}

	
	public <T> T executeUpdate(final String qry) {
		
		return (T) execute(statement-> statement.executeUpdate(qry));
		
		
	}
	
	
	public <T> Collection<T>  queryAll(final String qry, ResultSetMapper<T> mapper){
		
		return execute(statement->{
			
			ResultSet rs= statement.executeQuery(qry);
			
			ArrayList<T> result=new ArrayList<T>();
			
			while(rs.next()) {
				
				T object= mapper.map(rs);
				result.add(object);				
			}
			
			
			return result;
			
		});
		
		
		
	}
	
	
	
	public <T> T  queryOne(final String qry, ResultSetMapper<T> mapper){
		
		return execute(statement->{
			
			ResultSet rs= statement.executeQuery(qry);
			
			T object=null;
			
			if(rs.next()) {
				
				object= mapper.map(rs);
								
			}
			
			
			return object;
			
		});
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
