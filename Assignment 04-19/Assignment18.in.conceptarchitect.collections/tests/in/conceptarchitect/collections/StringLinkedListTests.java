package in.conceptarchitect.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import static in.conceptarchitect.collections.MyAsserts.*;
public class StringLinkedListTests {

	StringLinkedList list;
	String first="first";
	String last="last";
	String [] testValues;
	int size=0;
	
	@Before
	public void setUp() throws Exception {
		list=new StringLinkedList();
		testValues=new String[] { first,"a","b","c",last};
		for(String testValue:testValues)
			list.add(testValue);
		
		size=list.size();
	}
	
	@Test
	public void init_listShouldBeEmpty() {
		
		StringLinkedList list=new StringLinkedList();
		assertEquals("LinkedList()", list.toString());
		
	}
	
	
	
	

	@Test
	public void size_shouldBe0ForNewList() {
		StringLinkedList list=new StringLinkedList();
		assertEquals(0,list.size());
	}
	
	@Test
	public void add_increasesListSize() {
		
		list.add("one");
		list.add("two");
		
		assertEquals(size+2,list.size());
	}
	
	@Test
	public void adds_addsItemToTheEndOfTheList() {

		String end="last item";
		list.add(end);
		
		assertEquals(end, list.last.value);
	}
	
	@Test
	public void adds_shouldAddValueAsFirstItemInEmptyList() {
	
		StringLinkedList list=new StringLinkedList();
		String first="first";		
		list.add("first");
		
		assertEquals(first,list.first.value);
		
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
		
		assertEquals(newValue, list.first.value);
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
	
	@Test
	public void set_sizeMinus1ShouldModifyLastItem() {
		String newData="new data";
		
		list.set(list.size()-1, newData);
		assertEquals(newData, list.last.value);
	}
	
	@Test
	public void set_minus1ShouldModifyLastItem() {
		String newData="new data";
		
		list.set(-1, newData);
		assertEquals(newData,list.last.value);
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
