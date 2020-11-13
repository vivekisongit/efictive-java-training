package in.conceptarchitect.banking.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import in.conceptarchitect.banking.core.BankAccount;
import in.conceptarchitect.banking.core.CurrentAccount;
import in.conceptarchitect.banking.core.OverDraftAccount;
import in.conceptarchitect.banking.core.SavingsAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;

public class HashmapAccountRepository implements AccountRepository, Serializable {

	HashMap<Integer, BankAccount> accounts = new HashMap<Integer, BankAccount>();

	private int accountCount = 0;

	public int addAccount(BankAccount account) {

		accounts.put(account.getAccountNumber(), account);
		accountCount++;

		return account.getAccountNumber();
	}

	public BankAccount getAccountById(int accountNumber) {

		if (accounts.containsKey(accountNumber))
			return accounts.get(accountNumber);
		else
			throw new InvalidAccountNumberException(accountNumber);
	}

	public void removeAccount(int accountNumber) {
		if (accounts.containsKey(accountNumber))
			accounts.remove(accountNumber);
		else
			throw new InvalidAccountNumberException(accountNumber);
	}

	public Collection<BankAccount> getAllAccounts() {
		return accounts.values();
	}

	public void seed() {

		BankAccount[] accounts = { new SavingsAccount("Vivek", "hello", 50000),
				new CurrentAccount("EduWorldPublishers", "hello", 50000),
				new OverDraftAccount("Fagun", "hello", 50000) };

		int count = 0;
		for (BankAccount account : accounts) {
			count++;
			account.setAccountNumber(count);
			addAccount(account);
		}

	}

}
