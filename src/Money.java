

public class Money {
	private final int ammount;
	private final String currency;

	public Money(final int ammount, final String currency) {
		super();
		if ( ammount < 0 ){
			throw new IllegalArgumentException("illegal amount: [" + ammount + "]");
		}
		if ( (currency == null) || currency.isEmpty()){
			throw new IllegalArgumentException("illegal currency: [" + currency + "]");
		}
		this.ammount = ammount;
		this.currency = currency;
	}

	protected int getAmmount() {
		return ammount;
	}

	protected String getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ammount;
		result = (prime * result) + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Money){
			final Money money = (Money) obj;
			return money.getCurrency().equalsIgnoreCase(getCurrency()) && (getAmmount() == money.getAmmount());
		}
		else
			return false;
	}



}
