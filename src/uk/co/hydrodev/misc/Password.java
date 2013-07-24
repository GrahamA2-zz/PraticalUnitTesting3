package uk.co.hydrodev.misc;

public class Password {

	private String password;
	private static final int MIN_LENGTH = 6;

	public Password(String password) {
		this.password = password;
	}

	public Boolean isValid() {
		boolean lengthValid = password.length() >= MIN_LENGTH;
		for ( String s : password.split("")){
	
		}
		boolean caseValid = !(password.toUpperCase().equals(password));
		return lengthValid && caseValid;
	}

}
