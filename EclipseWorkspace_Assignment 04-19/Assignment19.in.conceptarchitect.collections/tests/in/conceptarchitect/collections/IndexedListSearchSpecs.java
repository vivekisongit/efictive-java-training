package in.conceptarchitect.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class IndexedListSearchSpecs {
	
	IndexedList<Integer> numbers;
	IndexedList<String> names;
	
	@Before
	public void init(){
		
		numbers=new LinkedList<Integer>().addMany(2,11,18,21,109,42,53,3, 108, 13, 22, 105);
		names=new DynamicArray<String>(5).addMany("Amit Singh","Jai Kumar","Chetan Deskhmukh","Singh Kumar","Praveen","Steve Jobs","Suman Singh","Ranjan Singh","Manas");
		
	}
	
	private <E> void assertListContainsAll(IndexedList<E> list, E ...values) {		
		for(E value: values) {
			if(! list.contains(value) )
				fail("list doesn't contain expected "+value);
		}		
	}
	
	
	@Test
	public void searchNamesShouldReturn3NamesWithSurnameSingh() {
		
		IndexedList<String> result=null; //search names with Surname as Singh
		
		assertListContainsAll(result, "Amit Singh","Suman Singh","Rajan Singh");
		
		assertEquals(3, result.size());	
		
		
	}
	
	@Test
	public void searchNamesShouldReturnAllNamesBeforeLetterM() {
		
		IndexedList<String> result=null; //search names with Surname as Singh
		
		assertListContainsAll(result, "Amit Singh","Jai Kumar","Chetan Deshmukh");
		
		assertEquals(3, result.size());	
		
		
	}
	
	@Test
	public void searchNamesShouldReturnAllNamesWithoutASurname() {
		
		IndexedList<String> result=null; //search names with Surname as Singh
		
		assertListContainsAll(result, "Praveen","Manas");
		
		assertEquals(2, result.size());	
		
		
	}
		
	
	
	@Test
	public void search_canSearchAllPrimeNumbers() {
		
		IndexedList<Integer> result= null; //numbers.search(...)
		
		assertListContainsAll(result, 2,11,53,3,13,109);
		assertEquals(6, result.size());		
	}
	
	@Test
	public void searchShouldReturnAllEvenNumbers() {
		IndexedList<Integer> result= null; //list.search()
		
		assertListContainsAll(result, 2,18,42,108,22);
		assertEquals(5, result.size());
		
	}
	
	
	@Test
	public void searchShouldReturnMultiplesOf7LessThan100() {
		
		IndexedList<Integer> result=null; //numbers.search
		
		assertListContainsAll(result, 21,42);
		assertEquals(2, result.size());
		
		
	}	
	
	
	
	
	
	
	

}
