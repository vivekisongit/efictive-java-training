package in.conceptarchitect.reflection;

import java.awt.GraphicsDevice.WindowTranslucency;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectionHelper {
	
	static HashMap<Class,Class> translateType=new HashMap<Class,Class>();
	
	static {
		translateType.put(Double.class, double.class);
		translateType.put(Integer.class, int.class);
	}
	
	

	public static <T> void set(Object obj, String property, T value) {
		
		try {
			
			//covert name property to getName()
			String methodName= String.format("set%s%s",Character.toUpperCase(property.charAt(0)), property.substring(1));
			Class cls=obj.getClass();
			
			Method method=getMethod(cls,methodName,value.getClass()); //try getting the value using getter
			if(method!=null)
				method.invoke(obj, value); //set the value using setter
			else {
				Field field=getField(cls,property);
				if(field!=null) {
					field.setAccessible(true);
					field.set(obj, value ); //return the field value directly	
				}
						
				
			}
				
		} catch(Exception ex) {
			//ex.printStackTrace();
			System.out.println(ex.getClass().getName()+"No "+property+" in "+obj.getClass().getSimpleName());
			
		}
	}
	
	public static <T> T get(Object obj, String property, T defaultValue) {
		
		try {
			
			//covert name property to getName()
			String methodName= String.format("get%s%s",Character.toUpperCase(property.charAt(0)), property.substring(1));
			Class cls=obj.getClass();
			
			Method method=getMethod(cls, methodName);
			if(method!=null)
				return (T)method.invoke(obj);
			else {
				Field field=getField(cls, property);
				if(field!=null) {
					field.setAccessible(true);
					return (T) field.get(obj); //return the field value directly	
				} else {
					return defaultValue;
				}
			}
				
		} catch(Exception ex) {
			ex.printStackTrace();
			return defaultValue;
		}
		
	}

	public static Field getField(Class cls, String fieldName) {
		
		try {
			return cls.getField(fieldName); //get any public field that is declared by this class or a super class
		} catch(NoSuchFieldException ex) {
			try {
			return cls.getDeclaredField(fieldName);  //get any field of any scope declared by this class itself
			}catch(NoSuchFieldException ex2) {
				return null;
			}
			
		}
	}
	
	
	public static Method getMethod(Object obj, String methodName, Object ...arguments) {
		return getMethod(obj.getClass(),methodName,arguments);
	}
	
	public static Method getMethod(Class cls, String methodName, Object ...arguments) {
		try {
			return cls.getClass().getMethod(methodName, getParameterTypes(arguments));
		}catch(Exception ex) {
			return null;
		}
	}
	
	
	private static Class[] getParameterTypes(Object[] arguments) {
		// TODO Auto-generated method stub
		Class[] types= new Class[arguments.length];
		for(int i=0;i<arguments.length;i++) {
			Class cls=arguments[i].getClass();
			if(translateType.containsKey(cls))
				cls=translateType.get(cls);
			types[i]=cls;
		}
		
		return types;
	}

	public static Object create(String type, Object ...arguments) {
		AutoObjectCreator<?> creator=new AutoObjectCreator<>();
		
		return  creator.create(type, arguments);
	}
	
	
}
