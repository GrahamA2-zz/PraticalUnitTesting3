package uk.co.hydrodev.misc;

public class AtomicIdGenerator implements IdGenerator {

	private static Long nextID = System.currentTimeMillis();
	@Override
	public Long nextId() {
		return nextID++;
	}

}
