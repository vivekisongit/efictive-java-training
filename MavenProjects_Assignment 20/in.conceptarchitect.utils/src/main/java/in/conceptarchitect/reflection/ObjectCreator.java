package in.conceptarchitect.reflection;

public interface ObjectCreator<T> {

	T create(Class<T> cls, Object ...arguments);
	
	default Object  create(String type, Object... arguments) {
		
		try {
			Class<T> cls=(Class<T>) Class.forName(type);			
			T obj= create(cls,arguments);
			return obj;
		} catch(Exception ex) {
			throw new ObjectCreationException(ex.getMessage(),ex);
		}
		
	}
}
