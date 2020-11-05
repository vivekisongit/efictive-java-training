package in.conceptarchitect.banking.reposiotory.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.core.CurrentAccount;
import in.conceptarchitect.banking.core.OverDraftAccount;
import in.conceptarchitect.banking.core.SavingsAccount;
import in.conceptarchitect.banking.repository.AccountRepository;
import in.conceptarchitect.banking.repository.HashmapAccountRepository;

public class CsvAccountRepository implements AccountRepository{
	
	String path;
	AccountRepository baseRepository;
	
	public CsvAccountRepository(String path, AccountRepository baseRepository) {
		super();
		this.path = path;
		this.baseRepository = baseRepository;
		
		load(); //try to fetch existing data
		
	}
	
	

	public CsvAccountRepository(String path) {
		this(path, new HashmapAccountRepository());
	}
	
	







	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		int id=baseRepository.addAccount(account);
		saveAll();
		return id;
		
	}

	@Override
	public BankAccount getAccountById(int accountNumber) {
		// TODO Auto-generated method stub
		return baseRepository.getAccountById(accountNumber);
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		baseRepository.removeAccount(accountNumber);
		saveAll();
		
	}

	@Override
	public Collection<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return baseRepository.getAllAccounts();
	}

	
	public void load() {
	
		BufferedReader reader=null;
		
		try{
			reader=new BufferedReader(new FileReader(path));
			boolean header=true;
			String line=null;
			while((line=reader.readLine())!=null) {
				
				if(header) { //remember first line is just header and not an account
					header=false;
					continue; 
				}
				
				String [] fields= line.split(",");
				BankAccount account=null;
				int accountNumber=Integer.parseInt(fields[1]);
				String name=fields[2];
				String password=fields[3];
				double balance=Double.parseDouble(fields[4]);				
				double odLimit=Double.parseDouble(fields[5]);
				
				
				
				switch(fields[0]) {
					default:case "savingsaccount": account=new SavingsAccount(name, "", balance); break;
					case "currentaccount": account=new CurrentAccount(name,"",balance); break;
					case "overdraftaccount": 
						account=new OverDraftAccount(name,"",balance);
						((OverDraftAccount)account).setOdLimit(odLimit);
						break;
				}
				
				account.setAccountNumber(accountNumber);
				account.setInternalPassword(password);
				
				baseRepository.addAccount(account);
				
			}
		}catch(IOException ex) {
			//if file doesn't exist, we don't have anything read
		}
		finally {
			if(reader!=null)
				try{reader.close();}catch(Exception ex) {}
		}
		
	}
	
	@Override
	public void save(BankAccount account) {
		// TODO Auto-generated method stub
		saveAll();
	}
	
	
	@Override
	public void saveAll() {
		//logic to save the records
		
		PrintWriter writer=null;
		
		try {
			writer=new PrintWriter(path);
			writer.println("Type,AccountNumber,Name,Password,Balance,ODLimit");
			for(BankAccount account: getAllAccounts()) {
				double odLimit=0;
				if(account instanceof OverDraftAccount) {
					OverDraftAccount oda=(OverDraftAccount)account;
					odLimit=oda.getOdLimit();
				}
				writer.printf("%s,%d,%s,%s,%f,%f\r\n",
							account.getClass().getSimpleName().toLowerCase(),
							account.getAccountNumber(),
							account.getName(),
							account.getEncryptedPassword(),
							account.getBalance(),
							odLimit
							);						
							
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			if(writer!=null)
				writer.close();
		}
		
		
	}
	
	
}
