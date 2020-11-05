package in.conceptarchitect.banking.atmapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.conceptarchitect.banking.atm.ATM;
import in.conceptarchitect.banking.core.Bank;
import in.conceptarchitect.banking.reposiotory.jdbc.JdbcAccountRepository;
import in.conceptarchitect.jdbc.JdbcManager;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		ATM atm = configureAtm();
		atm.start();
		
		
	}	
	
	public static ATM configureAtm() {
		
		System.out.println("creating the Application Context...");
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:config/app.config.xml");		
		System.out.println("Application Context is created...");
		
		ATM atm=  (ATM)context.getBean("myAtm");
		//atm.selfCheck();
		return atm;
	}

	
	private static ATM _configureAtm() throws IOException, FileNotFoundException {
		
		
		
		//Step 1. Read the properties file
		String path="src/main/resources/app.properties";
		Properties prop=new Properties();
		prop.load(new FileReader(path));
		String url=prop.getProperty("DB_URL");
		String user=prop.getProperty("DB_USER");
		String password=prop.getProperty("DB_PASSWORD");
		
		

		//Step 2. Create the Jdbc Manager. 
		JdbcManager manager=new JdbcManager();
		//Step 2.1. Inject properties to JdbcManager object
		manager.setUrl(url);
		manager.setPassword(password);
		manager.setUserName(user);
		
		
		//Step 3 Create the Repository Object and Inject JdbcManager here
		JdbcAccountRepository repository=new JdbcAccountRepository(manager);
	
		
		//Step 4. Create Bank object and inject repository here
		Bank bank=new Bank("ICICI", 12, repository);		
		//seedDummyAccounts(bank);
		
		
		//Step 5. create atm object
		
		ATM atm=new ATM();
		//Step 5.1. set bank to ATM
		atm.setBank(bank);
		
		
		//Step 6. get the atm Object
		return atm;
	}

	
	
	
	private static void seedDummyAccounts(Bank bank) {
		// TODO Auto-generated method stub
		bank.openAccount("SavingsAccount", "Vivek", "1111", 50000);
		bank.openAccount("CurrentAccount", "Sanjay", "1111", 50000);
		bank.openAccount("OverDraftAccount", "Chetan", "1111", 50000);
	}

}
