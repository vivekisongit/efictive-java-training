package in.conceptarchitect.banking.transaction;

public class TransactionInfo implements Transaction {
	int accountNumber;
	String description;
	double amount;
	TransactionType type; //debit or credit
	boolean isSuccess;

	@Override
	public TransactionInfo execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
