package in.conceptarchitect.collections;

public class StringLinkedList {

	Node first;
	Node last;
	int count;
	
	public void add(String value) {
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
	
	public String remove(int pos) {
		return null;
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
	
	
	public String get(int pos) {
	
		Node n = locate(pos);
			
		return n.value;
		
	}

	
	public void set(int pos, String value) {
	
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
	
	
	
	
}
