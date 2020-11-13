package in.conceptarchitect.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FluentApiTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void add_addsItemToTheEndOfTheList() {
		
		int lastValue=11;
		
		int actualValue= new LinkedList<Integer>()
										.addMany(1,2,3,4,5)
										.add(lastValue)
										.get(-1);
		
		assertEquals(lastValue,actualValue);
										
		
	}
	
	@Test
	public void add_setCanModifyValueAtGivenIndex() {
		
		int modifiedValue=100;
		
		int actualValue= new LinkedList<Integer>()
										.addMany(1,2,3,4,5)
										.set(2, modifiedValue)
										.get(2);
		
		
		
		assertEquals(modifiedValue,actualValue);
										
		
	}
	
	
	
	@Test
	public void fill_canFillEnterListWithASingleValue() {
		
		LinkedList<Integer> list=new LinkedList<Integer>();
		
		list.addMany(1,2,3,4,5).fill(10);
		
		for(int i=0;i<list.size();i++)
			assertEquals(10, (int) list.get(i));
		
	}
	
	
	
	@Test
	public void createList_canCreateALinkedListWithSuppliedValues() {
	
		IndexedList<Integer> values= IndexedList.createList(1,2,3,4,5);
		
		assertTrue(values instanceof LinkedList);
		
		
		
	}
	
	@Test
	public void createArray_canDynamicArrayWithGrowSizeEqualToSuppliedArgumentCount() {
	
		DynamicArray<Integer> values=(DynamicArray<Integer>) IndexedList.createArray(1,2,3,4,5);
		
		assertEquals(5, values.growSize);
		
		
		
	}
	
	

}
