package com.github.lordlothar99.random.impl.numeric;

import static java.lang.Math.max;
import static java.lang.String.valueOf;
import static java.math.BigDecimal.ROUND_HALF_EVEN;
import static org.apache.commons.lang.math.RandomUtils.nextDouble;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lordlothar99.random.api.Generator;

public abstract class AbstractRandomNumericGenerator<T> implements Generator<T> {

	private int defaultDecimalScale = 2;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private T min;
	private T max;
	
	public AbstractRandomNumericGenerator(T min, T max) {
		this.min = min;
		this.max = max;
	}

	public T create() {
		BigDecimal randomBigDecimal = generateBigDecimal();
		T randomNumber = fromBigDecimal(randomBigDecimal);
		logger.info("Min : " + min);
		logger.info("Max : " + max);
		logger.info("Generated : " + randomNumber);
		return randomNumber;
	}

	protected BigDecimal generateBigDecimal() {
		BigDecimal min = asBigDecimal(this.min);
		BigDecimal max = asBigDecimal(this.max);

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

	public void setMin(T min) {
		this.min = min;
	}

	public void setMax(T max) {
		this.max = max;
	}

	public void setDefaultDecimalScale(int defaultDecimalScale) {
		this.defaultDecimalScale = defaultDecimalScale;
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

	public int getDefaultDecimalScale() {
		return defaultDecimalScale;
	}
}
