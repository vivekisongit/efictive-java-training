package com.bank.main;

import java.io.IOException;

import com.bank.module.BankAccount;
import com.bank.utils.Input;

public class ATM {
	
	 
	public static void main(String args[]) throws NumberFormatException, IOException {
		 boolean quit=false;
		 Input input=new Input();	
		 BankAccount BA=new BankAccount(12345, "Vivek", "pass", 500.00, 10.00);
			while (!quit) {				
				int choice = 0;
				choice = input.readInt(
						"1. Deposit\n2. Withdraw\n3. Show\n4. Change Password\n5. Credit interest\n6. Exit\nChoose:");

				switch (choice) {			
				case 1:				
					int amountDeposit = input.readInt("Please enter amount");
					BA.deposit(amountDeposit);
					break;
				case 2:
					int amountWithdraw = input.readInt("Please enter amount");
					String passwordToWithdraw= input.readString("Please enter password");
					BA.withdraw(amountWithdraw, passwordToWithdraw);
					break;
				case 3:
					BA.show();
					break;
				case 4:
					String newPassword= input.readString("Please enter new password");
					BA.setPassword(newPassword);
					System.out.println("Password updated successfully");
					break;
				case 5:					
					BA.creditInterest();					
					break;
				case 6:
					quit = true;
					System.out.println("\nThank You. Quitting the Application Now...");
					break;
				default:
					break;
				}
			}
	}

}
