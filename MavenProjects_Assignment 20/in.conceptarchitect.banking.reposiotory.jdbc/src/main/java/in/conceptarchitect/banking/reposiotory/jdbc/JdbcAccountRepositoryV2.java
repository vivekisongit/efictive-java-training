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
import in.conceptarchitect.jdbc.JdbcManager;
import in.conceptarchitect.jdbc.StatementExecutor;

public class JdbcAccountRepositoryV2 implements AccountRepository {
	
	String url,userName,password; //just for time being
	
	
	JdbcManager manager;
	
		public JdbcAccountRepositoryV2(JdbcManager manager) {
		super();
		this.manager = manager;
	}
	
	
	
	

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		
		double odLimit=0;
		if(account instanceof OverDraftAccount) {
			OverDraftAccount od=(OverDraftAccount) account;
			odLimit=od.getOdLimit();
		}
		
		final String qry=String.format("insert into bankaccounts(account_type,name,password,balance,odLimit) "
								+ "values('%s','%s','%s',%f,%f)", 
								  account.getClass().getName(),
								  account.getName(),
								  account.getEncryptedPassword(),
								  account.getBalance(),
								  odLimit
								);
		
		
		return manager.execute( statement -> statement.executeUpdate(qry) );
		
		
		
		
	}
	
	
	public void save(BankAccount account) {
		// TODO Auto-generated method stub
		
		double odLimit= account instanceof OverDraftAccount? ((OverDraftAccount)account).getOdLimit():0;
		
		final String qry=String.format("update bankaccounts "
								+ "set name='%s', password='%s', balance=%f, odLimit=%f"
								+ "where account_number=%d", 
									account.getName(),
									account.getEncryptedPassword(),
									account.getBalance(),
									odLimit,
									account.getAccountNumber()
								);
		
		
		manager.execute(s-> s.executeUpdate(qry));
		
		
	}
	
	
	
	

	@Override
	public BankAccount getAccountById(final int accountNumber) {
		// TODO Auto-generated method stub
		
		return manager.execute(statement -> {
			
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
			} else
				throw new InvalidAccountNumberException(accountNumber);
		});	
		
		
	}

	
		
	
	@Override
	public void removeAccount(final int accountNumber) {
		// TODO Auto-generated method stub
		
		final String qry="delete from bankaccounts where account_number="+accountNumber;		
		
		manager.execute(statement -> statement.executeUpdate(qry));
		
		
	}

	@Override
	public Collection<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		
		
		return manager.execute(statement ->{
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
		
			
		});
			
		
	}

	@Override
	public void saveAll() {
		
		
	}
	
	
	
	
}
