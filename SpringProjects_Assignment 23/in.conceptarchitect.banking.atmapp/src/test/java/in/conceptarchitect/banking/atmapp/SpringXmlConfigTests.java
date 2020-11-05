package in.conceptarchitect.banking.atmapp;

import static org.junit.Assert.*;
import static in.conceptarchitect.testing.CustomAsserts.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.conceptarchitect.banking.atm.ATM;
import in.conceptarchitect.banking.core.Bank;

public class SpringXmlConfigTests {

	ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("classpath:config/test.config.xml");
	}

	@Test
	public void canAccessBeanByItsName() {
		Object atm = context.getBean("myAtm");
		
		assertNotNull(atm);
		assertType(ATM.class, atm);
	}
	
	@Test
	public void canAccessBeanByItsType() {
		ATM atm=context.getBean(ATM.class);
		assertNotNull(atm);
	}

	@Test
	public void contextConnectsTheObjectsBasedOnPropertyReference() {
		ATM atm=context.getBean(ATM.class);
		assertNotNull(atm.getBank());
	}
	
	
	@Test
	public void canSetPrimitivePropertyOfBankObjectUsingPropertyElement() {
		Bank bank= context.getBean(Bank.class);
		assertEquals("ICICI",bank.getName());
	}
	
	
	@Test
	public void beansAreByDefaultSingleton() {
		
		ATM atm1= context.getBean(ATM.class);
		ATM atm2= context.getBean(ATM.class);
		
		//the two objects are exactly same
		
		assertSame(atm1, atm2);
		assertEquals(atm1.hashCode(),atm2.hashCode());
	}
	
	
	
	
	
}
