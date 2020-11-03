package in.conceptarchitect.collections;

import in.conceptarchitect.utils.PrimeUtils;

public class IntLinkedList {

	Node first;
	Node last;
	int count;

	public class Node {
	
		int value;
		Node next;
		Node previous;
		public Node(int value) {
			
			this.value = value;
		}
	}
	
	
	public void add(int value) {
		Node newNode=new Node(value);
		
		if(size()==0) {
			first=newNode;
		} else {
			last.next=newNode;
			newNode.previous=last;
		}
		count++;
		last=newNode;
	}
	
	public int remove(int pos) {
		throw new RuntimeException("Not yet implemented");
	}
	
	private Node locate(int pos) {
		Node n=first;
		if(pos==-1 || pos== size()-1)
			n=last;
		else {
			if(pos<0 || pos>=size())
				throw new IndexOutOfBoundsException(pos);

			for(int i=0;i<pos;i++)
				n=n.next;
			
		}
		return n;
	}
	
	
	public int get(int pos) {
	
		Node n = locate(pos);
			
		return n.value;
		
	}

	
	public void set(int pos, int value) {
	
		locate(pos).value=value;
	}
	
	public int size() {
		return count;
	}
	
	@Override
	public String toString() {
		if(size()==0)
			return "LinkedList()";
		
		String str="LinkedList(\t";
		
		for(Node n=first;n!=null;n=n.next) {
			str+=n.value+"\t";
		}
		
		str+=")";
		
		return str;
	}

	public boolean contains(int value) {
		// TODO Auto-generated method stub
		for(int i=0;i<size();i++) {
			if(get(i)==value)
				return true;
		}
		
		return false;
	}

	public IntLinkedList searchEvens() {
		// TODO Auto-generated method stub
		IntLinkedList result=new IntLinkedList();
		
		for(int i=0;i<size();i++) {
			int value=get(i);
			if(value%2==0)
				result.add(value);
		}		
		return result;
		
	}
	
	public IntLinkedList searchPrimes() {
		// TODO Auto-generated method stub
		IntLinkedList result=new IntLinkedList();
		
		for(int i=0;i<size();i++) {
			int value=get(i);
			if(PrimeUtils.isPrime(value))
				result.add(value);
		}		
		return result;
	}
	
	public IntLinkedList searchMultiplesOf7Under100() {
		// TODO Auto-generated method stub
		IntLinkedList result=new IntLinkedList();
		
		for(int i=0;i<size();i++) {
			int value=get(i);
			if(value%7==0 && value<100)
				result.add(value);
		}		
		return result;
	}
	
	
	
	
}
