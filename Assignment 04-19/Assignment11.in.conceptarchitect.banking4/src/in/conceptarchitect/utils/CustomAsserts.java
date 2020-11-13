package in.conceptarchitect.utils;

import static org.junit.Assert.fail;

public class CustomAsserts {

	public static void assertType(Class expectedType, Object object) {
		if(!expectedType.isAssignableFrom(object.getClass()))
			fail("expectedType:"+expectedType.getSimpleName()+"\tactual type:"+object.getClass().getSimpleName());
	}

}
