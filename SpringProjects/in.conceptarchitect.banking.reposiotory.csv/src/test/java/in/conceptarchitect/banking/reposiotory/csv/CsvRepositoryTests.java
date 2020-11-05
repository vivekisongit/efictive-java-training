package in.conceptarchitect.banking.reposiotory.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.core.SavingsAccount;

public class CsvRepositoryTests {

	String path="src/test/resources/testaccounts.csv";
	File file;
	CsvAccountRepository repository;
	
	@Before
	public void setUp() throws Exception {
		file=new File(path);
		if(file.exists())
			file.delete();
		
		//repository=new CsvAccountRepository(path);
		repository=CsvAccountRepository.loadRepository(path);
	}

	@Test
	public void repositoryCanReferToNonExistentPath() {
		
		assumeFalse(file.exists()); //file doesn't exist yet
		 
		CsvAccountRepository repository=CsvAccountRepository.loadRepository(path);
		
		assertFalse(file.exists()); //file is still not created
		assertNotNull(repository); //repository is created in memory
		
	}

	
	@Test
	public void canSaveEmptyRepository() {
		repository.saveAll();
		assertTrue(file.exists());
	}
	
	@Test
	public void canSaveAndLoadDataFromRepository() {
		String name="Vivek";
		String password="password";
		double balance=10000;
		int accountNumber=1;
		BankAccount account=new SavingsAccount(name,password,balance);
		account.setAccountNumber(accountNumber);
		
		repository.addAccount(account);
		repository.saveAll();
		
		CsvAccountRepository repository2=CsvAccountRepository.loadRepository(path);
		
		BankAccount account2= repository2.getAccountById(accountNumber);
		
		assertEquals(account.getName(), account2.getName());
		
		
	}
	
	
	
	
	
}
