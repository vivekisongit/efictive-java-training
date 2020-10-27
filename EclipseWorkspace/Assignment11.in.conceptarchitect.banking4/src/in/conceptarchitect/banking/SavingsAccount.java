package in.conceptarchitect.banking;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String name, String password, double amount) {
		super(name, password, amount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		
		//write the additional logic that we need
		if(amount> getBalance()-5000)
			return false;		
		else
			return super.withdraw(amount, password); //let super class work
		
	}

}
