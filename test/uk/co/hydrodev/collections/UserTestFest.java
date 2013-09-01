package uk.co.hydrodev.collections;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import java.util.*;

import org.junit.*;
import org.unitils.reflectionassert.*;

public class UserTestFest {

	private static final String TEST_NUMBER_1 = "123 456 789";
	User  user;
	private Set<String> setA;
	private Set<String> setB;
	
	@Before
	public void setUp() throws Exception {
		user = new DefaultUser();
		
		String s1 = "a";
		String s2 = "b";
		
		setA = new LinkedHashSet<>();
		setA.add(s1);
		setA.add(s2);
		
		
		setB = new LinkedHashSet<>();
		setB.add(s2);
		setB.add(s1);
		
	}

	@Test
	public void testUserPhoneNumber() {
		user.addPhoneNumber(TEST_NUMBER_1);
		Collection<String> phones = user.getPhoneNumbers();
		assertNotNull(phones);
		assertEquals(1, phones.size());
		assertTrue(phones.contains(TEST_NUMBER_1));
	}
	
	@Test
	public void testSetContentExact() {
		assertReflectionEquals(setA, setB);
		
	}
	
	@Test
	public void testSetContentRelaxed() {
		assertReflectionEquals(setA, setB,ReflectionComparatorMode.LENIENT_ORDER);
		
	}

}
