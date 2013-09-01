package uk.co.hydrodev.collections;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;

import java.util.*;

import org.junit.*;

public class UserTest {

	private static final String TEST_NUMBER_1 = "123 456 789";
	User  user;
	private Set<String> setA;
	private Set<String> setB;
	private String s1;
	private String s2;
	
	@Before
	public void setUp() throws Exception {
		user = new DefaultUser();
		
		s1 = "a";
		s2 = "b";
		
		setA = new LinkedHashSet<>();
		setA.add(s1);
		setA.add(s2);
		
		
		setB = new LinkedHashSet<>();
		setB.add(s2);
		setB.add(s1);
		
	}


	
	@Test
	public void testCollectionUtilityMethods() {
		assertThat(setA).isNotEmpty().hasSize(2).doesNotHaveDuplicates();
		assertThat(setA).contains(s1,s2);
	}

	@Test
	public void mapUtilityMethods() {
		HashMap<String,Integer> map = new LinkedHashMap<>();
		map.put("a", 2);
		map.put("br", 3);
		assertThat(map).isNotNull().isNotEmpty().includes(entry("a",2),entry("b",3)).excludes(entry("c",1));
				
	}

}
