package in.conceptarchitect.collections;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IntListSearchSpecs {

	IntLinkedList list;
	int [] values= {2,11,18,21,109,42,53,3, 108, 13, 22, 105};
	
	@Before
	public void setUp() throws Exception {
		
		list=new IntLinkedList();
		for(int value:values) {
			list.add(value);
		}
		
	}

	
	private void assertListContainsAll(IntLinkedList list, int ...values) {
	
		for(int value: values) {
			if(! list.contains(value) )
				fail("list doesn't contain expected "+value);
		}
		
	}
	
	
	@Test
	public void searchShouldReturnAllEvenNumbers() {
		IntLinkedList result= list.searchEvens();
		
		assertListContainsAll(result, 2,18,42,108,22);
		assertEquals(5, result.size());
		
	}
	
	@Test
	public void searchShouldReturnAllPrimeNumbers() {
		IntLinkedList result=list.searchPrimes();
		assertListContainsAll(result, 2,11,53,3,13,109);
		assertEquals(6, result.size());
	}
	
	@Test
	public void searchShouldReturnMultiplesOf7LessThan100() {
		
		IntLinkedList result=list.searchMultiplesOf7Under100();
		
		assertListContainsAll(result, 21,42);
		assertEquals(2, result.size());
		
		
	}	

}
