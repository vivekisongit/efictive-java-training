package com.bank.main;

import java.io.IOException;

import com.bank.module.BankAccount;
import com.bank.utils.Input;

public class ATM {
	
	 
	public static void main(String args[]) throws NumberFormatException, IOException {
		 boolean quit=false;
		 Input input=new Input();	
		 BankAccount BA=null;
			
		 while (!quit) {				
				int choice = 0;
				choice = input.readInt(
						"0. Create Account\n1. Deposit\n2. Withdraw\n3. Show\n4. Change Password\n5. Credit interest\n6. Exit\nChoose:");

				switch (choice) {	
				case 0:
					String  name = input.readString("Please enter name");
					String  pass = input.readString("Please enter password");
					BA=new BankAccount(name, pass, 500.00, 10.00);
					break;
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
					String oldPassword= input.readString("Please enter old password");
					String newPassword= input.readString("Please enter new password");
					boolean b=BA.changePassword(oldPassword, newPassword);
					if(b) {
						System.out.println("Password updated successfully");
					}else {
						System.out.println("Wrong old password");
					}
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
