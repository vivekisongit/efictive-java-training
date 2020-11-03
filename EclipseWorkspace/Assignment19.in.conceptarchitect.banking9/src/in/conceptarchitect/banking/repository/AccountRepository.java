package in.conceptarchitect.banking.repository;

import java.util.Collection;

import in.conceptarchitect.banking.BankAccount;

public interface AccountRepository {

	int addAccount(BankAccount account);

	BankAccount getAccountById(int accountNumber);

	void removeAccount(int accountNumber);

	Collection<BankAccount> getAllAccounts();

	default void save() {
		
	}

	default void save(BankAccount account) {
		save();
	}
}