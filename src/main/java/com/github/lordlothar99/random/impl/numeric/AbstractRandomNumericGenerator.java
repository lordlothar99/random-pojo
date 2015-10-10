package com.github.lordlothar99.random.impl.numeric;

import static java.lang.Math.max;
import static java.lang.String.valueOf;
import static java.math.BigDecimal.ROUND_HALF_EVEN;
import static org.apache.commons.lang.math.RandomUtils.nextDouble;

import java.math.BigDecimal;

import com.github.lordlothar99.random.impl.AbstractRangedGenerator;

public abstract class AbstractRandomNumericGenerator<T> extends AbstractRangedGenerator<T> {

	private int defaultDecimalScale = 2;

	public AbstractRandomNumericGenerator(T min, T max) {
		super(min, max);
	}

	public T create() {
		BigDecimal randomBigDecimal = generateBigDecimal();
		T randomNumber = fromBigDecimal(randomBigDecimal);
		T min = getMin();
		T max = getMax();
		logger.debug("Generated value between {} and {} : {}", min, max, randomNumber);
		return randomNumber;
	}

	protected BigDecimal generateBigDecimal() {
		BigDecimal min = asBigDecimal(this.getMin());
		BigDecimal max = asBigDecimal(this.getMax());

		int scale = max(defaultDecimalScale, max(max.scale(), min.scale()));
		BigDecimal range = max.subtract(min);

		BigDecimal random = new BigDecimal(nextDouble());
		BigDecimal randomNormalized = range.multiply(random).setScale(scale, ROUND_HALF_EVEN);
		BigDecimal randomBigDecimal = randomNormalized.add(min);

		return randomBigDecimal;
	}

	protected BigDecimal asBigDecimal(T number) {
		return new BigDecimal(valueOf(number));
	}

	protected abstract T fromBigDecimal(BigDecimal bigDecimal);

	public void setDefaultDecimalScale(int defaultDecimalScale) {
		this.defaultDecimalScale = defaultDecimalScale;
	}

	public int getDefaultDecimalScale() {
		return defaultDecimalScale;
	}
}
