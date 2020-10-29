package in.conceptarchitect.banking.repository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountFileRepository implements AccountRepository{
	
	
  public void writeBankAccountToCSV(Collection<BankAccount> bankAccount, String filePath) {
    try (PrintWriter writer = new PrintWriter(new File(filePath))) {
      StringBuilder sb = new StringBuilder();
      
      //sb.append("AccountType, AccountNumber, Name, Password, Balance, OdLimit");
      
      sb.append("AccountNumber, Name, Balance");
      Iterator<BankAccount> iterator = bankAccount.iterator();
      while (iterator.hasNext()) {
    	  BankAccount ba=iterator.next();
    	  sb.append('\n');
    	  sb.append(ba.getAccountNumber()+","+ba.getName()+","+ba.getBalance());
    	  sb.append('\n');
      }     

      writer.write(sb.toString());

      System.out.println("done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

  }

@Override
public int addAccount(BankAccount account) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public BankAccount getAccountById(int accountNumber) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void removeAccount(int accountNumber) {
	// TODO Auto-generated method stub
	
}

@Override
public Collection<BankAccount> getAllAccounts() {
	// TODO Auto-generated method stub
	return null;
}
}