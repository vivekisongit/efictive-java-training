package in.conceptarchitect.utils;

public class PrimeUtils {

	public static boolean isPrime(int value) {
		// TODO Auto-generated method stub
		if(value<2)
			return false;
		for(int i=2;i<value;i++)
			if(value%i==0)
				return false;
		return true;
	}

}
