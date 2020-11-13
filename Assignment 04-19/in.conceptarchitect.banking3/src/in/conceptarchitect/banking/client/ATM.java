package in.conceptarchitect.banking.client;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.utils.Input;

public class ATM {
	
	
	Bank bank ;
	Input keyboard=new Input();
	private int accountNumber;
	
	public void connectTo(Bank bank) {
		// TODO Auto-generated method stub
		this.bank=bank;
	}
	
	
	public void start() {
		
		displayWelcomeScreen();		
		
	}


	private void displayWelcomeScreen() {
		// TODO Auto-generated method stub
		while(true) {
			accountNumber=keyboard.readInt("accountNumber?");
			if(accountNumber==-999) {		//hidden admin menu
				if(keyboard.readString("error:").equals("NIMDA"))
					if(displayAdminMenu().equals("QUIT"))
						return;
			}else
				displayUserMenu();
		
		}
	}


	private void displayUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("Welcome "+accountNumber);
		
		int choice;
		do {
			choice=keyboard.readInt("1. Deposit  2. Widthraw  3. Transfer  4. Show 5. Close Account  0. Exit: ");
			switch(choice) {
			case 1:
				doDeposit(); break;
			case 2:
				doWithdraw(); break;
			case 3:
				doTransfer(); break;
			case 4:
				doShow(); break;
			case 5:
				if(doCloseAccount())
					return ;
				else
					break;
			case 0: break;
			default:
				System.out.println("invalid input. retry");
				
			}
			System.out.println();
		}while(choice!=0);
		
		
	}


	private boolean doCloseAccount() {
		// TODO Auto-generated method stub
		String pin=keyboard.readString("pin?");
		if(bank.close(accountNumber, pin)) {
			printSlip("Your account has been closed");
			return true;
		} else {
			printSlip("Request to close account declined");
			return false;
		}
		
	}


	private void doShow() {
		// TODO Auto-generated method stub
		String pin= keyboard.readString("pin?");
		
		String accountInfo=bank.getAccountInfo(accountNumber,pin);
		if(accountInfo!=null)
			printSlip(accountInfo);
		else
			printSlip("Error: unable to fetch the details");
			
		
	}


	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Amount?");
		String pin=keyboard.readString("pin?");
		int toAccount=keyboard.readInt("To?");
		
		if(bank.transfer(accountNumber, amount, pin, toAccount))
			printSlip(amount+" transferred to "+toAccount);
		else
			printSlip("Transfer Request Declined");
		
	}


	private void doWithdraw() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Amount? ");
		String pin=keyboard.readString("pin?");
		if(bank.withdraw(accountNumber, amount, pin))
			dispenseCash(amount);
		else
			printSlip("Unable to widraw");
		
	}


	private void dispenseCash(int amount) {
		// TODO Auto-generated method stub
		if(amount%100==0) { //if not a multiple of 100
			
			System.out.println("Please collect your cash :"+amount);
			
		} else {
			printSlip("Unable to dispense cash. try a multiple of 100");
			bank.deposit(accountNumber, amount); //revert the transaction
		}
			
	}


	private void doDeposit() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Amount? ");
		if(bank.deposit(accountNumber, amount))
			printSlip("Amount Deposited");
		else
			printSlip("Deposit Failed");
	}


	private void printSlip(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}


	private String displayAdminMenu() {
		// TODO Auto-generated method stub
		int choice;
		do {
			choice=keyboard.readInt("1. Open Account  2. Credit Interest  3. Print Accounts  4. Shutdown  0. Exit: ");
			switch(choice) {
			case 1:
				doOpenAccount(); break;
			case 2:
				doCreditInterest(); break;
			case 3:
				doPrintAccounts(); break;
			case 4:
				return "QUIT";
			case 0: break;
			default:
				System.out.println("invalid input. retry");
				
			}
			System.out.println();
		}while(choice!=0);
		return "";
	}


	private void doPrintAccounts() {
		// TODO Auto-generated method stub
		bank.printAccountList();
	}


	private void doCreditInterest() {
		// TODO Auto-generated method stub
		bank.creditInterests();
	}


	private void doOpenAccount() {
		// TODO Auto-generated method stub
		String name=keyboard.readString("name?");
		String pin=keyboard.readString("pin?");
		int amount=keyboard.readInt("Initial Balance?");		
		int accountNumber=bank.openAccount(name, pin, amount);		
		printSlip("Your account number is "+accountNumber);
		
		
	}	
	
	
	
	
	
}
