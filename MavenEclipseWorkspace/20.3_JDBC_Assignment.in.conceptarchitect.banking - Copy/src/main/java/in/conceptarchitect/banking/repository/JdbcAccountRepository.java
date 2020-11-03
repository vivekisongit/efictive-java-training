package in.conceptarchitect.banking.repository;

import java.util.Collection;

import in.conceptarchitect.banking.core.BankAccount;

public class JdbcAccountRepository implements AccountRepository {

	public JdbcAccountRepository() {
		// TODO Auto-generated constructor stub
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
