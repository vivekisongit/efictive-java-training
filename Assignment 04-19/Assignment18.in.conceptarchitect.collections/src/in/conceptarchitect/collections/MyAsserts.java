package in.conceptarchitect.collections;

import static org.junit.Assert.fail;

public class MyAsserts {

	public static void assertStringContains(String expectedValue, String string) {
		assertStringContains(expectedValue, string,true);
	}
	
	public static void assertStringContains(String expectedValue, String string, boolean ignoreCase) {
		
		if(ignoreCase) {
			expectedValue=expectedValue.toLowerCase();
			string=string.toLowerCase();
		}
		
		if(string.indexOf(expectedValue)==-1)
			fail("string:"+string+" doesn't contain:"+expectedValue);
			
		
	}

}
