package in.conceptarchitect.banking.atmapp;

import in.conceptarchitect.banking.core.BankAccount;

public interface ObjectCreator<T> {
	T create(Class<T> type,Object...arguments);

	default Object create(String type,Object...arguments) {
		try {
			Class<?> cls=Class.forName(type);
			return (Object)create((Class<T>) cls,arguments);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
