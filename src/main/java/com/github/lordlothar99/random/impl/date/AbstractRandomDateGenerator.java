package com.github.lordlothar99.random.impl.date;

import com.github.lordlothar99.random.impl.AbstractRangedGenerator;

public abstract class AbstractRandomDateGenerator<D> extends AbstractRangedGenerator<D> {

	public AbstractRandomDateGenerator(Class<? extends D> clazz) {
		super(null, null, clazz);
		setMin(fromLong(0));
		setMax(fromLong(System.currentTimeMillis()));
	}

	public AbstractRandomDateGenerator() {
		super(null, null);
		setMin(fromLong(0));
		setMax(fromLong(System.currentTimeMillis()));
	}

	public AbstractRandomDateGenerator(D min, D max, Class<? extends D> clazz) {
		super(min, max, clazz);
	}

	public AbstractRandomDateGenerator(D min, D max) {
		super(min, max);
	}

	public D create() {
		long min = asLong(getMin());
		long max = asLong(getMax());

		long randomLong = toolkit().longValue(min, max);
		D randomDate = fromLong(randomLong);
		logger.info("Generated value between {} and {} : {}", min, max, randomDate);
		return randomDate;
	}

	protected abstract long asLong(D date);

	protected abstract D fromLong(long timeInMillis);

}
