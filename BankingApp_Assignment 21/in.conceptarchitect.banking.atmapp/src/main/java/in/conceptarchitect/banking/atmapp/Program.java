package in.conceptarchitect.banking.atmapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import in.conceptarchitect.banking.atm.ATM;
import in.conceptarchitect.banking.core.Bank;
import in.conceptarchitect.banking.reposiotory.jdbc.JdbcAccountRepository;
import in.conceptarchitect.jdbc.JdbcManager;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		//AccountRepository repository= new HashmapAccountRepository();
		String path="src/main/resources/app.properties";
		//System.out.println(new File(path).getAbsolutePath());
		
		Properties prop=new Properties();
		prop.load(new FileReader(path));
		
		
		
		String url=prop.getProperty("DB_URL");
		String user=prop.getProperty("DB_USER");
		String password=prop.getProperty("DB_PASSWORD");
		
		
//		JdbcAccountRepository repository=new JdbcAccountRepository(url);
//		repository.setUserName(user);
//		repository.setPassword(password);
		
		JdbcManager manager=new JdbcManager();
		manager.setUrl(url);
		manager.setPassword(password);
		manager.setUserName(user);
		
		JdbcAccountRepository repository=new JdbcAccountRepository(manager);
	
		
		Bank bank=new Bank("ICICI", 12, repository);		
		//seedDummyAccounts(bank);
		
		
		ATM atm=new ATM();
		atm.connectTo(bank);
		atm.start();
		
		
	}

	private static void seedDummyAccounts(Bank bank) {
		// TODO Auto-generated method stub
		bank.openAccount("SavingsAccount", "Vivek", "1111", 50000);
		bank.openAccount("CurrentAccount", "Sanjay", "1111", 50000);
		bank.openAccount("OverdraftAccount", "Chetan", "1111", 50000);
	}

}
