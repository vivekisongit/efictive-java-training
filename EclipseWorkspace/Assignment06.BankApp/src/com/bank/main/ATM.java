package com.bank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.bank.module.BankAccount;

public class ATM {
	static BankAccount BA=null;
	 
	public static void main(String args[]) throws NumberFormatException, IOException {
		 boolean quit=false;

        while(!quit){     
            
		BufferedReader reader = null;
		System.out.println("#############Welcome to the Bank App##############");
		System.out.println("Please enter your choice");
		System.out.println("1.Create Account");
		System.out.println("2.Deposite Money");
		System.out.println("3.Withdraw Money");	
		System.out.println("4.Show");
		System.out.println("5.Quit");
		String input = "";
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int expression = Integer.parseInt(input);
		
		switch (expression) {
		case 1:
			System.out.println("Please enter account number");
			int accountNo = Integer.parseInt(reader.readLine());

			System.out.println("Please enter name");
			String name = reader.readLine();

			System.out.println("Please enter password");
			String password = reader.readLine();

			System.out.println("Please enter initial deposits");
			int balance = Integer.parseInt(reader.readLine());

			System.out.println("Please enter rate of interest");
			int rate = Integer.parseInt(reader.readLine());
			
			System.out.println("Account created successfully");
			
			BA=new BankAccount(accountNo, name, password, balance, rate);
			break;
		case 2:			
			System.out.println("Please enter Amount");
			int amountDeposit = Integer.parseInt(reader.readLine());
			BA.deposit(amountDeposit);		
			break;
		case 3:
			System.out.println("Please enter Amount");
			int accountNoW = Integer.parseInt(reader.readLine());
			
			System.out.println("Please enter Password");
			String passwordToWithdraw=reader.readLine();			
			BA.withdraw(accountNoW, passwordToWithdraw);			
			break;
		case 4:
			BA.show();
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
