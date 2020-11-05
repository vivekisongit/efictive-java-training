package in.conceptarchitect.banking.atmapp;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.core.CurrentAccount;
import in.conceptarchitect.banking.core.OverDraftAccount;
import in.conceptarchitect.banking.core.SavingsAccount;

public class AccountCreator<T> implements ObjectCreator<T> {

	@Override
	public BankAccount<T> create(String accountType, String name, String password, double amount) {	
		
		switch (accountType.toLowerCase()) {
		default:
		case "savings":
			return  new SavingsAccount(name, password, amount);
			
		case "current":
			return  new CurrentAccount(name, password, amount);
			
		case "overdraft":
			return  new OverDraftAccount(name, password, amount);			
		}	
		
	}

}
