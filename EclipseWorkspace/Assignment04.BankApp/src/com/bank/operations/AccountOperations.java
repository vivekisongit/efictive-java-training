package com.bank.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.module.AccountInfo;

public class AccountOperations {
	Map<Long, AccountInfo> bankMap=new HashMap<Long, AccountInfo>();
	
	
	public AccountOperations() {
		bankMap=new HashMap<Long, AccountInfo>();
	}
	public void createAccount(long accountNo, String name, String password,int balance) {
		AccountInfo accountInfo=new AccountInfo();
		accountInfo.setAccountNo(accountNo);
		accountInfo.setName(name);
		accountInfo.setPassword(password);
		accountInfo.setBalance(balance);
		System.out.println("Account created with account no:"+accountInfo.getAccountNo());
		bankMap.put(accountNo,accountInfo);
	}

	public void depositeMoney(long accountNo, int amount) {
		if(bankMap.get(accountNo)!=null) {
			AccountInfo accountInfo=bankMap.get(accountNo);
			int balance=accountInfo.getBalance();
			int newBalance=balance+amount;
			accountInfo.setBalance(newBalance);
			System.out.println("Balance in account: "+accountInfo.getBalance());
			bankMap.put(accountNo, accountInfo);
		}
	}
	public int withdrawMoney(long accountNo, int amount) {		
		if(bankMap.get(accountNo)!=null) {
			AccountInfo accountInfo=bankMap.get(accountNo);
			int balance=accountInfo.getBalance();
			if(balance>amount) {
				int newBalance=balance-amount;
				accountInfo.setBalance(newBalance);
				System.out.println("Balance in account: "+accountInfo.getBalance());
				bankMap.put(accountNo, accountInfo);
			}else {
				return 1;
			}			
		}
		return 0;
	}
	public void creditInterest(long accountNo, int rateOfInterest) {
		if(bankMap.get(accountNo)!=null) {
			AccountInfo accountInfo=bankMap.get(accountNo);
			int balance=accountInfo.getBalance();			
			
			int newBalance=(balance*rateOfInterest)/1200;
			accountInfo.setBalance(newBalance);
			System.out.println("Balance in account: "+accountInfo.getBalance());
			bankMap.put(accountNo, accountInfo);
			System.out.println("Credit interested in your account");		
		}
	}
}
