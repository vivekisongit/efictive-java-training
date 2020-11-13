package in.conceptarchitect.banking.reposiotory.binary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.repository.AccountRepository;
import in.conceptarchitect.banking.repository.HashmapAccountRepository;

public class BinaryAccountRepository implements AccountRepository{
	
	String path;
	AccountRepository baseRepository;
	
	public BinaryAccountRepository(String path, AccountRepository baseRepository) {
		super();
		this.path = path;
		this.baseRepository = baseRepository;
		
		load(); //try to fetch existing data
		
	}
	
	

	public BinaryAccountRepository(String path) {
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
	
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			fis=new FileInputStream(path);
			ois=new ObjectInputStream(fis);
			
			baseRepository=(AccountRepository) ois.readObject();
			System.out.println("repository loaded properly");
			
		}catch(Exception ex) {
			//if file doesn't exist, we don't have anything read
			ex.printStackTrace();
		}
		finally {
			if(ois!=null)
				try{
					ois.close();
					fis.close();
				}catch(Exception ex) {}
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
		
		FileOutputStream fs=null;
		ObjectOutputStream oos=null;
		
		try {
			fs=new FileOutputStream(path);
			oos=new ObjectOutputStream(fs);
			
			oos.writeObject(baseRepository); //it saves all account and its internal details
			
		}catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			if(oos!=null) {
				try{
					oos.close();
					fs.close();
				}catch(Exception ex) {}
			}
		}	
		
	}
	
	
}
