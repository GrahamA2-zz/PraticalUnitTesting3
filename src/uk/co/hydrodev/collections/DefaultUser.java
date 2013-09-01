package uk.co.hydrodev.collections;

import java.util.*;

public class DefaultUser implements User {

	private Collection<String> phones = new HashSet<>(); 
	
	@Override
	public void addPhoneNumber(String phoneNumber) {
		phones.add(phoneNumber);
	}

	@Override
	public Collection<String> getPhoneNumbers() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableCollection(phones);
	}

}
