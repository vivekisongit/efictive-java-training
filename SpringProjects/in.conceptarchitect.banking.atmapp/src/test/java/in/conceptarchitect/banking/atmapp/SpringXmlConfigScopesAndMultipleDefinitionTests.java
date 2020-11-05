package in.conceptarchitect.banking.atmapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.conceptarchitect.banking.atm.ATM;
import in.conceptarchitect.banking.core.Bank;

public class SpringXmlConfigScopesAndMultipleDefinitionTests {

	ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("classpath:config/multiple-beans-and-scopes.xml");
	}

		
	@Test
	public void differentBeanDefinitionsReturnDifferentObjects() {
		Bank bank1 = context.getBean("icici", Bank.class); //get by name
		Bank bank2 = context.getBean("hdfc", Bank.class);
		
		assertNotEquals(bank1, bank2);
		
		assertEquals(3, bank1.getInterestRate(),0);
		assertEquals(4, bank2.getInterestRate(),0);
	}
	
	@Test(expected = NoUniqueBeanDefinitionException.class)
	public void getBeansByTypeShouldFailIfMultipleBeansOfSameTimeExists() {
		Bank bank= context.getBean(Bank.class); //there are two definition of this type representing different Bank
	}
	
	
	@Test
	public void contextCanReturnAllBeansMatchingAGivenType() {
		Map<String,Bank> allBankBeans=context.getBeansOfType(Bank.class);
		assertEquals(2, allBankBeans.size());
				
	}
	
	@Test
	public void defaultScopeOfABeanIsSingletonForABeanDefintion() {
		Bank b1= context.getBean("icici", Bank.class);
		Bank b2= context.getBean("icici", Bank.class);
		Bank b3= context.getBean("hdfc", Bank.class);
		
		assertSame(b1, b2);  //b1 and b2 are same object
		
		assertNotSame(b2, b3); //b2 and b3 are different object		
		
	}
	
	@Test
	public void getBeanReturnsANewObjectEveryTimeABeanWithPrototypeScopeIsRequested() {
		
		ATM atm1= context.getBean(ATM.class); //creates object on call
		ATM atm2= context.getBean(ATM.class); //creates object on call
		
		assertNotSame(atm1, atm2);
		
		
	}
	
	@Test
	public void twoPrototypeBeansMayReferToSameSingletonBean() {
		
		ATM atm1= context.getBean(ATM.class); //creates object on call
		ATM atm2= context.getBean(ATM.class); //creates object on call
		
		assertNotSame(atm1, atm2);
		
		
		//multiple ATM machines will refer to same Bank
		assertSame(atm1.getBank(), atm2.getBank());
		
		
	}
	
	
	
	
	
	
}
