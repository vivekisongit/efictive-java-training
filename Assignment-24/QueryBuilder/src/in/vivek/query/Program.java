package in.vivek.query;

public class Program {
	public static void main(String args[]) {
		QueryBuilder qb=new QueryBuilder();
		
		System.out.println("Building query for class BankAccount");
		String strToUpdate=qb.updateReflections(BankAccount.class);		
		System.out.println("Query String = "+strToUpdate.toString());
		
		
		
		
	}
}
