/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.lang.String.valueOf;
import static org.apache.commons.collections.ComparatorUtils.NATURAL_COMPARATOR;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;
import org.mockito.internal.matchers.EqualsWithDelta;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link RandomBigIntegerGenerator}.
 *
 * @author Francois Lecomte
 */
@SuppressWarnings("unchecked")
public abstract class AbstractRandomNumericGeneratorTest<T extends Comparable<T>, G extends AbstractRandomNumericGenerator<T>> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ToDoubleFunction<T> toDoubleFunction;

	@Test
	public void should_random_numeric_be_not_null() {
		G generator = newGenerator();
		T result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_numeric_be_between_min_and_max() {
		T min = min();
		T max = max();
		G generator = newGenerator();
		generator.setMin(min);
		generator.setMax(max);
		T randomValue = generator.create();
		assertNotNull(randomValue);
		assertThat("More than " + min, randomValue, new GreaterOrEqual<T>(min));
		assertThat("Less than " + max, randomValue, new LessOrEqual<T>(max));
	}

	@Test
	public void should_random_numeric_values_be_well_distributed() {
		G generator = newGenerator();
		T min = min();
		T max = max();
		generator.setMin(min);
		generator.setMax(max);

		List<T> values = new ArrayList<T>();
		for (int i = 0; i < 1000; i++) {
			T randomValue = generator.create();
			values.add(randomValue);
		}

		// check min/max
		T computedMin = min(values);
		T computedMax = max(values);
		assertThat("More than " + min, computedMin, new GreaterOrEqual<T>(min));
		assertThat("Less than " + max, computedMax, new LessOrEqual<T>(max));

		// check average
		double avg = avg(values);
		double expectedAvg = avg(min, max);
		double delta = delta(min, max);
		logger.info("Expecting an average of {} (+/- {})", expectedAvg, delta);
		assertThat("Average is ", avg, new EqualsWithDelta(expectedAvg, delta));
		logger.info("Average is {}", avg);
	}

	private T min(List<T> values) {
		T min = null;
		for (T value : values) {
			if (min == null || NATURAL_COMPARATOR.compare(min, value) > 0) {
				min = value;
			}
		}
		return min;
	}

	private T max(List<T> values) {
		T max = null;
		for (T value : values) {
			if (max == null || NATURAL_COMPARATOR.compare(max, value) < 0) {
				max = value;
			}
		}
		return max;
	}

	private double avg(List<T> values) {
		if (values.isEmpty()) {
			return 0d;
		}
		double sum = sum(values);
		double avg = sum / values.size();
		return avg;
	}

	private double sum(List<T> values) {
		double sum = 0d;
		for (T value : values) {
			sum += toDoubleFunction().applyAsDouble(value);
		}
		return sum;
	}

	private double avg(T min, T max) {
		double minDouble = toDoubleFunction().applyAsDouble(min);
		double maxDouble = toDoubleFunction().applyAsDouble(max);
		double avg = (minDouble + maxDouble) / 2;
		return avg;
	}

	private double delta(T min, T max) {
		double minDouble = toDoubleFunction().applyAsDouble(min);
		double maxDouble = toDoubleFunction().applyAsDouble(max);
		double delta = (maxDouble - minDouble) * .05d;
		return delta;
	}

	private ToDoubleFunction<T> toDoubleFunction() {
		if (toDoubleFunction == null) {
			toDoubleFunction = new ToDoubleFunction<T>() {
				public double applyAsDouble(T value) {
					if (value instanceof Number) {
						Number number = (Number) value;
						return number.doubleValue();
					} else {
						return NumberUtils.toDouble(valueOf(value));
					}
				}
			};
		}
		return toDoubleFunction;
	}

	protected abstract G newGenerator();

	protected abstract T min();

	protected abstract T max();

	private static interface ToDoubleFunction<T> {
		double applyAsDouble(T value);
	}
}
