package demo07.permutation;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n=8;
		int r=3;
		
		System.out.println("please wait...");
		
		long start=System.currentTimeMillis();
		int p = permutation(n, r);
		long end=System.currentTimeMillis();
		
		System.out.println("permutation is "+p);
		System.out.println("total time taken in "+(end-start)+" ms");
		
		
		
		
	}

	private static int permutation(int n, int r) {
		int fn=new Factorial(n);       //this will take 8 seconds
		int fn_r=Factorial.calculate(n-r);   // this will take 5 seconds
		int p= fn/fn_r;
		return p;
	}

}
