package com.bank.main;

import com.bank.module.Bank;
import com.bank.utils.Input;

public class ATM {
	
	 
	public void startExecution(){
		 boolean quit=false;
		 Input input=new Input();	
		 Bank bank=new Bank();	 
			
		 while (!quit) {				
				int choice = 0;
				int accountNo=0;
				int amt=0;
				choice = input.readInt(
						"0. Create Account\n1. Deposit\n2. Withdraw\n3. Show\n4. Change Password\n5. Credit interest\n6. Close account \n7. Exit\nChoose:");

				switch (choice) {	
				case 0:
					String  name = input.readString("Please enter name");
					String  pass = input.readString("Please enter password");
					amt=input.readInt("Please enter amt");
					bank.openAccount(name, pass, amt);
					
					break;
				case 1:		
					accountNo=input.readInt("Please enter account Number");
					amt = input.readInt("Please enter amount");
					
					bank.deposit(accountNo, amt);
					break;
				case 2:
					accountNo = input.readInt("Please enter account number");
					amt= input.readInt("Please enter amount");
					
					String passwordToWithdraw= input.readString("Please enter password");
					bank.withdraw(accountNo, passwordToWithdraw, amt);
					break;
				case 3:
					accountNo = input.readInt("Please enter account number");
					bank.show(accountNo);
					break;
				case 4:
					accountNo = input.readInt("Please enter account number");
					String oldPassword= input.readString("Please enter old password");
					String newPassword= input.readString("Please enter new password");
					boolean b=bank.changePassword(accountNo,oldPassword, newPassword);
					if(b) {
						System.out.println("Password updated successfully");
					}else {
						System.out.println("Wrong old password");
					}
					break;
				case 5:		
					accountNo = input.readInt("Please enter account number");
					bank.creditInterest(accountNo);					
					break;
				case 6:		
					accountNo = input.readInt("Please enter account number to close");
					bank.closeAccount(accountNo);					
					break;
				case 7:
					quit = true;
					System.out.println("\nThank You. Quitting the Application Now...");
					break;
				default:
					break;
				}
			}
	}

}
