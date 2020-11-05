package in.conceptarchitect.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PrimeUtilsTests {

	
	@Test
	public void number13ShouldBePrime() {
		
		assertTrue(PrimeUtils.isPrime(13));
		
	}
	
	@Test
	public void negativeNumbersAreNotPrime() {
		
		assertEquals(false, PrimeUtils.isPrime(-10));
		assertEquals(false, PrimeUtils.isPrime(-13));
		assertEquals(false, PrimeUtils.isPrime(-11));
		assertEquals(false, PrimeUtils.isPrime(-2));
		
	}
}
