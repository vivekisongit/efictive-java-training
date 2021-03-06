package in.conceptarchitect.banking.reposiotory.binary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class BinaryAccountRepositoryTests {

	
	
	
	String path="src/test/resources/testaccounts.csv";
	File file;
	BinaryAccountRepository repository;
	
	@Before
	public void setUp() throws Exception {
		file=new File(path);
		if(file.exists())
			file.delete();
		
		repository=new BinaryAccountRepository(path);
	}

	@Test
	public void repositoryCanReferToNonExistentPath() {
		
		assumeFalse(file.exists()); //file doesn't exist yet
		 
		BinaryAccountRepository repository=new BinaryAccountRepository(path);
		
		assertFalse(file.exists()); //file is still not created
		assertNotNull(repository); //repository is created in memory
		
	}

	
	@Test
	public void canSaveEmptyRepository() {
		repository.saveAll();
		assertTrue(file.exists());
	}
	
}
