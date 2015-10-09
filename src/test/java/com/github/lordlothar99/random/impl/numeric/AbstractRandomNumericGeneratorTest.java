/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.lang.String.valueOf;
import static org.apache.commons.collections.ComparatorUtils.naturalComparator;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.ToDoubleFunction;

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
		Optional<T> computedMin = values.stream().min(naturalComparator());
		Optional<T> computedMax = values.stream().max(naturalComparator());
		assertThat("More than " + min, computedMin.get(), new GreaterOrEqual<T>(min));
		assertThat("Less than " + max, computedMax.get(), new LessOrEqual<T>(max));

		// check average
		OptionalDouble avg = values.stream().mapToDouble(toDoubleFunction()).average();
		double expectedAvg = avg(min, max);
		double delta = delta(min, max);
		logger.info("Expecting an average of {} (+/- {})", expectedAvg, delta);
		assertThat("Average is ", avg.getAsDouble(), new EqualsWithDelta(expectedAvg, delta));
		logger.info("Average is {}", avg);
	}

	private ToDoubleFunction<T> toDoubleFunction() {
		return new ToDoubleFunction<T>() {
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

	protected abstract G newGenerator();

	protected abstract T min();

	protected abstract T max();
}
