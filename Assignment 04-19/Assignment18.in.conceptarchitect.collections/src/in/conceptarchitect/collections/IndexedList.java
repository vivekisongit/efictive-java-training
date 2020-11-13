package in.conceptarchitect.collections;

public interface IndexedList<Data> {

	IndexedList<Data> add(Data value);

	Data get(int pos);

	IndexedList<Data> set(int pos, Data value);

	int size();
	
	//useful default methods
	
	default boolean isEmpty() {
		return size()==0;
	}
	
	default IndexedList<Data> addMany(Data ...values) {
		for(Data value: values)
			add(value);
		
		return this;
	}

	
	default IndexedList<Data> fill(Data data){
		for(int i=0;i<size();i++)
			set(i,data);
		
		return this;
	}
	
	default IndexedList<Data> spread(int start, Data...values){
		for(Data value:values) {
			set(start++, value);
		}
		
		return this;
	}	
	
	static <E> IndexedList<E> createList(E...values){
		
		return new LinkedList<E>().addMany(values);			
		
	}
	
	static <E> IndexedList<E> createArray(E...values){
		
		return new DynamicArray<E>(values.length).addMany(values);			
		
	}
	default IndexedList<Data> addOnlyEven(Data ...values){
		for(Data value: values) {
			if((int)value%2==0) {
				add(value);
			}
		}
		return this;		
	}
	
	default IndexedList<Data> addOnlyOdd(Data ...values){
		for(Data value: values) {
			if((int)value%2!=0) {
				add(value);
			}
		}
		return this;		
	}
	
	default IndexedList<Data> addOnlyLessThanHundredAndMultipleOfSeven(Data ...values){
		for(Data value: values) {
			if((int)value<100 &&(int)value%7==0) {
				add(value);
			}
		}
		return this;		
	}
	
	
	
	
	
}