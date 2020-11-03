package demo07.permutation;

public class ThreadUtils {

	public static void print(Object obj, Object...args) {
		String output="";
		
		if(args.length==0)
			output=obj.toString();
		else
			output=String.format(obj.toString(), args);
		
		System.out.printf("[%d] %s", Thread.currentThread().getId(), output);
	}
	
	
	public static void println(Object obj, Object ...args) {
		// TODO Auto-generated method stub
		
		print(obj+"\n",args);
		
	}
	
	public static void sleep(int ms) {
		
		try {
			Thread.sleep(ms);
		}catch(InterruptedException ex) {
			throw new ThreadInterruptedException(ex.getMessage(),ex);
		}
	}
	
	public static void waitAll(Thread ...threads) {
		
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch(InterruptedException ex) {
				throw new ThreadInterruptedException(ex.getMessage(), ex);
			}
		}
		
	}
	
	
	

}
