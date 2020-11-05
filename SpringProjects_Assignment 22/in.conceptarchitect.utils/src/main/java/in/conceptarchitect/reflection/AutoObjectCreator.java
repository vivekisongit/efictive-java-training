package in.conceptarchitect.reflection;

import java.lang.reflect.Constructor;

public class AutoObjectCreator<T> implements ObjectCreator<T> {

	@Override
	public T create(Class<T> cls, Object... arguments) {

		Class [] parameterTypes=new Class[arguments.length];
		
		for(int i=0;i<arguments.length;i++)
			parameterTypes[i]=arguments[i].getClass();
		try {
			Constructor [] constructors= cls.getDeclaredConstructors();
			//Constructor ctor= cls.getConstructor(parameterTypes);
			Constructor ctor=constructors[0];
			return (T) ctor.newInstance(arguments);
			
			
		}catch(Exception ex) {
			throw new ObjectCreationException(ex.getMessage(), ex);
		}
		
	}

	

}
