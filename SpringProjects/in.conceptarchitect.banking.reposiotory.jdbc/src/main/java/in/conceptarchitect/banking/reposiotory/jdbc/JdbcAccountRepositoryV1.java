package in.conceptarchitect.banking.reposiotory.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.core.CurrentAccount;
import in.conceptarchitect.banking.core.OverDraftAccount;
import in.conceptarchitect.banking.core.SavingsAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;
import in.conceptarchitect.banking.repository.AccountRepository;

public class JdbcAccountRepositoryV1 implements AccountRepository {
	
	String url;
	String userName="root";
	String password="";

	public JdbcAccountRepositoryV1(String url) {
		// TODO Auto-generated constructor stub
		this.url=url;
	}
	
	

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



	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
			Statement statement=con.createStatement();
			
			double odLimit=0;
			if(account instanceof OverDraftAccount) {
				OverDraftAccount od=(OverDraftAccount) account;
				odLimit=od.getOdLimit();
			}
			
			String qry=String.format("insert into bankaccounts(account_type,name,password,balance,odLimit) "
									+ "values('%s','%s','%s',%f,%f)", 
									  account.getClass().getName(),
									  account.getName(),
									  account.getEncryptedPassword(),
									  account.getBalance(),
									  odLimit
									);
			
			statement.executeUpdate(qry);
			
			return 0;
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if(con!=null) {
				try {con.close();}catch(Exception ex) {}
			}
		}
		
		
		
		return 0;
	}

	@Override
	public BankAccount getAccountById(int accountNumber) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
			Statement statement=con.createStatement();			
			ResultSet rs=statement.executeQuery("select * from bankaccounts where account_number="+accountNumber);
			
			
			if(rs.next()) {
			
				BankAccount account=null;
				String accountType=rs.getString("account_type");
				//int accountNumber=rs.getInt("account_number");
				String name=rs.getString("name");
				String password=rs.getString("password");
				double balance= rs.getDouble("balance");
				double odLimit= rs.getDouble("odLimit");
						
				switch(rs.getString("account_type")) {
					default:case "in.conceptarchitect.banking.core.SavingsAccount":
						account=new SavingsAccount(name,"", balance); break;
					case "in.conceptarchitect.banking.core.CurrentAccount":
						account=new CurrentAccount(name,"", balance); break;
					case "in.conceptarchitect.banking.core.OverDraftAccount":
						account=new OverDraftAccount(name,"", balance); break;
				}
				account.setAccountNumber(accountNumber);
				account.setInternalPassword(password);
				if(account instanceof OverDraftAccount)
					((OverDraftAccount) account).setOdLimit(odLimit);
				
				return account;
				
			}
			else
				throw new InvalidAccountNumberException(accountNumber);
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage(), ex);
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception ex) {
					
				}
			}
		}
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
			Statement statement=con.createStatement();			
			statement.executeUpdate("delete from bankaccounts where account_number="+accountNumber);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage(), ex);
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception ex) {
					
				}
			}
		}

	}

	@Override
	public Collection<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
			Statement statement=con.createStatement();			
			ResultSet rs=statement.executeQuery("select * from bankaccounts");
			
			Collection<BankAccount> accounts=new ArrayList<BankAccount>();
			while(rs.next()) {
			
				BankAccount account=null;
				String accountType=rs.getString("account_type");
				int accountNumber=rs.getInt("account_number");
				String name=rs.getString("name");
				String password=rs.getString("password");
				double balance= rs.getDouble("balance");
				double odLimit= rs.getDouble("odLimit");
						
				switch(rs.getString("account_type")) {
					default:case "in.conceptarchitect.banking.core.SavingsAccount":
						account=new SavingsAccount(name,"", balance); break;
					case "in.conceptarchitect.banking.core.CurrentAccount":
						account=new CurrentAccount(name,"", balance); break;
					case "in.conceptarchitect.banking.core.OverDraftAccount":
						account=new OverDraftAccount(name,"", balance); break;
				}
				account.setAccountNumber(accountNumber);
				account.setInternalPassword(password);
				if(account instanceof OverDraftAccount)
					((OverDraftAccount) account).setOdLimit(odLimit);
				
				accounts.add(account);
				
			}
			
			return accounts;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage(), ex);
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception ex) {
					
				}
			}
		}
		
		
	}

	@Override
	public void saveAll() {
		
		
	}
	
	@Override
	public void save(BankAccount account) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
			Statement statement=con.createStatement();			
			
			double odLimit= account instanceof OverDraftAccount? ((OverDraftAccount)account).getOdLimit():0;
			
			String qry=String.format("update bankaccounts "
									+ "set name='%s', password='%s', balance=%f, odLimit=%f"
									+ "where account_number=%d", 
										account.getName(),
										account.getEncryptedPassword(),
										account.getBalance(),
										odLimit,
										account.getAccountNumber()
									);
			
			statement.executeUpdate(qry);			
		
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage(), ex);
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception ex) {
					
				}
			}
		}
		
	}
	
	
}
