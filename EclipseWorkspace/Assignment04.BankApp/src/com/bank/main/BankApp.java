package com.bank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.bank.operations.AccountOperations;

public class BankApp {

	public static void main(String args[]) throws NumberFormatException, IOException {
		 boolean quit=false;

        while(!quit){     
            
		BufferedReader reader = null;
		System.out.println("#############Welcome to the Bank App##############");
		System.out.println("Please enter your choice");
		System.out.println("1.Create Account");
		System.out.println("2.Deposite Money");
		System.out.println("3.Withdraw Money");
		System.out.println("4.Credit Interest");
		System.out.println("5.Quit");
		String input = "";
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int expression = Integer.parseInt(input);
		AccountOperations ao = new AccountOperations();
		switch (expression) {
		case 1:
			System.out.println("Please enter account number");
			long accountNo = Long.parseLong(reader.readLine());

			System.out.println("Please enter name");
			String name = reader.readLine();

			System.out.println("Please enter password");
			String password = reader.readLine();

			System.out.println("Please enter initial deposits");
			int balance = Integer.parseInt(reader.readLine());

			ao.createAccount(accountNo, name, password, balance);
			System.out.println("Account created successfully");
			break;
		case 2:
			System.out.println("Please enter Account No");
			long accountNoDeposit = Integer.parseInt(reader.readLine());
			
			System.out.println("Please enter Amount");
			int amountDeposit = Integer.parseInt(reader.readLine());
			ao.depositeMoney(accountNoDeposit, amountDeposit);
			
			System.out.println("Amount deposited successfully");
			break;
		case 3:
			System.out.println("Please enter Account No");
			long accountNoW = Integer.parseInt(reader.readLine());
			
			System.out.println("Please enter Amount");
			int amountW= Integer.parseInt(reader.readLine());
			
			int msg=ao.withdrawMoney(accountNoW, amountW);
			if(msg==0) {
				System.out.println("Amount withdrawn successfully");
			}else {
				System.out.println("Amount is higher than balance");
			}
			
			break;
		case 4:

			break;
		case 5:
			 quit=true;
             System.out.println("\nThank You. Quitting the Application Now...");
			break;
		default:
			break;
		}
	}
	}

}
