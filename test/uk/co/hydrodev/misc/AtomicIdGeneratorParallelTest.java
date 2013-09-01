package uk.co.hydrodev.misc;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.google.code.tempusfugit.concurrency.*;
import com.google.code.tempusfugit.concurrency.annotations.*;

public class AtomicIdGeneratorParallelTest {

	@Rule public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule public RepeatingRule repeatingRule = new RepeatingRule();
	private Set<Long> ids = new HashSet<>();
	private IdGenerator idGen = new AtomicIdGenerator();
	
	@Before
	public void setUp() throws Exception {
	}

	@Concurrent(count = 7)
	@Repeating(repetition=100)
	@Test
	public void test() {
		assertTrue(ids .add(idGen.nextId()));
	}

}
