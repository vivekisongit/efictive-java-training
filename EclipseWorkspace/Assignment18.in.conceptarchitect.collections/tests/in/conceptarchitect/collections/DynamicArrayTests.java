package in.conceptarchitect.collections;

import static in.conceptarchitect.collections.MyAsserts.assertStringContains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
public class DynamicArrayTests {

	DynamicArray<String> list;
	String first="first";
	String last="last";
	String [] testValues;
	int size=0;
	int growSize=3;
	
	
	
	
	
	@Before
	public void setUp() throws Exception {
		list=new DynamicArray<String>(growSize);
		testValues=new String[] { first,"a","b","c",last};
		for(String testValue:testValues)
			list.add(testValue);
		
		size=list.size();
	}
	
	
	@Test
	public void init_listShouldBeEmpty() {
		
		IndexedList<String> list=new DynamicArray<String>(growSize);
		
		
		
		assertEquals("DynamicArray(empty)", list.toString());
		
		assertTrue(list.isEmpty());
		
	}
	
	@Test
	public void addMany_canAddManyItemsToTheListAtOnce() {
		
		DynamicArray<Integer> numbers=new DynamicArray<Integer>(3);
		
		numbers.addMany(1,2,3,4,5,6,7,8,9,10);
		
		assertEquals(10, numbers.size());
		
		LinkedList<String> names=new LinkedList<String>();
		
		names.addMany("India","USA","UK","UAE","France");
		
		assertEquals(5, names.size());
		
		
		
	}
	
	
	
	@Test
	public void init_listHas0Size() {
		DynamicArray<String> arr=new DynamicArray<String>(growSize);
		
		assertEquals(0, arr.size());
	}
	
	@Test
	public void init_listHasCapacityEqualsGrowSize() {
		DynamicArray<String> arr=new DynamicArray<String>(growSize);
		
		assertEquals(growSize, arr.capacity());
	}
	
	@Test
	public void add_growSizeItemsMakesArraySizeEqualsCapacity() {
		DynamicArray<Integer> arr=new DynamicArray<>(growSize);
		
		for(int i=0;i<growSize;i++)
			arr.add(i);
		
		assertEquals(arr.capacity(), arr.size());
		
		
	}
	
	@Test
	public void add_beyondGrowSizeIncreasesCapacityByGrowSize() {
		DynamicArray<Integer> arr=new DynamicArray<>(growSize);
		
		for(int i=0;i<growSize;i++)
			arr.add(i);
		
		arr.add(100); //this should increase capacity by growSize
		
		assertEquals(growSize*2, arr.capacity());
		
		
	}
	
	
	
	
	

	@Test
	public void size_shouldBe0ForNewList() {
		
		IndexedList<String> list=new DynamicArray<>(growSize);
		
		
		assertEquals(0,list.size());
	}
	
	@Test
	public void add_increasesListSize() {
		
		IndexedList list=new DynamicArray(growSize);
		
		list.add("one");
		list.add("two");
		
		assertEquals(2,list.size());
	}
	
	
	
	@Test
	public void toString_shouldIncludeAddedItems() {
	
		list.add("one");
		list.add("two");
		
		assertStringContains("one", list.toString());
		assertStringContains("two", list.toString());
		
		
	}
	
	@Test
	public void get_0shouldReturnFirstItem() {
	
		assertEquals(first, list.get(0));
	}
	
	@Test
	public void get_sizeMinus1ShouldReturnLastItem() {
		
		
		assertEquals(last, list.get(list.size()-1));
	}
	
	@Test
	public void get_minus1ShouldReturnLastItem() {
		
		assertEquals(last, list.get(-1));
	}
	
	@Test
	public void get_returnsEachItemInZeroBasedIndex() {
	
		
		for(int i=0;i<testValues.length;i++) {
			assertEquals(testValues[i], list.get(i));
		}
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void get_shouldThrowIndexOutOfBoundsForInvalidIndex() {
		list.get(100);
	}
	
	@Test
	public void set_shouldReplaceExistingItem() {

		String newValue="new value";
		list.set(0, newValue );
		
		assertEquals(newValue, list.get(0));
	}
	
	@Test
	public void set_doesntAddNewItem() {
	
		String newValue="new value";
		list.set(1, newValue);
		
		assertEquals(size, list.size());
		
	}

	
	@Test
	public void set_0shouldModifyFirstItem() {
		
		String currentFirstItem= list.get(0);
		String newFirstItem= currentFirstItem+"-modified";
		
		list.set(0, newFirstItem);
		
		assertNotEquals(currentFirstItem, list.get(0));
		assertEquals(newFirstItem,list.get(0));
	}
	
	//@Test
	public void set_sizeMinus1ShouldModifyLastItem() {
		String newData="new data";
		
		list.set(list.size()-1, newData);
		assertEquals(newData, list.array[list.size()-1]);
	}
	
	//@Test
	public void set_minus1ShouldModifyLastItem() {
		String newData="new data";
		
		list.set(-1, newData);
		assertEquals(newData,list.array[list.size()-1]);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void set_shouldThrowIndexOutOfBoundsForInvalidIndex() {
		list.set(100, "Anything");
	}
	

//	@Test
//	public void add_canAddInteger() {
//		list.add(20);
//		
//	}
	
	
	
	

}
