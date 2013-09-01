package uk.co.hydrodev.matcher;


import static uk.co.hydrodev.matcher.BookAssert.*;

import org.junit.*;

public class BookTest {

	private static final String TITLE = "My Book";
	private Book myBook;
	@Before
	public void setUp() throws Exception {
		 myBook  = new BookImpl(TITLE);
	}

	@Test
	public void test() {
		assertThat(myBook).hasTitle(TITLE).isNotIn("SciFi").isWrittenIn("English");
	}


}
