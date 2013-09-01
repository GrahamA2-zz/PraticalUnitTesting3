package uk.co.hydrodev.spike.pattern2;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import uk.co.hydrodev.spike.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

	private static final String MOCK_TEXT = "Mock Foo";
	@Mock private MyClass.FactoryHelper mockFactoryHelper; 
	@Mock private Foo mockFoo; 
	
	@Before
	public void setUp() throws Exception {
		when(mockFoo.toString()).thenReturn(MOCK_TEXT);
		when(mockFactoryHelper.makeFoo()).thenReturn( mockFoo );
	}

	@Test
	public void testMockFooIsUSedByTheMock() {	
		assertThat(mockFoo.toString(),is(MOCK_TEXT));
		verify(mockFoo).toString();
	}

	@Test
	public void testMockFooIsUsedInTheSpy() { 
		MyClass toTest = new MyClass(mockFactoryHelper); 
		assertThat(toTest.print(),is(MOCK_TEXT));
		verify(mockFoo).toString();
	}
}
