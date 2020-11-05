package in.conceptarchitect.banking.repository;

import java.util.Collection;

import in.conceptarchitect.banking.core.BankAccount;

public interface AccountRepository {

	int addAccount(BankAccount account);

	BankAccount getAccountById(int accountNumber);

	void removeAccount(int accountNumber);

	Collection<BankAccount> getAllAccounts();

	default void saveAll() {
		
	}

	default void save(BankAccount account) {
		saveAll();
	}
}