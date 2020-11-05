package in.conceptarchitect.banking.app;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.client.ATM;
import in.conceptarchitect.utils.Input;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bank icici=new Bank("ICICI", 12); //bank will have bank accounts
		
		seedDummyData(icici); //add some dummy data for test
		
		//runApp(icici);
		
		testApp(icici);
	

	}

	private static void testApp(Bank bank) {
		// TODO Auto-generated method stub
		
		testWithdraw(bank,1, 40000,"2222");
		//testWithdraw(bank,1, 40000,"1111");
		
		testTransfer(bank,1,45000,"1111",2);
	}

	private static void testWithdraw(Bank bank, int accountNumber, int amount, String password) {
		// TODO Auto-generated method stub
		if( bank.withdraw(accountNumber, amount, password))
			System.out.println("withdrawal success");
		else
			System.out.println("withdrawal failed");		
	}

	private static void testTransfer(Bank bank, int accountNumber, int amount, String password, int toAccount) {
		// TODO Auto-generated method stub
		if(bank.transfer(accountNumber, amount, password, toAccount))
			System.out.println("transfer success");
		else
			System.out.println("transfer failed");		
	}

	

	private static void runApp(Bank icici) {
		ATM atm=new ATM(); //should be connected to some bank
			
		atm.connectTo(icici); //assoicate the hardware with the Bank		
		
		atm.start();  //switch on the ATML machine
	}

	private static void seedDummyData(Bank bank) {
		// TODO Auto-generated method stub		
		bank.openAccount("Vivek", "1111", 50000);
		bank.openAccount("Sanjay", "2222", 50000);
		bank.openAccount("Shivanshi", "3333", 50000);
		
	}
}
