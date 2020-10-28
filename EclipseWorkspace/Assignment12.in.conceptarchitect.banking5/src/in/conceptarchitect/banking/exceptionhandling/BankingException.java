package in.conceptarchitect.banking.exceptionhandling;

public class BankingException extends RuntimeException{
	String message;
	BankingException(String s){ 
		this.message=s;
	}
	@Override
	public String toString() {
		return "BankingException [message=" + message + "]";
	}
	
}
