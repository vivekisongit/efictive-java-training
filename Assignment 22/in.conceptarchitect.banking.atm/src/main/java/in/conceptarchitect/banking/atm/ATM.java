package in.conceptarchitect.banking.atm;

import in.conceptarchitect.banking.core.Bank;
import in.conceptarchitect.banking.exceptions.BankingException;
import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;
import in.conceptarchitect.utils.Input;

public class ATM {

	public Bank getBank() {
		return bank;
	}

	Bank bank;
	Input keyboard = new Input();
	private int accountNumber;

	public void setBank(Bank bank) {
		// TODO Auto-generated method stub
		this.bank = bank;
	}

	public void start() {

		if(this.bank==null)
			throw new IllegalStateException("ATM is not currently associated with any Bank");
		displayWelcomeScreen();

	}

	private void displayWelcomeScreen() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				accountNumber = keyboard.readInt("accountNumber?");
				if (accountNumber == -999) { // hidden admin menu
					if (keyboard.readString("error:").equals("NIMDA"))
						displayAdminMenu();

				} else
					displayUserMenu();

			}
		} catch (AtmSignalException ex) {
			//Ok we are out of our ATM
			System.out.println("ATM is shutdown");
		}
	}

	private void doChangePassword() {
		// TODO Auto-generated method stub
		String currentPassword=keyboard.readString("current password?");
		String newPassword=keyboard.readString("new password?");
		bank.changePassword(accountNumber, currentPassword, newPassword);
		printSlip("Password changed successfully");
		
	}

	private void displayUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("Welcome " + accountNumber);

		int choice = 0;

		do {
			try {
				choice = keyboard.readInt("1. Deposit  2. Widthraw  3. Transfer  4. Show 5. Change Password 6. Close Account  0. Exit: ");
				switch (choice) {
				case 1:
					doDeposit();
					break;
				case 2:
					doWithdraw();
					break;
				case 3:
					doTransfer();
					break;
				case 4:
					doShow();
					break;
				case 5:
					doChangePassword();
					break;
				case 6:
					doCloseAccount();
					return;

				case 0:
					break;
				default:
					System.out.println("invalid input. retry");

				}

			} catch (InsufficientBalanceException ex) {
				printSlip("Account Number " + ex.getAccountNumber() + " has insufficient balance. Total Deficit:"
						+ ex.getDeficit());
			} catch (BankingException ex) {
				printSlip("Error in account:" + ex.getAccountNumber() + "\t" + ex.getMessage());

			}

			System.out.println();
		} while (choice != 0);

	}

	private void doCloseAccount() {
		// TODO Auto-generated method stub
		String pin = keyboard.readString("pin?");
		bank.closeAccount(accountNumber, pin);
		printSlip("Your account has been closed");

	}

	private void doShow() {
		// TODO Auto-generated method stub
		String pin = keyboard.readString("pin?");
		String accountInfo = bank.getAccountInfo(accountNumber, pin);
		printSlip(accountInfo);

	}

	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount?");
		String pin = keyboard.readString("pin?");
		int toAccount = keyboard.readInt("To?");
		bank.transfer(accountNumber, amount, pin, toAccount);
		printSlip(amount + " transferred to " + toAccount);

	}

	private void doWithdraw() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount? ");
		String pin = keyboard.readString("pin?");

		if (amount % 100 != 0)
			throw new InvalidDenominationException("Amount Must be a mulitple of 100");

		bank.withdraw(accountNumber, amount, pin);
		dispenseCash(amount); // don't worry. we reach here only when withdraw succeeed

	}

	private void dispenseCash(int amount) {

		System.out.println("Please collect your cash :" + amount);

	}

	private void doDeposit() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount? ");
		bank.deposit(accountNumber, amount);
		printSlip("Amount Deposited");
		// no need to handle error here

	}

	private void printSlip(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

	private void displayAdminMenu() {
		// TODO Auto-generated method stub
		int choice;
		do {
			choice = keyboard.readInt("1. Open Account  2. Credit Interest  3. Print Accounts  4. Shutdown  0. Exit: ");
			switch (choice) {
			case 1:
				doOpenAccount();
				break;
			case 2:
				doCreditInterest();
				break;
			case 3:
				doPrintAccounts();
				break;
			case 4:
				throw new AtmSignalException("QUIT SIGNAL");
			case 0:
				break;
			default:
				System.out.println("invalid input. retry");

			}
			System.out.println();
		} while (choice != 0);

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
		String type = keyboard.readString("account type [SavingsAccount/CurrentAccount/OverDraftAccount] ? ");
		String name = keyboard.readString("name?");
		String pin = keyboard.readString("pin?");
		int amount = keyboard.readInt("Initial Balance?");
		int accountNumber = bank.openAccount(type, name, pin, amount);
		printSlip("Your account number is " + accountNumber);

	}

}
