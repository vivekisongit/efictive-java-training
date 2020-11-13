package in.conceptarchitect.banking.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloJUnitTest {

	@Test
	public void test() {
		System.out.println("test running");
	}
	
	@Test
	public void test2() {
		System.out.println("I am test 2");
	}
	
	@Test
	public void iAmNotATest() {
		System.out.println("I am not a test");
	}
	
	public void iAmTheLastTest() {
		System.out.println("I am the last test");
	}

}
