package in.conceptarchitect.banking;

public class OverDraftAccount extends BankAccount{

	double odLimit;
	public OverDraftAccount(String name, String password, double amount) {
		super(name, password, amount);
		adjustOdLimit();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public boolean withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		if(!authenticate(password)) {			
			return false;
		}else if(amount<=0) {
			
			return false;
			
		} else if(amount> balance+odLimit) {
			
			return false;
			
		}else {
			
			balance-=amount;
			
			if(balance<0) {
				double odFee= balance/10;
				balance+=odFee; //odfee is calculated negate
			}
			
			return true;
			
		}
		
	}
	
	
	@Override
	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		if(super.deposit(amount)) {
			adjustOdLimit();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		super.creditInterest(interestRate);
		adjustOdLimit();
	}


	private void adjustOdLimit() {
		double newOdLimit=getBalance()*0.1;
		
		if(odLimit< newOdLimit)
			odLimit=newOdLimit;
	}

	public double getOdLimit() {
		// TODO Auto-generated method stub
		return odLimit;
	}

}
