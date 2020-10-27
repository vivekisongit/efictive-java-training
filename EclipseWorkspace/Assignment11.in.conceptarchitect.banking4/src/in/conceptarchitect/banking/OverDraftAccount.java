package in.conceptarchitect.banking;

public class OverDraftAccount extends BankAccount {

	public OverDraftAccount(String name, String password, double amount) {
		super(name, password, amount);		
	}
	
	@Override
	public boolean withdraw(double amount, String password) {		
		//write the additional logic that we need
		if(amount>= getBalance()+getOdLimit())
			return false;		
		else
			return super.withdraw(amount, password); //let super class work
		
	}

	public double getOdLimit() {
		return getBalance()%10;
	}

}
