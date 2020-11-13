package in.conceptarchitect.collections;

import java.util.Arrays;

public class DynamicArray<E> implements IndexedList<E> {

	E [] array;  //this is the inner array to store value
	int growSize;
	int count;
	
	public DynamicArray(int growSize) {
		// TODO Auto-generated constructor stub
		this.growSize=growSize;

		array = (E[]) new Object[growSize];
		
		count=0;
		
		
		
	}

	@Override
	public IndexedList<E> add(E value) {
		// TODO Auto-generated method stub
		ensureCapacity();
		
		array[count]=value;
		count++;
		
		return this;
	}

	private void ensureCapacity() {
		// TODO Auto-generated method stub
		if(count==capacity()) {
			int newSize= capacity()+growSize;			
			array= Arrays.copyOf(array, newSize); //create a larger array and copy my value		
			
		}
	}

	public int capacity() {
		// TODO Auto-generated method stub
		return array.length;
	}

	@Override
	public E get(int pos) {
		// TODO Auto-generated method stub
		pos=validate(pos);
		
		return array[pos];
	}

	@Override
	public IndexedList<E> set(int pos, E value) {
		// TODO Auto-generated method stub
		
		array[validate(pos)]=value;
		
		return this;

	}

	private int validate(int pos) {
		// TODO Auto-generated method stub
		if(pos==-1)
			pos=size()-1;
		
		if(pos<0 || pos>=count)
			throw new IndexOutOfBoundsException(pos);
		
		return pos;
	}
	
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(size()==0)
			return "DynamicArray(empty)";
		
		String s="DynamicArray";
		if(size()==capacity())
			s+="[\t";
		else
			s+="(\t";
		
		for(int i=0;i<size();i++)
			s+=array[i]+"\t";
		
		if(size()==capacity())
			s+="]";
		else
			s+=")";
		
		return s;
	}
	

}
