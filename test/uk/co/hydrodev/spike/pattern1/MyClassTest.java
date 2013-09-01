package uk.co.hydrodev.spike.pattern1;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.runners.*;

import uk.co.hydrodev.spike.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {
	
	private static final String MOCK_TEXT = "Mock Foo";
	private Foo mockFoo = mock(Foo.class);
	private MyClass toTest = spy(new MyClass());
			
//	private MyClass toTest = new MyClass(){	
//	@Override
//	Foo makeFoo() {
//		return mockFoo;
//	}}; 

	@Before
	public void setUp() throws Exception {
		when(mockFoo.toString()).thenReturn(MOCK_TEXT);
		doReturn( mockFoo ).when( toTest ).makeFoo( ); 
		
	}
	
	@Test
	public void testMockFooIsUSedByTheMock() {	
		assertThat(mockFoo.toString(),is(MOCK_TEXT));
		verify(mockFoo).toString();
	}

	@Test
	public void testMockFooIsUsedInTheSpy() { 
		assertThat(toTest.print(),is(MOCK_TEXT));
		verify(mockFoo).toString();
	}

}
