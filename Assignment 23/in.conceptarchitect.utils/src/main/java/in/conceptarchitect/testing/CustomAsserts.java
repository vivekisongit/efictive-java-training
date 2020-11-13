package in.conceptarchitect.testing;

import static org.junit.Assert.fail;

public class CustomAsserts {

	public static void assertType(Class expectedType, Object object) {
		if(!expectedType.isAssignableFrom(object.getClass()))
			fail("expectedType:"+expectedType.getSimpleName()+"\tactual type:"+object.getClass().getSimpleName());
	}

	public static void assertLessThan(double expectedMax, double actualValueShouldBeLess) {
		
		if(expectedMax<=actualValueShouldBeLess)
			fail("expcted value "+expectedMax+" is not less than actual "+actualValueShouldBeLess);
		
	}
	
}
