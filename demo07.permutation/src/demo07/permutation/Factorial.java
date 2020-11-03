package demo07.permutation;

public class Factorial  implements  Runnable{
	int n;
	int result;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Factorial(int n) {
		this.n=n;
	}
	public static int calculate(int n) {
		
		int fn=1;
		
		while(n>=1) {
			
			ThreadUtils.sleep(1000);
			fn*=n--;
		}
		
		return fn;
	}

	@Override
	public void run() {
		
		calculate(n);
	}

}
