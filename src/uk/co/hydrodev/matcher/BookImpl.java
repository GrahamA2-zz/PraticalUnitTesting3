package uk.co.hydrodev.matcher;

public class BookImpl implements Book {

	private String title;

	public BookImpl(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getLang() {
		return "English";
	}

}
